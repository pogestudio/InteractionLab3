package se.kth.csc.iprog.dinnerplanner.swing;

import java.awt.Dimension;

import javax.swing.JFrame;

import se.kth.csc.iprog.dinnerplanner.model.*;
import se.kth.csc.iprog.dinnerplanner.swing.view.*;


public class DinnerPlanner extends JFrame {

	private static final long serialVersionUID = 1L;

	public DinnerPlanner() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
	
	private DinnerModel model = new DinnerModel();

	public DinnerModel getModel() {
		return model;
	}

	public void setModel(DinnerModel model) {
		this.model = model;
	}

	public static void main(String[] args) {
		//Initiating the main JFrame
		DinnerPlanner dinnerPlanner = new DinnerPlanner();
		dinnerPlanner.setTitle("Dinner Planner");
		
		//Creating the first view
		MainView mainView = new MainView(dinnerPlanner.getModel());
		
		//Adding the view to the main JFrame
		dinnerPlanner.getContentPane().add(mainView);

		//Resize it so content fits
		dinnerPlanner.pack();
		
		//and starting the JFrame
		dinnerPlanner.setVisible(true);

		dinnerPlanner.setSize(800, 500);
		dinnerPlanner.setPreferredSize(new Dimension(800,500));
		dinnerPlanner.setMinimumSize(new Dimension(800,500));
		

	}

}
