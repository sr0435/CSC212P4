package edu.smith.cs.csc212.p4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This represents a place in our text adventure.
 * @author jfoley
 *
 */
public class Place {
	/**
	 * This is a list of places we can get to from this place.
	 */
	private List<Exit> exits;
	
	private List<Exit> totalExits;
	/**
	 * This is the identifier of the place.
	 */
	
	public List<String> items;
	
	public List<String> inventory;
	//private List<SecretExit> secretExits;
	
	private String id;
	/**
	 * What to tell the user about this place.
	 */
	private String description;
	/**
	 * Whether reaching this place ends the game.
	 */
	private boolean terminal;
	
	/**
	 * Internal only constructor for Place. Use {@link #create(String, String)} or {@link #terminal(String, String)} instead.
	 * @param id - the internal id of this place.
	 * @param description - the user-facing description of the place.
	 * @param terminal - whether this place ends the game.
	 */
	private Place(String id, String description, boolean terminal) {
		this.id = id;
		this.description = description;
		this.exits = new ArrayList<>();
		this.totalExits = new ArrayList<>();
		this.terminal = terminal;
		this.items = new ArrayList<>();
		this.inventory = new ArrayList<>();
	}
	
	/**
	 * Create an exit for the user to navigate to another Place.
	 * @param exit - the description and target of the other Place.
	 */
	public void addExit(Exit exit) {
		this.exits.add(exit);
		this.totalExits.add(exit);
	}
	
	public void addItem(String item) {
		this.items.add(item);
	}
	
	/*
	 * public void addSecretExit(SecretExit exit) { this.secretExits.add(exit); }
	 */
	
	/**
	 * For gameplay, whether this place ends the game.
	 * @return true if this is the end.
	 */
	public boolean isTerminalState() {
		return this.terminal;
	}
	
	/**
	 * The internal id of this place, for referring to it in {@link Exit} objects.
	 * @return the id.
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * The narrative description of this place.
	 * @return what we show to a player about this place.
	 */
	public String getDescription() {
		return this.description;
	}
	
	public void getItems() {
		for (Exit exit : (List<Exit>) exits) {
			String[] wordList = exit.getDescription().split(" ");
			for (String word : (String[]) wordList) {
				//for (String used : (List<String>) inventory) {
					//System.out.println(inventory);
					//if (word.contentEquals(used)==false) {
						for (String item : (List<String>) items) {
							if (word.equalsIgnoreCase(item)) {
								System.out.println("You obtained the: " + item);
								inventory.add(item);
								
								}
							}
						}
					}
				}
			//}
		//}
	
	public List<String> getInventory() {
		System.out.println("This is your inventory list: " + this.inventory);
		return this.inventory;
	}

	/**
	 * Get a view of the exits from this Place, for navigation.
	 * @return all the exits from this place.
	 */
	public List<Exit> getVisibleExits() {
		exits.removeAll(totalExits);
		exits.addAll(totalExits);
		for (Exit exit : totalExits) {
			//System.out.println("before");
			if (exit.isSecret() == true) {
				//System.out.println(exit);
				//Exit exit1 = (Exit) exit;
				exits.remove(exit);
				//SecretExit secExit = (SecretExit) exit;
				//secretExits.add(secExit);
			}
		}
		return Collections.unmodifiableList(exits);
	}
	
	public List<Exit> totalExits() {
		return totalExits;
	}

	
	/*
	 * public List<Exit> getInvisibleExits() { for (SecretExit secExit :
	 * (List<SecretExit>) secretExits) { if (secExit.isSecret() == true) { Exit exit
	 * = (Exit) secExit; exits.add(secExit); System.out.println("secret exit");
	 * //SecretExit secExit = (SecretExit) exit; //secretExits.add(secExit); } }
	 * return Collections.unmodifiableList(exits); }
	 */
	
	/**
	 * This is a terminal location (good or bad).
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is the description of the place.
	 * @return the Place object.
	 */
	public static Place terminal(String id, String description) {
		return new Place(id, description, true);
	}
	
	/**
	 * Create a place with an id and description.
	 * @param id - this is the id of the place (for creating {@link Exit} objects that go here).
	 * @param description - this is what we show to the user.
	 * @return the new Place object (add exits to it).
	 */
	public static Place create(String id, String description) {
		return new Place(id, description, false);
	}
	
	/**
	 * Implements what we need to put Place in a HashSet or HashMap.
	 */
	public int hashCode() {
		return this.id.hashCode();
	}
	
	/**
	 * Give a string for debugging what place is what.
	 */
	public String toString() {
		return "Place("+this.id+" with "+this.exits.size()+" exits.)";
	}
	
	/**
	 * Whether this is the same place as another.
	 */
	public boolean equals(Object other) {
		if (other instanceof Place) {
			return this.id.equals(((Place) other).id);
		}
		return false;
	}
	
}
