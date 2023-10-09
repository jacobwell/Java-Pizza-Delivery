/**
 *
 *The Customer class holds customer data and allows 
 *for access and change of such information
 *
 *@author jxd2847 Ian Dempsey
 *@author cjd8363 Charlene DiMeglio
 *@author jgw7654 Jacob Wellinghoff
 */

import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.Stack;

public class Customer implements Serializable {
	
	/**
	 * String representing the customer's name
	 */
	private String name;
	
	
	/**
	 * The customer's phone number, will be unique per customer
	 */	
	private Long phone;
	
	
	/**
	 * The customer's location
	 */
	private Location location;
	
	
	/**
	 * An array of the customer's previous orders
	 */
	private Stack<Order> history;
	
	
	/**
	 * Constructor for Customer
	 * 
	 * @param  name      The name of the customer 
	 * @param  phone     The phone number of the customer
	 * @param  location  The location of the customer  
	 */
	public Customer(String name, Long phone, Location location){
		this.name = name;
		this.phone = phone;
		this.location = location;
		this.history = new Stack<Order>();
	}
	
	
	/**
	 * 
	 * Returns the name of the Customer
	 * 
	 * @return name
	 */
	public String getName(){
		return name;
	}
	
	
	/**
	 * 
	 * Changes the name of the customer
	 *
	 *@param newName the new version of the name to be stored
	 */
	public void setName(String newName){
		this.name = newName;
	}
	
	
	/**
	 * 
	 * Returns the phone number of the Customer
	 * 
	 * @return phone
	 */
	public Long getPhone(){
		return phone;
	}
	
	
	/**
	 * 
	 * Changes the phone number of the customer
	 *
	 *@param newPhone the new version of the phone number to be stored
	 */
	public void setPhone(Long newPhone){
		this.phone = newPhone;
	}
	
	
	/**
	 * 
	 * Return the location of the Customer
	 * 
	 * @return location
	 */
	public Location	getLocation(){return 
			location;
	}
	
	
	/**
	 * 
	 * Changes the location of the customer
	 *
	 *@param newLocal the new Location to be stored
	 */
	public void setLocation(Location newLocal){
		this.location = newLocal;
	}
	
	
	/**
	 * 
	 * Return the order history of the customer
	 * 
	 * @return history
	 */
	public Stack<Order>	getHistory(){
		return history;
	}
	
	
	/**
	 *
	 * Adds a new order to the history
	 * 
	 * @param newOrder
	 */
	public void addOrder(Order newOrder){
		history.push(newOrder);
	}
	
	/**
	 *
	 * Removes the last imported order in the history
	 * 
	 * Pre-condition: history is not empty
	 * 
	 * @return the order that was removed
	 */
	public Order removeOrder(){
		try{
			return history.pop();
		}
		catch(EmptyStackException e){
			System.err.println("History is empty. No order to remove");
		}
		return null;
	}
	
	
	/**
	 *
	 * Returns a string representation of the customer
	 * 
	 * @return a string representation of the customer
	 */
	public String toString(){
		return "Name: " + this.getName() + "\n" + "Phone: " + this.getPhone() + "\n" + "Location: " + this.getLocation() + 
				"\n" + this.stringOrders() + "\n";  
	}
	
	
	/**
	 *
	 * Returns a string representation of the history
	 * 
	 * @return a string representation of the history
	 */
	public String stringOrders(){
		String result = "";
		for (int i = 0; i < this.getHistory().size(); i++){
			result = result + this.getHistory().elementAt(i) + "\n";
		}
		return result;
	}
	
}