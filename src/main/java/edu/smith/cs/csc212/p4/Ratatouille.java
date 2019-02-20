package edu.smith.cs.csc212.p4;

import java.util.HashMap;
import java.util.Map;

public class Ratatouille implements GameWorld{

	private Map<String, Place> places = new HashMap<>();
	/**
	 * Where should the player start?
	 */
	@Override
	public String getStart() {
		return "gusteauFoyer";
	}

	/**
	 * This constructor builds our SpookyMansion game.
	 */
	public Ratatouille() {
		Place gusteauFoyer = insert(
				Place.noItem("gusteauFoyer", "Your first day at your new job.\n"
						+ "You're working at one of the best restaurants in Paris, Gusteau's. \n"
						+ "What could go wrong?"));
		gusteauFoyer.addExit(new Exit("diningRoom", "The dining room is through the hallway."));
		gusteauFoyer.addExit(new Exit("coatCheck", "The little closet is the coat check."));
		
		Place diningRoom = insert(
				Place.noItem("diningRoom", "This is the dining room. It's nice and cozy.\n" 
						+ "Everything is so elegant.\n" 
						+ "But you won't be working here."
						));
		diningRoom.addExit(new Exit("gusteauFoyer", "Go back through the hallway to the foyer."));
		diningRoom.addExit(new Exit("coatCheck", "This leads to the coat check closet."));
		diningRoom.addExit(new Exit("kitchen", "Double doors leading to the kitchen."));
		
		Place coatCheck = insert(
				Place.noItem("coatCheck", "This is the coat check closet. It's rather snug inside.\n" 
				+ "It's so tight in here that you can't get out!"));
		/*
		 * coatCheck.addExit(new Exit("gusteauFoyer",
		 * "Go back through the hallway to the foyer.")); coatCheck.addExit(new
		 * Exit("diningRoom", "This leads to the dining room."));
		 */
		
		Place kitchen = insert(
				Place.noItem("kitchen", "This is the kitchen. Maybe you'll get to meet Remy here"
				));
		kitchen.addExit(new Exit("diningRoom", "These doors lead to the dining room"));
		kitchen.addExit(new Exit("soupStation", "This is the kitchen's soup station"));

		Place soupStation = insert(Place.noItem("soupStation",
				 "This is where you'll be making soup.\n" +
				 "Mmmm Bouillabaisse...")); 
		soupStation.addExit(new Exit("kitchen", "Back to the main area of the kitchen")); 
		soupStation.addExit(new Exit("breadStation", "This is the kitchen's bread oven"));
		
		Place breadStation = insert(Place.noItem("breadStation",
				 "This is where all the bread gets baked...\n" +
				 "It's nice and warm in here.")); 
		breadStation.addExit(new Exit("kitchen", "Back to the main area of the kitchen")); 
		breadStation.addExit(new Exit("soupStation", "This is the kitchen's soup station"));
		breadStation.addExit(new Exit("pantry", "This door leads to the pantry and cooler"));
		
		Place pantry = insert(Place.noItem("pantry",
				 "This is where all the food for Gusteau's gets stored.\n" +
				 "There's so much food here...")); 
		pantry.addExit(new Exit("kitchen", "Back to the main area of the kitchen")); 
		pantry.addExit(new Exit("soupStation", "This is the kitchen's soup station"));
		pantry.addExit(new Exit("pantrySection0", "This door leads to the pantry and cooler"));
		
		 int pantryDepth = 3; 
		 int lastPantrySection = pantryDepth - 1; 
		 for (int i=0; i<pantryDepth; i++) { 
			 Place pantrySection = insert(Place.noItem("pantrySection"+i,
				  "This is a very long pantry. You're on like shelf " + (i+1)*10 +
				  " now")); 
			 if (i == 0) { 
				 pantrySection.addExit(new Exit("kitchen", "Go back.")); 
			 } else { 
				 pantrySection.addExit(new Exit("pantrySection"+(i-1), "Go back.")); 
			 } if (i != lastPantrySection) {
				pantrySection.addExit(new Exit("pantrySection"+(i+1), "Go forward.")); 
			 } else { 
				 pantrySection.addExit(new Exit("cooler", "It's starting to feel a bit chilly")); } }
				  
		Place cooler = insert(Place.terminal("cooler", "You have found the cooler.\n"
				 + "The refrigerator door has closed behind you.\n"+
				 "It's so cold in here\n" + "Your freezing hands can't unlock the latch.\n"
				 + "You're trapped..."));

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
