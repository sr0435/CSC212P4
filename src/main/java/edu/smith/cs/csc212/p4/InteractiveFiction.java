package edu.smith.cs.csc212.p4;

import java.util.ArrayList;
import java.util.List;

/**
 * This is our main class for P4. It interacts with a GameWorld and handles user-input.
 * @author jfoley
 *
 */
public class InteractiveFiction {

	/**
	 * This is where we play the game.
	 * @param args
	 */
	
	/*
	 * initializes the counter for time elapsed in the game
	 */
	static int hoursPassed = 0;
	
	
	/*
	 * the main method for the game
	 */
	public static void main(String[] args) {
		// This is a text input source (provides getUserWords() and confirm()).
		TextInput input = TextInput.fromArgs(args);

		// This is the game we're playing.
		//GameWorld game = new SpookyMansion();
		
		/*
		 * another version of the game
		 */
		GameWorld game = new Ratatouille();
		
		// gives the starting hour of the game
		GameTime time = new GameTime(12);
		
		//initializes the combinator
		// lets the player combine items they already have into new items
		FoodMaking combinator = new FoodMaking();
		// returns the list of combinable items and recipes
		combinator.cooking();

		// initializes the player's game inventory
		List<String> inventoryList = new ArrayList<>();
		
		// This is the current location of the player (initialize as start).
		String place = game.getStart();
		
		// Play the game until quitting.
		// This is too hard to express here, so we just use an infinite loop with breaks.
		while (true) {
			// Print the description of where you are.
			
			Place here = game.getPlace(place);
			
			// gives a different description if you've already obtained the room's items
			if (inventoryList.containsAll(here.items)) {
				here.printDescription();
			}
			
			// prints original description
			else {
				System.out.println(here.getDescription());
			}
			// prints the hour
			time.getHour();
			

			// Game over after print!
			if (here.isTerminalState()) {
				break;
			}

			// Show a user the ways out of this place.
			List<Exit> exits = here.getVisibleExits();
			List<Exit> totalExits = here.totalExits();
			
			for (int i=0; i<exits.size(); i++) {
			    Exit e = exits.get(i);
				System.out.println(" ["+i+"] " + e.getDescription());
			}

			// Figure out what the user wants to do, for now, only "quit" is special.
			List<String> words = input.getUserWords(">");
			if (words.size() == 0) {
				System.out.println("Must type something!");
				continue;
			} else if (words.size() > 1) {
				System.out.println("Only give me 1 word at a time!");
				continue;
			}
			
			// Get the word they typed as lowercase, and no spaces.
			String action = words.get(0).toLowerCase().trim();
			
			if (action.equals("escape") || action.equals("q")) {
				if (input.confirm("Are you sure you want to quit?")) {
					break;
				} else {
					continue;
				}
			}
			// goes through each exit and changes secret/locked exits to visible
			else if (action.equals("search")) {
				for (Exit exit : totalExits) {
					if (exit instanceof SecretExit) {
						exit.search();
					} else if (exit instanceof LockedExit) {
						// only opens a locked exit if the player has a key
							if (inventoryList.contains(((LockedExit) exit).key)==true) {
								System.out.println("You've unlocked a new way out!");
								exit.search();
							} 
						}
				}
				continue;
			}
			
			// lets the player take items from the places
			else if (action.equals("take")) {
				for (int i=0; i<here.items.size(); i++) {
					if (inventoryList.contains(here.items.get(i))==false) {
						inventoryList.add(here.items.get(i));
						System.out.println("You've added an item to your inventory!");
						continue;
					} else {
						System.out.println("You've taken all the items from here");
						continue;
					}
				
			}
				if (here.items.size() == 0) {
				System.out.println("There aren't any items here.");
				}
			}
			
			else if (action.equals("inventory")) {
				if (inventoryList.isEmpty()==true) {
					System.out.println("You have nothing in your inventory!");
				}
				else { 
					System.out.println("These are the items in your inventory!");
					System.out.println(inventoryList);
				}
				continue;
			}
			
			// doubles the amount of time passed
			else if (action.equals("rest")) {
				time.increaseHour();
				time.increaseHour();

				continue;
				
			}
			
			// lets the player combine two items into a new one
			else if (action.equals("combine")) {
				System.out.println("Choose two items from your inventory to combine\n" 
				+ "Then type them out, separated by spaces");
				//shows the inventory for the player to pick from
				System.out.println(inventoryList);
				// takes the player's choices
				List<String> comboChoice = input.getUserWords(">");
				try {
					// strings them together to check with the combo keys
				String comboProduct1 = comboChoice.get(0)+comboChoice.get(1).toLowerCase().trim();
				String comboProduct2 = comboChoice.get(1)+comboChoice.get(0).toLowerCase().trim();
				// if the one of the strings are in combo map, then it adds the value of that key to the inventory
				if (combinator.combos.containsKey(comboProduct1) || combinator.combos.containsKey(comboProduct2)) {
					String newCreation1 = combinator.combos.get(comboProduct1);
					inventoryList.add(newCreation1);
					inventoryList.remove(comboChoice.get(0));
					inventoryList.remove(comboChoice.get(1));
					System.out.println("You've added an item to your inventory!");
				} 
					
				else {
					System.out.println("These two items can't be combined");
				}
			}
				// makes sure the player only chooses two objects
				catch (IndexOutOfBoundsException ioob) {
					System.out.println("You must have/can only use two objects");
				}
				continue;
			}
			
			// From here on out, what they typed better be a number!
			Integer exitNum = null;
			try {
				exitNum = Integer.parseInt(action);
			} catch (NumberFormatException nfe) {
				if (action.equals("take")==false) {
					System.out.println("That's not something I understand! Try a number!");
				}
				
				continue;
			} 
			
			if (exitNum < 0 || exitNum >= exits.size()) {
				System.out.println("I don't know what to do with that number!");
				continue;
			}
			
			// Move to the room they indicated.
			Exit destination = exits.get(exitNum);
			// increases the time passed when the player moves, not when they do anything else
			time.increaseHour();
			hoursPassed +=1;

			place = destination.getTarget();
		}

		// You get here by "quit" or by reaching a Terminal Place.
		System.out.println(">>> GAME OVER <<<");
		// says how many hours passed
		System.out.println("You've spent: " + hoursPassed + " hours in the game...");
	}
	
	}
