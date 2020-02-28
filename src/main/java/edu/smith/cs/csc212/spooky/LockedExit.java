package edu.smith.cs.csc212.spooky;

public class LockedExit extends Exit {
	// canOpen set to false
	private boolean open = false;
	// What does the user need to open the exit?
	String required;

	public LockedExit(String target, String description, String required) {
		super(target, description);
		// TODO Auto-generated constructor stub
		this.required = required;
	}
	
	/**
	 * Can the player open this door?
	 * @param player - the player object (and all other state)
	 * @return true if that is OK, false if they need something special.
	 */
	public boolean canOpen(Player player) {
		return this.open;
	}
	
	/*
	 * changes boolean canOpen to true if player has key in inventory
	 */
	public void unlock(Player player) {
		for (String item : player.keys) {
			if (item.equals(this.required)) {
				this.open = true;
			}
		}
		
	}
}
