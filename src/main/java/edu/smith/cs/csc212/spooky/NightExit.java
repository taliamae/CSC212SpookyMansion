package edu.smith.cs.csc212.spooky;

public class NightExit extends Exit {

	public NightExit(String target, String description) {
		super(target, description);
		// TODO Auto-generated constructor stub
	}
	/* If it's night, exit is open.
	 * Returns true if hour is between 20 and 6
	 */
	public boolean isSecret(GameTime t) {
		return t.isNightTime();
	}

}
