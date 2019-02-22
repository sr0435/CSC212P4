package edu.smith.cs.csc212.p4;

import java.util.HashMap;
import java.util.Map;

// the class that all the minigames inherit from
public class FoodMaking {
	
	/*
	 * initializes the combos map
	 */
	Map<String,String> combos;
	
	/*
	 * constructs the combos map
	 */
	public FoodMaking() {
		this.combos = new HashMap<>();
	}

	/*
	 * list of all the available combos in the games
	 */
	public Map<String,String> cooking() {
		combos.put("bouillabaissebaguette", "Meal");
		combos.put("baguettebouillabaisse", "Meal");

		combos.put("eraserpencil", "eraserPencil");
		combos.put("pencileraser", "eraserPencil");

		combos.put("sugarvanilla", "flavorings");
		combos.put("vanillasugar", "flavorings");
		combos.put("butterflavorings", "wetingredients");
		combos.put("flavoringsbutter", "wetingredients");
		combos.put("flourwetingredients", "batter");
		combos.put("wetingredientsflour", "batter");
		combos.put("batterchocolatechips", "chocolateChipCookie");
		combos.put("chocolatechipsbatter", "chocolateChipCookie");
		return combos;
		
	}
}
