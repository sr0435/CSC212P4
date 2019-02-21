package edu.smith.cs.csc212.p4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	static int hoursPassed = 0;
	
	public static void main(String[] args) {
		// This is a text input source (provides getUserWords() and confirm()).
		TextInput input = TextInput.fromArgs(args);

		// This is the game we're playing.
		GameWorld game = new SpookyMansion();
		//GameWorld game = new Ratatouille();
		
		GameTime time = new GameTime(12);
		
		FoodMaking combinator = new FoodMaking();
		combinator.cooking();
		System.out.println("yeet");
		combinator.cooking();
		System.out.println(combinator.combos);
		System.out.println("nyeet");
		//GameTime time;
		
		//GameTime hour = 3;
		
		// This is the current location of the player (initialize as start).
		// Maybe we'll expand this to a Player object.
		List<String> inventoryList = new ArrayList<>();
		String place = game.getStart();
		

		//time.increaseHour();
		//time.hour = 12;
		
		//Map<String,String> combos = new HashMap<>();

		// Play the game until quitting.
		// This is too hard to express here, so we just use an infinite loop with breaks.
		while (true) {
			// Print the description of where you are.
			
			Place here = game.getPlace(place);
			
			if (inventoryList.containsAll(here.items)) {
				here.printDescription();
			}

			else {
				System.out.println(here.getDescription());
			}
			time.getHour();
			

			// Game over after print!
			if (here.isTerminalState()) {
				break;
			}

			// Show a user the ways out of this place.
			List<Exit> exits = here.getVisibleExits();
			List<Exit> totalExits = here.totalExits();
			//List<String> inventory = ;
			
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
			
			else if (action.equals("search")) {
				for (Exit exit : totalExits) {
					if (exit instanceof SecretExit) {
						exit.search();
					} else if (exit instanceof LockedExit) {
							if (inventoryList.contains(((LockedExit) exit).key)==true) {
								System.out.println("You've unlocked a new way out!");
								exit.search();
							} 
						}
						//System.out.println("Nothing to find here!\n" + "...yet");
				}
				continue;
			}
			
			else if (action.equals("take")) {
				//here.getItems();
				for (int i=0; i<here.items.size(); i++) {
					//if (inventoryList.contains(here.items)==false) {
					if (inventoryList.contains(here.items.get(i))==false) {
						inventoryList.add(here.items.get(i));
						System.out.println("You've added an item to your inventory!");
						continue;
						//action.contentEquals("inventory");
					} else {
						System.out.println("You've taken all the items from here");
						continue;
					}
				//}
				
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
			
			else if (action.equals("rest")) {
				time.increaseHour();
				time.increaseHour();

				continue;
				
			}
			
			else if (action.equals("combine")) {
				System.out.println("Choose two items from your inventory to combine\n" 
				+ "Then type them out, separated by spaces");
				System.out.println(inventoryList);
				List<String> comboChoice = input.getUserWords(">");
				try {
				String comboProduct1 = comboChoice.get(0)+comboChoice.get(1);
				String comboProduct2 = comboChoice.get(1)+comboChoice.get(0);
				if (combinator.combos.containsKey(comboProduct1)) {
					String newCreation1 = combinator.combos.get(comboProduct1);
					inventoryList.add(newCreation1);
					inventoryList.remove(comboChoice.get(0));
					inventoryList.remove(comboChoice.get(1));
					System.out.println("You've added an item to your inventory!");
				} else if (combinator.combos.containsKey(comboProduct2)) {
					String newCreation2 = combinator.combos.get(comboProduct2);
					inventoryList.add(newCreation2);
					inventoryList.remove(comboChoice.get(0));
					inventoryList.remove(comboChoice.get(1));
					System.out.println("You've added an item to your inventory!");
					
				} else {
					System.out.println("These two items can't be combined");
				}
			}
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
			time.increaseHour();
			hoursPassed +=1;

			place = destination.getTarget();
		}

		// You get here by "quit" or by reaching a Terminal Place.
		System.out.println(">>> GAME OVER <<<");
		System.out.println("You've spent: " + hoursPassed + " hours in the game...");
	}
	
	}
