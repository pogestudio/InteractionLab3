package se.kth.csc.iprog.dinnerplanner.swing.controller;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.swing.view.DinnerDishList;
import se.kth.csc.iprog.dinnerplanner.swing.view.DinnerDishList.DinnerListListener;

public class DinnerDishListController{
	
	
	public DinnerDishListController(final DinnerModel model, DinnerDishList view) {

		view.addListener(new DinnerListListener() {			
			@Override
			public void onRemoved(Dish dish) {
				model.deleteDish(dish);
			}	
			@Override
			public void onAdded(Dish dish) {
				model.selectDish(dish);
			}	
		});
	}

}
