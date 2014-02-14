package se.kth.csc.iprog.dinnerplanner.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DinnerModel implements IDinnerModel {

	Set<Dish> dishes = new HashSet<Dish>();
	ArrayList<Dish> selectedDishes;
	int _numberOfGuests = 0;

	/**
	 * TODO: For Lab2 you need to implement the IDinnerModel interface. When you do this you will have all the needed
	 * fields and methods for the dinner planner (number of guests, selected dishes, etc.).
	 */

	/**
	 * The constructor of the overall model. Set the default values here
	 */
	public DinnerModel() {

		// Adding some example data, you can add more
		Dish dish1 = new Dish(
				"French toast",
				Dish.STARTER,
				"toast.jpg",
				"In a large mixing bowl, beat the eggs. Add the milk, brown sugar and nutmeg; stir well to combine. Soak bread slices in the egg mixture until saturated. Heat a lightly oiled griddle or frying pan over medium high heat. Brown slices on both sides, sprinkle with cinnamon and serve hot.");
		Ingredient dish1ing1 = new Ingredient("eggs", 0.5, "", 1);
		Ingredient dish1ing2 = new Ingredient("milk", 30, "ml", 6);
		Ingredient dish1ing3 = new Ingredient("brown sugar", 7, "g", 1);
		Ingredient dish1ing4 = new Ingredient("ground nutmeg", 0.5, "g", 12);
		Ingredient dish1ing5 = new Ingredient("white bread", 2, "slices", 2);
		dish1.addIngredient(dish1ing1);
		dish1.addIngredient(dish1ing2);
		dish1.addIngredient(dish1ing3);
		dish1.addIngredient(dish1ing4);
		dish1.addIngredient(dish1ing5);
		dishes.add(dish1);

		Dish dish2 = new Dish(
				"Meat balls",
				Dish.MAIN,
				"meatballs.jpg",
				"Preheat an oven to 400 degrees F (200 degrees C). Place the beef into a mixing bowl, and season with salt, onion, garlic salt, Italian seasoning, oregano, red pepper flakes, hot pepper sauce, and Worcestershire sauce; mix well. Add the milk, Parmesan cheese, and bread crumbs. Mix until evenly blended, then form into 1 1/2-inch meatballs, and place onto a baking sheet. Bake in the preheated oven until no longer pink in the center, 20 to 25 minutes.");
		Ingredient dish2ing1 = new Ingredient("extra lean ground beef", 115,
				"g", 20);
		Ingredient dish2ing2 = new Ingredient("sea salt", 0.7, "g", 3);
		Ingredient dish2ing3 = new Ingredient("small onion, diced", 0.25, "", 2);
		Ingredient dish2ing4 = new Ingredient("garlic salt", 0.6, "g", 3);
		Ingredient dish2ing5 = new Ingredient("Italian seasoning", 0.3, "g", 3);
		Ingredient dish2ing6 = new Ingredient("dried oregano", 0.3, "g", 3);
		Ingredient dish2ing7 = new Ingredient("crushed red pepper flakes", 0.6,
				"g", 3);
		Ingredient dish2ing8 = new Ingredient("Worcestershire sauce", 16, "ml",
				7);
		Ingredient dish2ing9 = new Ingredient("milk", 20, "ml", 4);
		Ingredient dish2ing10 = new Ingredient("grated Parmesan cheese", 5,
				"g", 8);
		Ingredient dish2ing11 = new Ingredient("seasoned bread crumbs", 115,
				"g", 4);
		dish2.addIngredient(dish2ing1);
		dish2.addIngredient(dish2ing2);
		dish2.addIngredient(dish2ing3);
		dish2.addIngredient(dish2ing4);
		dish2.addIngredient(dish2ing5);
		dish2.addIngredient(dish2ing6);
		dish2.addIngredient(dish2ing7);
		dish2.addIngredient(dish2ing8);
		dish2.addIngredient(dish2ing9);
		dish2.addIngredient(dish2ing10);
		dish2.addIngredient(dish2ing11);
		dishes.add(dish2);

		Dish dish3 = new Dish(
				"Baked Brie",
				Dish.DESERT,
				"bakedbrie.jpg",
				"Preheat an oven to 400 degrees F (200 degrees C). Place the beef into a mixing bowl, and season with salt, onion, garlic salt, Italian seasoning, oregano, red pepper flakes, hot pepper sauce, and Worcestershire sauce; mix well. Add the milk, Parmesan cheese, and bread crumbs. Mix until evenly blended, then form into 1 1/2-inch meatballs, and place onto a baking sheet. Bake in the preheated oven until no longer pink in the center, 20 to 25 minutes.");
		Ingredient dish3ing1 = new Ingredient("Brie", 115, "g", 20);
		dish3.addIngredient(dish3ing1);
		dishes.add(dish3);

		Dish dish4 = new Dish(
				"Ice cream",
				Dish.DESERT,
				"icecream.jpg",
				"Preheat an oven to 400 degrees F (200 degrees C). Place the beef into a mixing bowl, and season with salt, onion, garlic salt, Italian seasoning, oregano, red pepper flakes, hot pepper sauce, and Worcestershire sauce; mix well. Add the milk, Parmesan cheese, and bread crumbs. Mix until evenly blended, then form into 1 1/2-inch meatballs, and place onto a baking sheet. Bake in the preheated oven until no longer pink in the center, 20 to 25 minutes.");
		Ingredient dish4ing1 = new Ingredient("Ice", 115, "g", 20);
		Ingredient dish4ing2 = new Ingredient("Cream", 115, "g", 20);
		dish4.addIngredient(dish4ing1);
		dish4.addIngredient(dish4ing2);
		dishes.add(dish4);

		Dish dish5 = new Dish(
				"Sour dough",
				Dish.STARTER,
				"sourdough.jpg",
				"Preheat an oven to 400 degrees F (200 degrees C). Place the beef into a mixing bowl, and season with salt, onion, garlic salt, Italian seasoning, oregano, red pepper flakes, hot pepper sauce, and Worcestershire sauce; mix well. Add the milk, Parmesan cheese, and bread crumbs. Mix until evenly blended, then form into 1 1/2-inch meatballs, and place onto a baking sheet. Bake in the preheated oven until no longer pink in the center, 20 to 25 minutes.");
		Ingredient dish5ing1 = new Ingredient("Sour powder", 115, "g", 20);
		Ingredient dish5ing2 = new Ingredient("Dough", 115, "g", 20);
		dish5.addIngredient(dish5ing1);
		dish5.addIngredient(dish5ing2);
		dishes.add(dish5);
		
		selectedDishes = new ArrayList<Dish>(4);
		for(int i = 0; i < 5; i++)
			selectedDishes.add(null);
	}

	/**
	 * Returns the set of all dishes.
	 */
	public Set<Dish> getDishes() {
		return dishes;
	}

	/**
	 * Returns the set of dishes of specific type. (1 = starter, 2 = main, 3 = desert).
	 */
	public Set<Dish> getDishesOfType(int type) {
		Set<Dish> result = new HashSet<Dish>();
		for (Dish d : dishes) {
			if (d.getType() == type) {
				result.add(d);
			}
		}
		return result;
	}

	/**
	 * Returns the set of dishes of specific type, that contain filter in their name or name of any ingredient.
	 */
	public Set<Dish> filterDishesOfType(int type, String filter) {
		Set<Dish> result = new HashSet<Dish>();
		for (Dish d : dishes) {
			if (d.getType() == type && d.contains(filter)) {
				result.add(d);
			}
		}
		return result;
	}

	public int getNumberOfGuests() {
		return this._numberOfGuests;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this._numberOfGuests = numberOfGuests;
	}

	/**
	 * Returns the dish that is SELECTED ON THE menu for selected type (1 = starter, 2 = main, 3 = desert).
	 */
	public Dish getSelectedDish(int type) {
		return selectedDishes.get(type - 1);
	}

	/**
	 * Returns all the dishes THE USER HAS SELECTED.
	 */
	public Set<Dish> getFullMenu() {
		Set<Dish> result = new HashSet<Dish>();
		for (Dish d : selectedDishes) {
			if (d != null) {
				result.add(d);
			}
		}
		return result;
	}

	/**
	 * Returns all ingredients for all the dishes on the SELECTED MENU.
	 */
	public Set<Ingredient> getAllIngredients() {
		Set<Ingredient> result = new HashSet<Ingredient>();
		for (Dish d : selectedDishes) {
			if (d != null) {
				for (Ingredient i : d.ingredients) {
					result.add(i);
				}
			}
		}

		Set<Ingredient> ingredientsToUseInList = new HashSet<Ingredient>();

		for (Ingredient newIngredient : result) {
			boolean exists = false;
			for (Ingredient existingIngredient : ingredientsToUseInList) {
				if (existingIngredient.getName() == newIngredient.getName()) {
					exists = true;
					existingIngredient.setQuantity(existingIngredient
							.getQuantity() + newIngredient.getQuantity());
					existingIngredient.setPrice(existingIngredient.getPrice()
							+ newIngredient.getPrice());
					break;
				}
			}
			if (exists == false) {
				ingredientsToUseInList.add(newIngredient);
			}
		}

		return ingredientsToUseInList;
	}

	/**
	 * Returns the total price of the menu (all the ingredients multiplied by number of guests).
	 */
	public float getTotalMenuPrice() {
		/*Set<Ingredient> allIngredients = getAllIngredients();
		float totalPrice = 0;
		for (Ingredient i : allIngredients) {
			totalPrice += i.getPrice() * getNumberOfGuests();
		}
		return totalPrice;*/
		float totalPrice = 0;
		for(Dish d : selectedDishes) {
			if(d != null)
				totalPrice += d.getPrice() * getNumberOfGuests();
		}
		return totalPrice;
	}

	// Add dish to currently selectd dishes!
	public void selectDish(Dish dishToSelect) {
		/*for (int i = 0; selectedDishes.size() > i; i++) {
			Dish d = selectedDishes.get(i);
			if (d != null && d.type == dishToSelect.type) {
				selectedDishes.set(i, null);
			}
		}*/

		selectedDishes.set(dishToSelect.type - 1, dishToSelect);
		System.out.println("Added dish!");
	}

	// Add dish to currently selectd dishes!
	public void deleteDish(Dish dishToDelete) {
		
		if(selectedDishes.get(dishToDelete.type - 1) == dishToDelete);
			selectedDishes.set(dishToDelete.type - 1, null);
		System.out.println("Removed dish");
	}

	// Add dish to currently selectd dishes!
	public void printAllDishes() {
		System.out.println("WANT TODOPRINT TIDSHES");
		for (Dish d : selectedDishes) {
			if(d != null)
				System.out.println("dish:: dish" + d.getName());
		}

	}

	// TEMPORARY THING
	// #TODO remove
	public void tempSeedOfChoice() {
		Set<Dish> allDishes = getDishes();
		int i = 0;
		for (Dish d : allDishes) {
			if (d != null && i > 2)
				break;
			selectDish(d);
		}
		System.out.println("Seeded!");
	}

}
