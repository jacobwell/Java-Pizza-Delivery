/**
 * Location.java
 * Project: Pizza Delivery System
 */
public enum Location {

	RIT(18), UR(12), NAZARETH(25), FISHER(21), RWC(25), MCC(18);
	
	public int driveTime;
	
	private Location(int t) { this.driveTime = t; }
}