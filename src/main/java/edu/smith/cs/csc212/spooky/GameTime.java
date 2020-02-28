package edu.smith.cs.csc212.spooky;

public class GameTime {
	private int hour = 0;
	
	public GameTime() {
	
	}
	/*
	 * @return int current hour in military time 
	 */
	public int getMilTime() {
		return this.hour % 24;
	}
	/*
	 * @return int total hours spent in game
	 */
	public int getTotalTime() {
		return this.hour;
	}
	/*
	 * Increases hour spent in game. Called every time a player goes to a new room.
	 */
	public void incHour() {
		this.hour += 1;
	}
	/*
	 * Increases time by two hours. Called when player enters "rest"
	 */
	public void rest() {
		this.hour += 2;
	}
	/*
	 * @return true if between hours of 20h and 6h
	 */
	public boolean isNightTime() {
		if (this.getMilTime() > 20 || this.getMilTime() < 6) {
			return true;
		} else {
			return false;
		}
		
	}
	

}
