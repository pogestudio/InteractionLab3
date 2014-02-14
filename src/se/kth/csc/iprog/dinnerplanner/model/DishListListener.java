package se.kth.csc.iprog.dinnerplanner.model;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import se.kth.csc.iprog.dinnerplanner.swing.view.DinnerDishList.DinnerListListener;


public class DishListListener implements DinnerListListener {
	
	private DinnerModel thaDinnerModel;
	private DefaultListModel dinnerList;

	    public void onChanged() {
	    }
	    
	    public void onAdded(Dish d) {
	    	System.out.println("dish added: " + d.getName());
	    	
	    	thaDinnerModel.selectDish(d);
	    	thaDinnerModel.printAllDishes();
	    	
	    }
	    public void onRemoved(Dish d) {
	    	System.out.println("dish removed: " + d.getName());
	    	
	    	thaDinnerModel.deleteDish(d);
	    	thaDinnerModel.printAllDishes();
	    }
		public DinnerModel getThaDinnerModel() {
			return thaDinnerModel;
		}
		public void setThaDinnerModel(DinnerModel thaDinnerModel) {
			this.thaDinnerModel = thaDinnerModel;
		}
		public DefaultListModel getDinnerList() {
			return dinnerList;
		}
		public void setDinnerList(DefaultListModel dinnerList) {
			this.dinnerList = dinnerList;
		}

}