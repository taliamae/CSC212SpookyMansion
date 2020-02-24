package edu.smith.cs.csc212.spooky;

import java.util.HashMap;
import java.util.Map;

// Map loosely based off of a drawing from https://www.deviantart.com/leethree9/art/Map-of-Hogwarts-draft-I-209067160
// This game takes a LOT of creative license and is not true to the canonical series.


public class Hogwarts implements GameWorld {
	private Map<String, Place> places = new HashMap<>();
	

	/**
	 * Where should the player start?
	 */
	@Override
	public String getStart() {
		return "greatHall";
	}

	/**
	 * This constructor builds the Hogwarts game.
	 */
	public Hogwarts() {
		Place greatHall = insert(
				Place.create("greatHall", "You find yourself in a beautiful cafeteria, finishing a meal among your friends.\n"
						+ "You finish your meal. What's your next step?"));
		greatHall.addExit(new Exit("grandStaircase", "Exit out of the hallway."));
		greatHall.addExit(new Exit("caretakerOffice", "There is a brown door with scratches on it. "
				+ "You hear mumbling coming from inside."));

		Place grandStaircase = insert(Place.create("grandStaircase", "You find yourself standing in front of a series of moving stairs.\n"
				+ "To your left you see a gargoyle.\n" 
				+ "What could it mean?"));
		grandStaircase.addExit(new Exit("greatHall", "Go back."));
		grandStaircase.addExit(new Exit("gargoyle", "Inspect the gargoyle."));
		grandStaircase.addExit(new Exit("middleCourtyard", "Go down the stairs."));
		grandStaircase.addExit(new Exit("defenseTower", "Go up the stairs."));
		

		Place gargoyle = insert(
				Place.create("gargoyle", "You inspect the gargoyle. Something about it catches your eye,\n"
						+ "but for now it just stares into your soul."));
		gargoyle.addExit(new Exit("grandStaircase", "Go back."));
		gargoyle.addSecretExit(new SecretExit("dumbleTower", "The gargoyle clicks and spins into the ground. A new passage opens up."));
		
		Place dumbleTower = insert(Place.terminal("dumbleTower", "You enter a magnificent office. \n" +
		"You hear a deep and slow voice emerge from the back of the room.\n" +
				"Congratulations, Harry. You have found your way."));
		
		
		Place defenseTower = insert(
				Place.create("defenseTower", "I don't know what you were thinking..."));
		defenseTower.addExit(new Exit("grandStaircase", "Keep falling."));
		defenseTower.addExit(new Exit("hospitalWing", "Keep falling."));
		
		Place middleCourtyard = insert(Place.create("middleCourtyard", 
				"You walk to the middle of a big, open field in the middle of some buildings."));
		middleCourtyard.addExit(new Exit("grandStaircase", "Cross the stone bridge."));
		middleCourtyard.addExit(new Exit("greenhouses", "Walk through the courtyard."));
		middleCourtyard.addExit(new Exit("trainingGrounds", "Walk around the grass."));
		middleCourtyard.addExit(new Exit("hospitalWing", "Enter short building."));

		Place hospitalWing = insert(Place.create("hospitalWing",
				"There are cots laid out all over the room." + "It smells musty."));
		hospitalWing.addExit(new Exit("defenseTower", "There are stairs leading up."));
		hospitalWing.addExit(new Exit("middleCourtyard", "Go back through the archway."));
		hospitalWing.addExit(new Exit("clockTower", "There is a back door leading to a staircase."));
		
		Place clockTower = insert(Place.create("clockTower", "You find yourself at the top of tall building, "
				+ "staring at a huge clock."));
		clockTower.addExit(new Exit("hospitalWing", "Go back down the stairs."));

		Place trainingGrounds = insert(Place.create("trainingGrounds", "You find yourself in a huge open field. \n"
				+ "There are hoops scattered in various areas."));
		trainingGrounds.addExit(new Exit("greenhouses", "There is a shaded walkway."));
		trainingGrounds.addExit(new Exit("middleCourtyard", "Walk around the grass."));
		
		Place greenhouses = insert(Place.create("greenhouses", "You are in a warm glass building filled with lots of plants."));
		greenhouses.addExit(new Exit("traningGrounds", "Exit through shaded walkway."));
		greenhouses.addExit(new Exit("middlecourtyard", "Exit through the front door."));
		
		Place kitchen = insert(
				Place.create("kitchen", "You're in a kitchen with lots of working elves. Congratulations."));
		kitchen.addExit(new Exit("greatHall", "Go back."));

		// Make sure your graph makes sense!
		checkAllExitsGoSomewhere();
	}

	/**
	 * This helper method saves us a lot of typing. We always want to map from p.id
	 * to p.
	 * 
	 * @param p - the place.
	 * @return the place you gave us, so that you can store it in a variable.
	 */
	private Place insert(Place p) {
		places.put(p.getId(), p);
		return p;
	}

	/**
	 * I like this method for checking to make sure that my graph makes sense!
	 */
	private void checkAllExitsGoSomewhere() {
		boolean missing = false;
		// For every place:
		for (Place p : places.values()) {
			// For every exit from that place:
			for (Exit x : p.getVisibleExits()) {
				// That exit goes to somewhere that exists!
				if (!places.containsKey(x.getTarget())) {
					// Don't leave immediately, but check everything all at once.
					missing = true;
					// Print every exit with a missing place:
					System.err.println("Found exit pointing at " + x.getTarget() + " which does not exist as a place.");
				}
			}
		}

		// Now that we've checked every exit for every place, crash if we printed any
		// errors.
		if (missing) {
			throw new RuntimeException("You have some exits to nowhere!");
		}
	}

	/**
	 * Get a Place object by name.
	 */
	public Place getPlace(String id) {
		return this.places.get(id);
	}
}




