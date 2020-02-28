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
		greatHall.addExit(new Exit("grandStaircase", "Exit out of the hallway and cross stone bridge."));
		greatHall.addExit(new Exit("caretakerOffice", "There is a brown door with scratches on it. "
				+ "You hear mumbling coming from inside."));
		
		Place caretakerOffice = insert(Place.create("caretakerOffice", "A skinny old white man with a scraggly beard yells at you.\n"
				+ "You think to yourself, you probably shouldn't be in here."));
		caretakerOffice.addExit(new Exit("greatHall", "Go back."));

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
		gargoyle.addExit(new LockedExit("dumbleTower", "Inspect.", "magic key"));
		
		Place dumbleTower = insert(Place.terminal("dumbleTower", "The gargoyle clicks and spins into the ground. "
				+ "A new passage opens up. You enter a magnificent office. \n" +
		"You hear a deep and slow voice emerge from the back of the room.\n" +
				"Congratulations, Harry. You have found your way."));
		
		
		Place defenseTower = insert(
				Place.create("defenseTower", "You come upon a tall tower with seemingly dark energy."));
		defenseTower.addItem("Broken wand");
		defenseTower.addExit(new Exit("grandStaircase", "Go to grand staircase."));
		defenseTower.addExit(new Exit("hospitalWing", "Exit out back door."));
		
		Place middleCourtyard = insert(Place.create("middleCourtyard", 
				"You walk to the middle of a big, open field in the middle of some buildings."));
		middleCourtyard.addExit(new Exit("grandStaircase", "Cross the stone bridge."));
		middleCourtyard.addExit(new DayExit("greenhouses", "Walk through the courtyard."));
		middleCourtyard.addExit(new Exit("trainingGrounds", "Walk around the grass."));
		middleCourtyard.addExit(new Exit("hospitalWing", "Enter short building."));

		Place hospitalWing = insert(Place.create("hospitalWing",
				"There are cots laid out all over the room, some with healing patients. " + "It smells musty." 
				+ " \nA woman named Madame Pomfrey stands at attention."));
		hospitalWing.addExit(new Exit("defenseTower", "There are stairs leading up."));
		hospitalWing.addExit(new Exit("middleCourtyard", "Go back through the archway."));
		hospitalWing.addExit(new Exit("clockTower", "There is a back door leading to a staircase."));
		hospitalWing.addExit(new NightExit("dungeons", "There's some stairs leading down to a dark hall."));
		
		Place dungeons = insert(Place.create("dungeons", "There's a dark hall with doors leading out, but all of them are locked."));
		dungeons.addItem("broom");
		dungeons.addExit(new Exit("hospitalWing", "Go back."));
		
		Place clockTower = insert(Place.create("clockTower", "You find yourself at the top of tall building, "
				+ "staring at a huge clock."));
		clockTower.addExit(new Exit("hospitalWing", "Go back down the stairs."));

		Place trainingGrounds = insert(Place.create("trainingGrounds", "You find yourself in a open grass field surroundd with bleachers. \n"
				+ "There are hoops scattered in various areas."));
		trainingGrounds.addExit(new Exit("greenhouses", "There is a shaded walkway."));
		trainingGrounds.addExit(new Exit("middleCourtyard", "Walk around the grass."));
		trainingGrounds.addExit(new SecretExit("hagridHut", "There is a path leading down a long hill."));
		
		Place hagridHut = insert(Place.create("hagridHut", "You walk for a while, eventually stumbling upon a small straw hut.\n"
				+ "You hear a variety of weird, slightly unsettling noises."));
		hagridHut.addExit(new Exit("trainingGrounds", "Go back."));
		hagridHut.addItem("magic key");
		
		Place greenhouses = insert(Place.create("greenhouses", "You are in a warm glass building filled with lots of plants."));
		greenhouses.addExit(new Exit("trainingGrounds", "Exit through shaded walkway."));
		greenhouses.addExit(new Exit("middleCourtyard", "Exit through the front door."));
		
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




