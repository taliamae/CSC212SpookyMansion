package edu.smith.cs.csc212.spooky;

import java.util.List;

/**
 * This is our main class for SpookyMansion.
 * It interacts with a GameWorld and handles user-input.
 * It can play any game, really.
 *
 * @author jfoley
 *
 */
public class InteractiveFiction {

	/**
	 * This method actually plays the game.
	 * @param input - a helper object to ask the user questions.
	 * @param game - the places and exits that make up the game we're playing.
	 * @return where - the place the player finished.
	 */
	static String runGame(TextInput input, GameWorld game) {
		// This is the current location of the player (initialize as start).
		Player player = new Player(game.getStart());

		// Play the game until quitting.
		// This is too hard to express here, so we just use an infinite loop with breaks.
		GameTime hour = new GameTime();
		
		while (true) {
			// Print the description of where you are.
			Place here = game.getPlace(player.getPlace());
			
			System.out.println();
			System.out.println("... --- ...");
			System.out.println("Hour: " + hour.getMilTime() + 
					"\nTime Passed: " + hour.getTotalTime());
			System.out.println(here.getDescription());
			
			
			if (player.beenHere()) {
				System.out.println("This place feels familiar...");
			}

			// Game over after print!
			if (here.isTerminalState()) {
				System.out.println("You spent a total of " + hour.getTotalTime() + " hours in the game.");
				break;
			}

			// Show a user the ways out of this place. Only prints non-secret ones.
			List<Exit> exits = here.getVisibleExits();

			for (int i=0; i<exits.size(); i++) {
				Exit e = exits.get(i);
				if (!e.isSecret()) {
					System.out.println(" "+i+". " + e.getDescription());
				}
			}

			// Figure out what the user wants to do
			List<String> words = input.getUserWords("?");
			if (words.size() > 1) {
				System.out.println("Only give the system 1 word at a time!");
				continue;
			}

			// Get the word they typed as lowercase, and no spaces.
			// Do not uppercase action -- I have lowercased it.
			String action = words.get(0).toLowerCase().trim();
			if (action.equals("quit") || action.equals("q") || action.equals("escape")) {
				if (input.confirm("Are you sure you want to quit?")) {
					// quit!
					System.out.println("You spent a total of " + hour.getTotalTime() + " hours in the game.");
					break;
				} else {
					// go to the top of the game loop!
					continue;
				}
			}
			// Displays possible actions
			if (action.equals("help")) {
				System.out.println("Type and enter a number "
						+ "that corresponds with provided options. "
						+ "Type 'quit,' 'q,' or 'escape' to quit."
						+ "Type 'take' to take an item, 'search' to search for secret exits,"
						+ " or 'stuff' to view your inventory.");
				continue;
			}
			
			if (action.equals("search")) {
				here.search();
				hour.incHour();
				continue;
			}
			
			//Prints player's inventory
			if (action.equals("stuff")) {
				if (player.keys.size() > 0) {
					System.out.println("Here is your stuff: " + player.keys);
				} else {
					System.out.println("You have no items.");
				}
				continue;
				
			}
			
			// Advances time by 2 hours
			if (action.equals("rest")) {
				hour.rest();
			}
			
			// Player takes all items in room
			if (action.equals("take")) {
				for (String item : here.items) {
					player.addStuff(item);
				}
				here.items.clear();
				continue;
			}

			// From here on out, what they typed better be a number!
			Integer exitNum = null;
			try {
				exitNum = Integer.parseInt(action);
			} catch (NumberFormatException nfe) {
				System.out.println("That's not something I understand! Try a number!");
				continue;
			}

			if (exitNum < 0 || exitNum >= exits.size()) {
				System.out.println("I don't know what to do with that number!");
				continue;
			}

			// Move to the room they indicated.
			Exit destination = exits.get(exitNum);
			// Increment time by one hour each time player moves
			hour.incHour();
			if (destination.canOpen(player)) {
				player.moveTo(destination.getTarget());
			} 
			else if (player.keys.contains("magic key")) {
				player.moveTo(destination.getTarget());
			}
				else {
				System.out.println("The door is locked. Come back later when you have more items.");
			} 
			
		}

		return player.getPlace();
		
		
	}

	/**
	 * This is where we play the game.
	 * @param args
	 */
	public static void main(String[] args) {
		// This is a text input source (provides getUserWords() and confirm()).
		TextInput input = TextInput.fromArgs(args);

		// This is the game we're playing.
		//GameWorld game = new SpookyMansion();
		GameWorld game = new Hogwarts();

		// Actually play the game.
		runGame(input, game);

		// You get here by typing "quit" or by reaching a Terminal Place.
		System.out.println("\n\n>>> GAME OVER <<<");
		
	}

}
