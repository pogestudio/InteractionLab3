package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.*;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;


public class MainView extends JPanel {
	
	private static final long serialVersionUID = 1L;

	DishDetails dishDetails;
	DishListView dishes;
	DinnerModel wholeModel;
	JSplitPane split;
	DinnerListView dinnerListView;
	
	public MainView(DinnerModel instancedModel){
		wholeModel = instancedModel;
		//DishDetails.OpenWindow(wholeModel.getSelectedDish(1));
		//ListIngredients.OpenWindow(wholeModel.getAllIngredients());
		
		this.setLayout(new BorderLayout());
		
		dishes = new DishListView();
		dishes.setMinimumSize(new Dimension(500, 50));
		
		dinnerListView = new DinnerListView(); 
		dinnerListView.setPreferredSize(new Dimension(100,50));
				
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				dishes,
				dinnerListView);
		
		split.setResizeWeight(0.44);
		//split.setDividerLocation(150);
		
		this.add(split, BorderLayout.CENTER);
	}
	
}
