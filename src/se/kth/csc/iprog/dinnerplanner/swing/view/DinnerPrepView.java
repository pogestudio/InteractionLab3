package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;


public class DinnerPrepView extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private static final String PREP_INTRO = "<h1>Dinner menu preparation </h1><br>";	

	DinnerModel model;
	JTextPane textPane;
	
	public static void OpenWindow(DinnerModel dm){
		DinnerPrepView dpv = new DinnerPrepView(dm);
		JFrame frame = new JFrame("Preparation"); 
		dpv.textPane.setPreferredSize(new Dimension(500, 500));		

		JScrollPane jsp = new JScrollPane(dpv.textPane);

		frame.getContentPane().add(jsp, BorderLayout.CENTER); 
		frame.setLocationRelativeTo(null); 
		frame.pack(); 
		frame.setVisible(true); 
	}

	public DinnerPrepView(DinnerModel instancedModel){
		model = instancedModel;
		textPane = new JTextPane();
		this.add(textPane, BorderLayout.CENTER);
		instancedModel.addObserver(this);
		init();
	}

	private void init(){
		String starters = getDesc(1);	
		String main = getDesc(2);
		String desert = getDesc(3);

		ArrayList<String> meals = new ArrayList<String>();
		meals.add(starters);
		meals.add(main);
		meals.add(desert);

		String desc = PREP_INTRO;
		for (String meal : meals)
			desc = desc + meal + "<br> <br>";

		this.setLayout(new BorderLayout());
		textPane.setContentType("text/html");
		if(!desc.equals(PREP_INTRO)){
			textPane.setText("<html> <center>" + desc + " </center></html>");
		}
		else
			textPane.setText("No meals to display");
	}
	
	private String getDesc(int type){
		Dish sel_dish = model.getSelectedDish(type);
		System.out.println("null - " + sel_dish);

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
		String dish = "";
		if(sel_dish != null)
				dish = "<b>" + mealtype + sel_dish.getName() + "</b>" + "<br> " + sel_dish.getDescription();
		return dish;
	}

	@Override
	public void update(Observable dm, Object arg1) {
		init();
	}
}	

