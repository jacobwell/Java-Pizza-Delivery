/**
 * Order.java
 * Project: Pizza Delivery System
 * Team D 
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class Order implements Serializable {
	
	// - - - - - - - - - - - - - - - - - - - - //
	//               Attributes                //
	// - - - - - - - - - - - - - - - - - - - - //
	

	private Customer		customer;
	private Location		location;
	private ArrayList<Item> items;
	private Status			status;
	private double			total;
	private Date			nextAdvance;
	private int				id;
	
	public enum Status { ORDERED, READY, DELIVERED }
	
	// - - - - - - - Time Logs - - - - - - - - //
	
	public Date timeOrdered;
	public Date timeEstimated;
	public Date timeDelivered;
	public Date timeCancelled;
	
	// - - - - - - - - - - - - - - - - - - - - //
	//                Methods                  //
	// - - - - - - - - - - - - - - - - - - - - //
	public Order(Customer c, int n, Date t) {
		id = n;
		customer = c;
		location = c.getLocation();
		items = new ArrayList<Item>();
		status = Status.ORDERED;
		
		total = 0;
		
		timeOrdered = t;
		nextAdvance = t;
	
	}
	
	public Order(Order o) {
		id 			= o.getID();
		customer 	= o.getCustomer();
		location	= o.getLocation();
		items		= o.getItems();
		status 		= Status.ORDERED;
		total 		= o.getTotal();
		timeOrdered = o.getTimeOrdered();
		nextAdvance = o.getNextAdvance();
	}
	
	// - - - - - - - Accessors - - - - - - - - //
	
	public int 				getID()			{ return id; }
	public Customer 		getCustomer()	{ return customer; }
	public Location 		getLocation()	{ return location; }
	public ArrayList<Item>	getItems()		{ return items; }
	public double 			getTotal()		{ 
		double temp = 0.00; 
		for (Item item : items) {
			temp += item.getPrice();
		} 
		return temp;
		 }
	
	public Status			status()		{ return status; }
	
	public Date getNextAdvance()			{ return nextAdvance; }
	public void setNextAdvance( Date d )	{ nextAdvance = d; }
	
	public Date getTimeOrdered()	{ return timeOrdered; }
	public Date getTimeEstimated()	{ return timeEstimated; }
	public Date getTimeDelivered()	{ return timeDelivered; }
	public Date getTimeCancelled()	{ return timeCancelled;}
	
	// - - - - - - - Functions - - - - - - - - //
	
	/**
	 * addItem
	 * Adds the given item to the order
	 * @param i - item to be added
	 */
	public void addItem(Item i) {
		items.add(i);
		total += i.getPrice();
	}
	
	/**
	 * removeItem
	 * remove item from the order
	 * @param i - item to be removed 
	 */
	public void removeItem(Item i) {
		items.remove(i);
		total += i.getPrice();
	}
	
	/**
	 * empty
	 * checks if there are items in the order
	 * @return 
	 */
	public boolean empty() {
		return items.isEmpty();
	}
	
	/**
	 * ready
	 * Checks if the order is ready to be delivered
	 * @return true if is ready
	 */
	public boolean ready() {
		for (Item item: items) { if ( item.state() != Item.State.DONE ) return false; }
		return true;
	}
	
	/**
	 * toString
	 * Displays the order
	 */
	public String toString() { return "Order #" + id + " -- " + customer.getName() + " (" + customer.getPhone() + ") -- " + customer.getLocation(); }
	
	/**
	 * advance
	 * Advances the order according to the order status
	 * @param t - time given
	 */
	public String advance(Date t) {
		String printed = ""; 
		switch (status) {
			case ORDERED:
				status = Status.READY;
				setNextAdvance(t);
				printed = t + ":  Order #" + id + " is ready for delivery.";					
				break;
			
			case READY:
				status = Status.DELIVERED;
				Date delivered = t;
				printed=t.toString();
				delivered.setTime( t.getTime() + location.driveTime * 60 * 1000 );
				timeDelivered = delivered;
				printed+= ":  Order #" + id + " out for delivery.   ETA: " + delivered;
				Date returned = t;
				returned.setTime( delivered.getTime() + (location.driveTime * 60 * 1000) + (2 * 60 * 1000) );
				setNextAdvance(returned);
				break;
				
			case DELIVERED:
				printed =  t.getTime() + ":  Order " + id + " complete!";
				break;
		}
		return printed;		
	} 
}
