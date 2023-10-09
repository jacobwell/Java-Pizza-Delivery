/**
 *
 *This class represents food items
 *
 *@author ijd8975 Ian Dempsey
 *@author cjd8363 Charlene DiMeglio
 */

import java.io.Serializable;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Date;
import java.text.DecimalFormat;

public class Item implements Serializable {
	
	/**
	 * the name of the item
	 */
	
	private String	name;
	
	/**
	 * the name of the key
	 */
	private String	keyName;
	
	/**
	 * the price of the item
	 */
	private double	price;
	
	/**
	 * the prep time of the item
	 */
	private int		prepTime;
	
	/**
	 * the cooke time of the item
	 */
	private int		cookTime;
	
	/**
	 * the space the item takes in the oven
	 */
	private int		ovenSpace;
	
	/**
	 * the price of toppings for the item
	 */
	private double	toppingPrice;
	
	/**
	 * the current state of the item
	 */
	private State	state;
	
	/**
	 * the number of the parent of the item
	 */
	private int		parent;
	
	/**
	 * The time for the next step in production
	 */
	private Date	nextAdvance;
	
	/**
	 * The number of toppings
	 */
	private int fullToppings = 0;
	
	/**
	 * Is it ready?
	 */
	private boolean ready = false;
	
	/**
	 * A mapping of the toppings to the side they are on 
	 */
	private TreeMap<String, Character> toppings = new TreeMap<String, Character>(); // Char is the side the topping is on. L, R, B
	
	/**
	 * States of production
	 */
	public enum State { RAW, PREPPING, COOKING, DONE }
	
	/**
	 * 
	 * Full constructor of item
	 * 
	 * @param n  the name of the item
	 * @param k  the key name of the item
	 * @param p  the price of the item
	 * @param pt the prep time of the item
	 * @param ct the cooking time of the item
	 * @param os the oven space the item takes up
	 * @param tp the topping price for the item
	 */
	public Item(String n, String k, double p, int pt, int ct, int os, double tp, int _p) {
		name 		= n;
		keyName 	= k; 
		price 		= p;
		prepTime 	= pt;
		cookTime 	= ct;
		ovenSpace 	= os;
		toppingPrice = tp;
		state = State.RAW;
		parent = _p;
		//pdsgui = _pdsgui;
	}
	
	/**
	 * 
	 * Copy Constructor
	 * 
	 * @param it the item to be copied
	 * @param p the number of the parent
	 */
	public Item(Item it, int p) {
		name 		= it.getName();
		keyName 	= it.getKeyName();
		price 		= it.getPrice();
		prepTime	= it.getPrepTime();
		cookTime 	= it.getCookTime();
		ovenSpace	= it.getOvenSpace();
		toppingPrice = it.getToppingPrice();
		state 		= State.RAW;
		parent 		= p;
	}
	
	/**
	 * 
	 * Returns the name of the item
	 * 
	 * @return name
	 */
	public String	getName() 			{ return name; }
	
	/**
	 * 
	 * Returns the key name of the item
	 * 
	 * @return keyName
	 */
	public String	getKeyName()		{ return keyName; }
	
	/**
	 * 
	 * returns a printable version of the item
	 * 
	 */
	public String toString()	{ 
		String print;
		print = "$" + getPrice() + " - " + name;
		if (toppingPrice > 0 && toppings.size() > 0) {
			//JNOTE: edited here per Ian's suggestion
			print += ": ";
			ArrayList<String> Both = new ArrayList<String>();
			ArrayList<String> Left = new ArrayList<String>();
			ArrayList<String> Right = new ArrayList<String>();
			for (String key : getToppings().keySet()) {
				switch ( getToppings().get(key) ) {
					case 'B': Both.add(key); break;
					case 'L': Left.add(key); break;
					case 'R': Right.add(key); break;
				}
			}
			if (Both.size() > 0) { 
				print += "\n" + "\t" + "B: ";
				for (int i = 0; i < Both.size(); i++) { print += Both.get(i).substring(0, 3) + ", "; }
				print = print.substring(0, print.length() - 2);
				print += "\n";
				}
			if (Left.size() > 0) { 
				print += "\n" + "\t" + "L: ";
				for (int i = 0; i < Left.size(); i++) { print += Left.get(i).substring(0, 3) + ", "; }
				print = print.substring(0, print.length() - 2);
				print += "\n";
				}
			if (Right.size() > 0) { 
				print += "\n" + "\t" + "R: ";
				for (int i = 0; i < Right.size(); i++) { print += Right.get(i).substring(0, 3) + ", "; }
				print = print.substring(0, print.length() - 2);
				print += "\n";
				}
		} 
		return print;
		}
		
