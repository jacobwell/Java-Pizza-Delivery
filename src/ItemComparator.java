/**
 * ItemComparator.java
 * Project: Pizza Delivery System
 * Team D 
 */
import java.util.Comparator;
import java.util.Date;

/**
 * Item Comparator class
 */
public class ItemComparator implements Comparator<Item> {
	
	/**
	 * compare
	 * compares if two items are the same
	 * @param a first item
	 * @param b second item
	 * @return int representation of the comparason
	 */
	public int compare(Item a, Item b) {
        return a.getNextAdvance().compareTo(b.getNextAdvance());
    }
}