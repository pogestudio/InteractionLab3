package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Set;

import javax.swing.*;

import external.WrapLayout;

import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

public class DishDetails extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public static void OpenWindow(Dish dish)
	{
		DishDetails detailsForWindow = new DishDetails(dish);
		 JFrame frame = new JFrame("Dish Details"); 
		 
		 //JLabel textLabel = new JLabel("I'm a label in the window",SwingConstants.CENTER);
		 detailsForWindow.setPreferredSize(new Dimension(500, 500));
		 detailsForWindow.setMinimumSize(new Dimension(100, 100));

		 frame.getContentPane().add(detailsForWindow, BorderLayout.CENTER);
		 
		 //Display the window. 
		 frame.setLocationRelativeTo(null); 
		 frame.pack(); 
		 frame.setVisible(true); 

	}

	private Dish activeDish;
	public DishDetails(Dish dish) {		
		activeDish = dish;
		
		
		JPanel top = getTopPanel();
		
		JScrollPane left = getLeftPanel();
		JScrollPane right = getRightPanel(dish.getIngredients());
		
		this.setLayout(new BorderLayout());

		
		JSplitPane absoluteBottom = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				left,
				right);
		
		absoluteBottom.setResizeWeight(0.5);
		//absoluteBottom.setDividerLocation(0.5);
		
		JSplitPane masterSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				top,
				absoluteBottom);
		
		masterSplitPane.setResizeWeight(0.3);
		//masterSplitPane.setDividerLocation(0);
		
		
		this.add(masterSplitPane, BorderLayout.CENTER);
		
	}
	
	
	JPanel getTopPanel()
	{
		JPanel top = new JPanel(new BorderLayout());
		top.setMinimumSize(new Dimension(100, 100));
		
		ImageIcon image = new ImageIcon("images/"+ activeDish.getImage());
		JLabel imgLabel = new JLabel("", image, JLabel.CENTER);
		top.add(imgLabel, BorderLayout.WEST);
		
		top.add(getTopRightPanel()); 
		
		return top;
	}
	
	JPanel getTopRightPanel()
	{
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
		String completeDishString = activeDish.getName();
		String otherInfo = "$" +  String.valueOf(activeDish.getPrice()) + " for 1 person";
		JLabel dishInfo = new JLabel(completeDishString, JLabel.LEFT);
		JLabel otherInfoLabel = new JLabel(otherInfo, JLabel.LEFT);
		rightPanel.add(dishInfo);
		rightPanel.add(otherInfoLabel);
		
		
		return rightPanel;
	}
	
	
	JScrollPane getLeftPanel()
	{
		JTextArea text =  new JTextArea (activeDish.getDescription());
		text.setWrapStyleWord(true);
		text.setLineWrap(true);
		text.setEditable(false);
		JScrollPane left = new JScrollPane(text);
		left.setMinimumSize(new Dimension(100, 100));
		
		return left;
	}
	
	JScrollPane getRightPanel(Set<Ingredient> Ingredients)
	{
//		JPanel right = new JPanel();
//		right.setMinimumSize(new Dimension(100, 100));
//		right.add(new JButton("RIGHT SHIIIIIIIT")); 	
//		return right;
		
	    ListIngredients allIngredients = new ListIngredients();
	    
	      return allIngredients.getTableForIngredients(Ingredients);
	}
	
}