	/**
	 * 
	 * Returns the price of the item, toppings included
	 * 
	 * @return the price of the item
	 */
	public double	getPrice() 			{ return price + (toppings.size() + fullToppings) * toppingPrice/2; }
	
	/**
	 * 
	 * Returns the price of the toppings for the item
	 * 
	 * @return toppingPrice
	 */
	public double	getToppingPrice()	{ return toppingPrice; }
	
	/**
	 * 
	 * Returns the prep time for the item
	 * 
	 * @return prepTime
	 */
	public int		getPrepTime()		{ return prepTime; }
	
	/**
	 * 
	 * Returns the cook time for the item
	 * 
	 * @return cookTime
	 */
	public int		getCookTime()		{ return cookTime; }
	
	/**
	 * 
	 * Returns the oven space for the item
	 * 
	 * @return ovenSpace
	 */
	public int		getOvenSpace()		{ return ovenSpace; }
	
	/**
	 * 
	 * Returns the state for the item
	 * 
	 * @return state
	 */
	public State	state()				{ return state; }
	
	/**
	 * 
	 * Returns whether the item is ready or not
	 * 
	 * @return ready
	 */
	public boolean	isReady()			{ return ready; }
	
	/**
	 * 
	 * Returns the next advance time
	 * 
	 * @return nextAdvance 
	 */
	public Date		getNextAdvance() 		{ return nextAdvance; }
	
	/**
	 * 
	 * Set the next advance time
	 * 
	 * @param d the time to be set
	 */
	public void 	setNextAdvance(Date d)	{ nextAdvance = d; }
	
	/**
	 * 
	 * Returns the map of toppings
	 * 
	 * @return toppings
	 */
	public TreeMap<String, Character> getToppings(){ return toppings; }
	
	/**
	 * 
	 * sends the item through a process depending on its state
	 * 
	 * @param t the current date (time)
	 */
	public String advance(Date t) { 
		String printed = "";
		switch (state) {
			
			case RAW:
				printed =  t + ": Item " + name + " started getting prepped. [" + prepTime + " minutes]"; //(Order #" + parent + ")" ;
				state = State.PREPPING;
				Date p = t;
				p.setTime( t.getTime() + prepTime * 60 * 1000 );
				setNextAdvance(p);
				break;
				
			case PREPPING:
				printed =  t + ": Item " + name + " was put into the oven. [" + cookTime + " minutes.]"; //(Order #" + parent + ")";
				state = State.COOKING;
				Date c = t;
				c.setTime( t.getTime() + cookTime * 60 * 1000 );
				setNextAdvance(c);
				break;
				
			case COOKING:
				printed =  t + ": Item " + name + " cooked and ready."; //(Order #" + parent + ")" ;
				state = State.DONE;
				setNextAdvance(t);
				break;
				
			case DONE:
				break;
		}
		return printed;		
	} 
	
	/**
	 * 
	 * Add a topping to the item
	 * 
	 * @param name the name of the topping
	 * @param side the side it is on
	 */
	public void addTopping(String name, Character side) {
		if ( toppings.containsKey(name) ) {
			int oldSide = toppings.get(name);
			if (oldSide == 'B') fullToppings--;  
			toppings.remove(name);
		}
		if (side == 'B') fullToppings++;
		toppings.put(name, side);
	}
	
	/**
	 * 
	 * Remove a topping from an item
	 * 
	 * @param name the name of the topping to be removed
	 */
	public void removeTopping( String name ) { 
		if ( toppings.containsKey(name) ) { toppings.remove(name); }
		else {} //System.out.println( "That topping is not available." ); }
	}
}