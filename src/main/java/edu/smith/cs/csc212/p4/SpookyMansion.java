package edu.smith.cs.csc212.p4;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * SpookyMansion, the game.
 * @author jfoley
 *
 */
public class SpookyMansion implements GameWorld {
	private Map<String, Place> places = new HashMap<>();
	public Map<String,List<String>> player = new HashMap<>();
	//private List<String> inventoryList = new ArrayList<>();
	//private List<String> items = new ArrayList<>();
	
	/**
	 * Where should the player start?
	 */
	@Override
	
	public String getStart() { 
		return "entranceHall"; 
		}
	 

	/**
	 * This constructor builds our SpookyMansion game.
	 */
	
	
	public SpookyMansion() {
		
		
		Place entranceHall = insert(
				Place.noItem("entranceHall", "You are in the grand entrance hall of a large building.\n"
						+ "The front door is locked. How did you get here?"));
		entranceHall.addExit(new Exit("basement", "There are stairs leading down"));
		entranceHall.addExit(new Exit("attic", "There are stairs leading up."));
		entranceHall.addExit(new Exit("kitchen", "There is a red door."));
		
		
		//entranceHall.addExit(new Exit("rock", "This is a rock."));
		
		
		Place basement = insert(
				Place.create("basement", "You have found the basement of the mansion. There's a cute stuffed bear in the corner.\n" + 
		                           "It is darker down here.\n" +
						"You get the sense a secret is nearby, but you only see the stairs you came from.",
						"You have found the basement of the mansion.\n" + 
                        "It is darker down here.\n" +
				"You get the sense a secret is nearby, but you only see the stairs you came from."
				));
		basement.addExit(new Exit("entranceHall", "There are stairs leading up."));
		basement.addExit(new SecretExit("cellar", "This is the secret cellar."));
		basement.addExit(new Exit("trampolineRoom", "For some reason there's a trampoline and a giant hole in the ceiling"));
		basement.addItem("bear");
		
		Place attic = insert(Place.create("attic",
				"Something rustles in the rafters as you enter the attic. Creepy.\n"
				+ "You tripped over a pencil. \n"
				+ "It's big up here.", 
				"Something rustles in the rafters as you enter the attic. Creepy.\n" + "It's big up here."));
		attic.addExit(new Exit("entranceHall", "There are stairs leading down."));
		attic.addExit(new Exit("attic2", "There is more through an archway"));
		attic.addItem("pencil");
		
		Place attic2 = insert(Place.noItem("attic2", "There's definitely a bat in here somewhere.\n"
				+ "This part of the attic is brighter, so maybe you're safe here."));
		attic2.addExit(new Exit("attic", "There is more back through the archway"));
		attic2.addExit(new Exit("trampolineRoom", "There is a hole that leads to the trampoline room."));

		Place cellar = insert(Place.noItem("cellar", "There are a lot of pickles in this cellar"));
		cellar.addExit(new SecretExit("basement", "This is the basement"));
		
		Place trampolineRoom = insert(Place.noItem("trampolineRoom", "A trampoline room... Odd flex, but ok..."));
		trampolineRoom.addExit(new Exit("attic2", "This is the attic"));
		
		Place kitchen = insert(Place.noItem("kitchen", "You've found the kitchen. You smell old food and some kind of animal."));
		kitchen.addExit(new Exit("entranceHall", "There is a red door."));
		kitchen.addExit(new Exit("dumbwaiter", "There is a dumbwaiter."));
		
		Place dumbwaiter = insert(Place.noItem("dumbwaiter", "You crawl into the dumbwaiter. What are you doing?"));
		dumbwaiter.addExit(new Exit("secretRoom", "Take it to the bottom."));
		dumbwaiter.addExit(new Exit("kitchen", "Take it to the middle-level."));
		dumbwaiter.addExit(new Exit("attic2", "Take it up to the top."));
		
		Place secretRoom = insert(Place.noItem("secretRoom", "You have found the secret room."));
		secretRoom.addExit(new Exit("hallway0", "There is a long hallway."));
		secretRoom.addExit(new Exit("basement", "You have found the basement of the mansion, but you don't know how you got here"));
		
		int hallwayDepth = 5;
		int lastHallwayPart = hallwayDepth - 1;
		for (int i=0; i<hallwayDepth; i++) {
			Place hallwayPart = insert(Place.noItem("hallway"+i, "This is a very long hallway. The number " + (i+1) + " is scratched on the wall"));
			if (i == 0) {
				hallwayPart.addExit(new Exit("secretRoom", "Go back."));
			} else {
				hallwayPart.addExit(new Exit("hallway"+(i-1), "Go back."));
			}
			if (i != lastHallwayPart) {
				hallwayPart.addExit(new Exit("hallway"+(i+1), "Go forward."));
			} else {
				hallwayPart.addExit(new Exit("crypt", "There is darkness ahead."));
			}
		}
		
		Place crypt = insert(Place.terminal("crypt", "You have found the crypt.\n"
				+"It is scary here, but there is an exit to outside.\n"+
				"Maybe you'll be safe out there."));
		
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
		
		// Now that we've checked every exit for every place, crash if we printed any errors.
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
