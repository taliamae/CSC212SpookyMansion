package edu.smith.cs.csc212.spooky;

import java.util.List;

public class SecretExit extends Exit {
	
	private boolean isSecret = true;

	public SecretExit(String target, String description) {
		super(target, description);
		// TODO Auto-generated constructor stub
		//List<String> words = input.getUserWords();
		//String action = words.get(0).toLowerCase().trim();
		
	}
	
	/*
	 * @Override Returns true if exit is secret
	 */
	public boolean isSecret(TextInput input) {
		if (input.equals("search")) {
			this.isSecret = false;
			System.out.println("searched");
		}
		
		System.out.println(isSecret);
		return isSecret;
	}
	
	
	/**
	 * If player searches, secret exits become apparent.
	 */
	public void search() {
		this.isSecret = false;
	}
	
	/**
	 * Can the player open this door?
	 * @param player - the player object (and all other state)
	 * @return true if that is OK, false if they need something special.
	 */
	public boolean canOpen(Player player) {
		// DO NOT CHANGE THIS METHOD. ONLY OVERRIDE IN A SUBCLASS.
		return false;
	}

}
