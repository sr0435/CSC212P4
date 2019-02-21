package edu.smith.cs.csc212.p4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// the class that all the minigames inherit from
public class FoodMaking {
	
	
	Map<String,String> combos;
	
	
	public FoodMaking() {
		this.combos = new HashMap<>();
	}
	
	public Map<String,String> cooking() {
		//combos.put("bouillabaissebaguette", "Meal");
		combos.put("eraserpencil", "eraserPencil");
		combos.put("pencileraser","eraserPencil");
		return combos;
		
	}

	
	
	
	
	/*
	 * public static void main(String[] args) { String[] ingredients = new
	 * String[20]; // This is a text input source (provides getUserWords() and
	 * confirm()). TextInput input = TextInput.fromArgs(args);
	 * 
	 * List<String> words = input.getUserWords(">"); if (words.size() == 0) {
	 * System.out.println("Must type something!"); //continue; } else if
	 * (words.size() > 1) { System.out.println("Only give me 1 word at a time!");
	 * //continue; }
	 * 
	 * // Get the word they typed as lowercase, and no spaces. String action =
	 * words.get(0).toLowerCase().trim();
	 * 
	 * if (action.equals(ingredients[0])) {
	 * 
	 * }
	 */
}
