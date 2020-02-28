package edu.smith.cs.csc212.spooky;


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
	public boolean isSecret() {
		return this.isSecret;
	}
	
	
	/**
	 * If player searches, secret exits become apparent.
	 */
	public void search() {
		this.isSecret = false;
	}
	

}
