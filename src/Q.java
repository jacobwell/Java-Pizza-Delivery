import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Date;
import java.io.Serializable;

public class Q extends Thread implements Serializable{
	
	public PriorityQueue<Order>	orderQ;
	public PriorityQueue<Item>	itemQ;
	public PriorityQueue<Order>	deliveryQ;
	
	public Boolean locked = false;
	
	public DB db;
	public PDSGUI pdsgui;
	
	public Date now;
	private int timeForward = 0;
	
	public Q (DB _db, PDSGUI _pdsgui) {		
		orderQ 		= new PriorityQueue<Order>(11, new OrderComparator());
		itemQ 		= new PriorityQueue<Item>(11, new ItemComparator());
		deliveryQ 	= new PriorityQueue<Order>(11, new OrderComparator());
		pdsgui = _pdsgui;
		
		db = _db;
		now = new Date();
		
		start();
	}

	public Date now() {
		Date time = new Date();
		time.setTime( time.getTime() + timeForward );
		return time;
	}
	

	public void run() {
		while ( true ) {
			now = now();		
			
			processOrders();
			processItems();
			processDeliveries();
			
			try { Thread.sleep(300); } 
			catch ( InterruptedException e) {}
		}
	}
	
	public void processOrders() {	
		if (!locked && orderQ.peek() != null && orderQ.peek().getNextAdvance().before(now)) { //System.out.println("[Processing Orders]");
			Order top = orderQ.peek();
			if ( top.ready() ) {
				pdsgui.updateLog(top.advance(now()));
				deliveryQ.add( orderQ.poll() );
			} else { //System.out.println("Order " + " isn't ready yet! Check again later.");
				top.setNextAdvance(delay());
				orderQ.add(orderQ.poll());
			}
		}	
	}
	

	public void processItems() {
		if (!locked && itemQ.peek() != null) {
			if (itemQ.peek().getNextAdvance().before(now)) { //System.out.println("[Processing Items]");
				Item top = itemQ.peek();
				switch ( top.state() ) {
					
					case RAW:
						if (db.getCooks() >= 1) {
							//JNOTE BRB 
							//db.pdsgui.updateLog( db.getCooks() + " cook(s) available." );
							db.removeCook();
							pdsgui.updateLog(top.advance(now())); //System.out.println("An item is being prepped.");
							pdsgui.refresh();
						} else { 
							top.setNextAdvance(delay()); //System.out.println("Not enough cooks! Delayed!");
						}
						itemQ.add(itemQ.poll());			
						break;
						
					case PREPPING:
						if (db.getOvens() >= top.getOvenSpace()) {
							db.setOvens(db.getOvens() - top.getOvenSpace());
							//db.pdsgui.updateLog( db.getOvens() + " oven space(s) left." );
							pdsgui.updateLog(db.getOvens() + " oven space(s) left.");
							pdsgui.updateLog(top.advance(now()));	//System.out.println("An item is waiting to be cooked.");
							pdsgui.refresh();
						} else {
							top.setNextAdvance(delay()); //System.out.println("Not enough oven space! Delayed!");
						}
						itemQ.add(itemQ.poll());
						break;
						
					case COOKING:
						db.setOvens(db.getOvens() + top.getOvenSpace());
						db.addCook();
						pdsgui.updateLog(top.advance(now()));
						pdsgui.refresh();
						itemQ.add(itemQ.poll()); //System.out.println("An item is done cooking!");
						break;
						
					case DONE:
						itemQ.poll();
						break;
				}
			}
		}
	}
	
	public void processDeliveries() {
		if (!locked && deliveryQ.peek() != null && deliveryQ.peek().getNextAdvance().before(now())) { //System.out.println("[Processing Deliveries]");
			Order top = deliveryQ.peek();
			switch ( top.status() ) {
				case READY:
					if (db.getDrivers() >= 1) {
						pdsgui.updateLog( db.getDrivers() + " driver(s) available." );
						pdsgui.refresh();
						db.removeDriver();
						pdsgui.updateLog(top.advance(now()));
					} else {
						top.setNextAdvance(delay());
					}
					deliveryQ.add(deliveryQ.poll());
					break;
					
				case DELIVERED:
					pdsgui.updateLog(now() + ": Driver returned after delivering order #" + top.getID() + " to " + top.getLocation() + "." );
					db.addDriver();
					deliveryQ.poll();
					break;
			}
		}
	}	

	public Date delay() {  // Returns a Date object that is the current time + 10 minutes.
		Date next = now();
		next.setTime(next.getTime() + 10 * 1000);
		return next;
	}
	
	public int spedUp() { return timeForward; }
	

	public void speedUp(int x) { timeForward += x * 60000; } // Elapses time by x minutes.
	
	public void cancelOrder( Order o ){ 
		if(db.isOrder((long) o.getID())){
			db.removeOrder(o.getID());	
			orderQ.remove(o.getID()); 
			for(int i = 0; i < o.getItems().size(); i++){
				itemQ.remove(o.getItems().get(i));
			} 
			if(deliveryQ.contains(o)){
				deliveryQ.remove(o);
			}
		}
	}
	
	
	
	
	
	
}
