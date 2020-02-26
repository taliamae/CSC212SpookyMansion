package edu.smith.cs.csc212.spooky;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents all of the game state needed to understand the player.
 * At the beginning of this project, it is just the ID or name of a place.
 * 
 * @author jfoley
 *
 */
public class Player {
	/**
	 * The ID of the Place object where the player is currently.
	 */
	private String place;
	
	// The player will now remember places they have been.
	private Set<String> visited;
	
	// List of items the player has.
	public Set<String> keys;

	/**
	 * A player is created at the start of a game with just an initial place.
	 * @param initialPlace - where do we start?
	 */
	
	public Player(String initialPlace) {
		this.place = initialPlace;
		this.visited = new HashSet<>();
	}
	
	/**
	 * Adds place to player's memory.
	 */
	public void markVisited() {
		this.visited.add(place);
		
	}
	
	/**
	 * Checks if player has been to place before.
	 * @return true if player has visited before.
	 */
	public boolean beenHere() {
		return this.visited.contains(this.getPlace());
	}

	/**
	 * Get access to the place instance variable from outside this class.
	 * @return the id of the current location.
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * Call this method when the player moves to a new place.
	 * @param place - the place we are now located at.
	 */
	public void moveTo(String place) {
		this.markVisited();
		this.place = place;
	}
	
	public void addStuff(String item) {
		this.keys.add(item);
	}
	
	//public boolean hasStuff() {
		//return 
	//}
}
