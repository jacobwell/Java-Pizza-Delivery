/**
 *
 * DB class is responsible for managing all data storage.
 *
 *@author ijd8975 Ian Dempsey
 */

import java.io.Serializable;
import java.util.*;

public class DB implements Serializable {
	
	public Hashtable< Long, Customer >	customers;
	public Hashtable<Long, Order>		history;
	public Hashtable<String, Item>		menu;
	public HashSet<String>				toppings;
	public HashSet<String>				messages;
	
	public int	cooks;
	public int	drivers;
	public int	ovens;
	
	public int ordersIn = 1;

	public DB() {
		
		customers	= new Hashtable	< Long, Customer >();
		history		= new Hashtable	< Long, Order >();	
		menu 		= new Hashtable	< String, Item >();
		toppings	= new HashSet	< String >();
		messages	= new HashSet   <String>();
		
		//		   KEY						NAME			KEY			PRICE	PR	CK	V	TOP PRICE	PARENT ORDER ID
		menu.put( "Small Pizza",	new Item("Small Pizza",	"small",	8.00,	8,	13,	1,	1.00,		0) );
		menu.put( "Medium Pizza",	new Item("Medium Pizza","medium",	11.00,	10,	15,	2,	1.50,		0) );
		menu.put( "Large Pizza",	new Item("Large Pizza",	"large",	16.00,	15,	20,	4,	2.00,		0) );
		menu.put( "Pizza Logs",		new Item("Pizza Logs",	"logs",		6.00,	0,	10,	1,	0.00,		0) );
		menu.put( "Tossed Salad",	new Item("Small Salad",	"salad",	5.00,	5,	0,	0,	0.00,		0) );
		
		toppings.add("Pepperoni");
		toppings.add("Sausage");
		toppings.add("Onions");
		toppings.add("Peppers");
		toppings.add("Mushrooms");
		
		cooks 	= 2;
		drivers = 2;
		ovens 	= 10;
		
	}
	
	public Hashtable<Long, Customer>	getCustomerDB() { return customers; }
	public Hashtable<Long, Order>		getOrderDB()    { return history; }
	public void		addTopping( String t ) { toppings.add(t); }
	public boolean	hasTopping( String t ) { return toppings.contains(t); }
	
	
	//	CUSTOMER, MENU  & ORDER METHODS
	
	public void		addCustomer( Customer c )	{ customers.put( c.getPhone(), c); }
	public void		addOrder( Order o )			{ history.put( Long.valueOf(o.getID()), o); }
	public void		addItem( Item i )			{ menu.put( i.getKeyName(), i ); }
	public Customer findCustomer( Long n )		{ return customers.get( n ); }
	
	public boolean	isOrder( Long i ){ 
		if (history.get(i) != null) { return true; }
		else { return false; }
	}
	
	// find Order by 
	public Order	findOrder( Long i )			{ return history.get( i ); }
	public Item		findItem( String s )		{ return menu.get( s ); }
	
	public Hashtable<String, Item> 	getMenu() 	{ return menu; }
	
	public void		removeCustomer( Long n )	{ customers.remove( n ); }
	public void		removeOrder( int i )		{ history.remove( i ); }
	public void		removeItem( String s )		{ menu.remove( s ); }
	
	public int		numCustomers()				{ return customers.size(); }
	public int		numOrders()					{ return history.size(); }
	public int		numItems()					{ return menu.size(); }
	

	//	DRIVER, COOK & OVEN METHODS 
	
	public int getCooks()		{ return cooks; }
	public int getDrivers()		{ return drivers; }
	public int getOvens()		{ return ovens;}
	
	public void setCooks(int c)		{ cooks = c; }
	public void setDrivers(int d)	{ drivers = d; }
	public void setOvens(int o)		{ ovens = o; }
	

	public void addCook()		{ cooks++; }
	public void addDriver()		{ drivers++; }
	public void addOven()		{ ovens++; }
	
	public void removeCook()	{ if (cooks > 0)	cooks--; }
	public void removeDriver()	{ if (drivers > 0)	drivers--; }
	public void removeOven()	{ if (ovens > 0)	ovens--; }
	
	
	//toString methods
	
	//Customers toString
	public String toStringCustomer(){
		String result = "Customer Database \n \n";
		Object[] data = customers.values().toArray();
		for(int i = 0; i < data.length; i++){
			result += (Customer)data[i] + "\n"; 
		}
		return result;
	}
	
	//history toString
	public String toStringHistory(){
		String result = "History \n \n";
		Object[] data = history.values().toArray();
		for(int i = 0; i < data.length; i++){
			result += (Order)data[i] + "\n"; 
		}
		return result;
	}
	
	//toppings toString
	public String toStringToppings(){
		String result = "Toppings \n \n";
		Object[] data = toppings.toArray();
		for(int i = 0; i < data.length; i++){
			result += (String)data[i] + "\n"; 
		}
		return result;
	}
	
	
	//menu toString
	public String toStringMenu(){
		String result = "Menu \n";
		Object[] data = menu.values().toArray();
		for(int i = 0; i < data.length; i++){
			result += (Item)data[i] + "\n"; 
		}
		return result;
	}
}
