/**
 * OrderComparator.java
 * Project: Pizza Delivery System
 * Team D 
 */
import java.util.Comparator;
import java.util.Date;

/**
 * OrderComparator
 */
public class OrderComparator implements Comparator<Order> {
	
	/**
	 * compare
	 * Compares to order objects
	 * @param a - first order
	 * @param b - second order
	 */
	public int compare(Order a, Order b) {
        return a.getNextAdvance().compareTo(b.getNextAdvance());
    }
}