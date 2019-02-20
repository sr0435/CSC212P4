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

	List<String> inventory;
	
	//this.inventory = inventory;
	/**
	 * This is where we play the game.
	 * @param args
	 */
	
		
	public static void main(String[] args) {
		// This is a text input source (provides getUserWords() and confirm()).
		TextInput input = TextInput.fromArgs(args);

		// This is the game we're playing.
		GameWorld game = new SpookyMansion();
		//GameWorld game = new Ratatouille();
		
		//GameTime time;
		
		//GameTime hour = 3;
		
		// This is the current location of the player (initialize as start).
		// Maybe we'll expand this to a Player object.
		List<String> player = new ArrayList<>();
		String place = game.getStart();
		
		

		// Play the game until quitting.
		// This is too hard to express here, so we just use an infinite loop with breaks.
		while (true) {
			// Print the description of where you are.
			Place here = game.getPlace(place);
			
			if (here.items.isEmpty()) {
				System.out.println(here.getDescription());
				System.out.println(player);
			}
			else {
			for (int i=0; i<here.items.size(); i++) {
				if (player.contains(here.items.get(i))) {
					here.printDescription();
				}
				else {
					System.out.println(here.getDescription());
					System.out.println(player);
				}
			}
			}
			
			
			
			

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
					if (exit instanceof Exit || exit instanceof SecretExit) {
						exit.search();
					} else {
						if (exit instanceof LockedExit) {
							//if (player.contains(here.key)==true)
						}
					}
					
				}
				continue;
			}
			
			else if (action.equals("take")) {
				//here.getItems();
				for (int i=0; i<here.items.size(); i++) {
					if (player.contains(here.items)==false) {
					//if (player.contains(here.items.get(i))==false) {
						player.add(here.items.get(i));
					}
				}
				System.out.println(player);
				continue;
			}
			
			else if (action.equals("inventory")) {
				if (player.isEmpty()==true) {
					System.out.println("You have nothing in your inventory!");
				}
				else { System.out.println(player);
				}
				continue;
			}
			
			else if (action.equals("rest")) {
				//time.getHour();
				
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
			place = destination.getTarget();
		}

		// You get here by "quit" or by reaching a Terminal Place.
		System.out.println(">>> GAME OVER <<<");
	}
	
	}
