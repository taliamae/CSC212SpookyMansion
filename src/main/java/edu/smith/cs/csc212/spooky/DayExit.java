package edu.smith.cs.csc212.spooky;

public class DayExit extends Exit {

	public DayExit(String target, String description) {
		super(target, description);
		// TODO Auto-generated constructor stub
	}
	/* If it's day, exit is open.
	 * Returns true if hour is between 6 and 20
	 */
	public boolean isSecret(GameTime t) {
		return !t.isNightTime();
	}
}
