package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;


public class DinnerPrepView extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String PREP_INTRO = "<h1>Dinner menu preparation </h1><br>";



	public static void OpenWindow(DinnerModel dm){
		DinnerPrepView dpv = new DinnerPrepView(dm);
		JFrame frame = new JFrame("Preparation"); 
		dpv.textPane.setPreferredSize(new Dimension(500, 500));		

		JScrollPane jsp = new JScrollPane(dpv.textPane);
		
		frame.getContentPane().add(jsp, BorderLayout.CENTER); 
		//Display the window. 
		frame.setLocationRelativeTo(null); 
		frame.pack(); 
		frame.setVisible(true); 

		System.out.println("Presentation-view");


	}

	JTextPane textPane = new JTextPane();
	String contentString = "";

	public DinnerPrepView(DinnerModel instancedModel){
		ArrayList<String> starters = getDesc(instancedModel, 1);	
		ArrayList<String> main = getDesc(instancedModel, 2);
		ArrayList<String> desert = getDesc(instancedModel, 3);

		ArrayList<String> meals = new ArrayList<String>();
		meals.addAll(starters);
		meals.addAll(main);
		meals.addAll(desert);

		String desc = PREP_INTRO;
		for (String meal : meals)
			desc = desc + meal + "<br> <br>";

		this.setLayout(new BorderLayout());
		//JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		if(!desc.equals(PREP_INTRO)){
			textPane.setText("<html> <center>" + desc + " </center></html>");
		}
		else
			textPane.setText("No meals to display");

		this.add(textPane, BorderLayout.CENTER);

		contentString = desc;
	}


	private ArrayList<String> getDesc(DinnerModel dm, int type){
		ArrayList<Dish> uniqueDishList = new ArrayList<Dish>();
		Dish sel_dish = dm.getSelectedDish(type);
		
		System.out.println("dish - " + sel_dish);
		
		// Unnecessary Set but it's here since I thought that we could have
		// multiple dishes for a dish type which is not the case 
		Set<Dish> dishList = new HashSet<Dish>();
		dishList.add(sel_dish);

		ArrayList<String> list = new ArrayList<String>();

		String mealtype = "";
		if(type == 1){
			mealtype = "Starter: ";
		}
		else if(type == 2){
			mealtype = "Main: ";
		}
		else{
			mealtype = "Desert: ";

		}
		for (Dish dish : dishList) {
			if(dish != null && !uniqueDishList.contains(dish)){
				
				list.add("<b>" + mealtype + dish.getName() + "</b>" + "<br> " + dish.getDescription());
				uniqueDishList.add(dish);
			}
		}
		return list;
	}

}

