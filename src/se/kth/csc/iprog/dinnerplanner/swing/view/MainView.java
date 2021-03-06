package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.swing.controller.DinnerListController;


public class MainView extends JPanel {
	
	private static final long serialVersionUID = 1L;

	DishDetails dishDetails;
	DishListView dishes;
	DinnerModel wholeModel;
	JSplitPane split;
	DinnerListView dinnerListView;
	DinnerListController dinnerListController;
	
	public MainView(DinnerModel instancedModel){
		wholeModel = instancedModel;
		
		this.setLayout(new BorderLayout());
		
		dishes = new DishListView();
		dishes.setMinimumSize(new Dimension(500, 50));
		
		dinnerListView = new DinnerListView(wholeModel); 
		dinnerListView.setPreferredSize(new Dimension(100,50));
				
		dinnerListController = new DinnerListController(dinnerListView,  wholeModel);
		
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				dishes,
				dinnerListView);
		
		split.setResizeWeight(0.44);
		
		this.add(split, BorderLayout.CENTER);
	}
	
}
