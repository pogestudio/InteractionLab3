package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import se.kth.csc.iprog.dinnerplanner.model.Dish;

import external.WrapLayout;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.DishListListener;
import se.kth.csc.iprog.dinnerplanner.swing.view.DinnerDishList.DinnerListListener;

public class DinnerListView extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

	private DinnerDishList dishes;
	private DinnerModel thaDinnerModel;
	private JLabel totalCostLabel;
	private JSpinner numPeopleSpinner; 
	JButton preparation;
	JButton ingredients;
	
	@SuppressWarnings("unchecked")
	public DinnerListView (DinnerModel model) {
		
		thaDinnerModel = model;
		thaDinnerModel.addObserver(this);

		this.setLayout(new BorderLayout());

		//Top
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(2,2, 0, 10));
		top.add(new JLabel("Number of people: "));
		top.setBorder(new EmptyBorder(10, 30, 10, 30) );

		SpinnerModel spinmodel = new SpinnerNumberModel(2, 1, 100, 1);
		numPeopleSpinner = new JSpinner(spinmodel);
		
		
		top.add(numPeopleSpinner);
		top.add(new JLabel("Total cost: "));
		
		totalCostLabel = new JLabel("$94300");
		top.add(totalCostLabel);
		
		this.add(top, BorderLayout.NORTH);
		
		//Middle - list
		JPanel middle = new JPanel();
		middle.setLayout(new BorderLayout());
	
		
		@SuppressWarnings("rawtypes")
		DefaultListModel lmodel = new DefaultListModel();
		for(Dish d : thaDinnerModel.getFullMenu()) {
			lmodel.addElement(d);
		}
		
		DishListListener dishListener = new DishListListener();
		dishListener.setDinnerList(lmodel);
		dishListener.setThaDinnerModel(thaDinnerModel);
		//lmodel.addListDataListener(dishListener);

		dishes = new DinnerDishList(lmodel);
		dishes.setLayout(new WrapLayout());
		dishes.addListener(dishListener);
			dishes.addListener(new DinnerListListener() {
			@Override
			public void onChanged() {
				updateCostLabel();
			}
			@Override
			public void onAdded(Dish dish) {}
			@Override
			public void onRemoved(Dish dish) {}
		});
		JLabel title = new JLabel("Dinner menu", JLabel.CENTER);
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
		middle.add(title, BorderLayout.NORTH);
		
		JScrollPane scroll = new JScrollPane(dishes);
		middle.add(scroll, BorderLayout.CENTER);
		scroll.setBorder(new EmptyBorder(0,0,0,0));
	
		this.add(middle, BorderLayout.CENTER);
		
		//Bottom - buttons
		JPanel bottom = new JPanel();

		preparation =new JButton("Preparation"); 
		bottom.add(preparation);
		ingredients =new JButton("Ingredients"); 
		bottom.add(ingredients);

		this.add(bottom, BorderLayout.SOUTH);
		updateCostLabel();
	}

	public void addPreparationButtonListener(ActionListener listener) {
		preparation.addActionListener(listener);
	}
	
	public void addIngredientsButtonListener(ActionListener listener) {
		ingredients.addActionListener(listener);
	}
	
	public JSpinner getNumPeopleSpinner() {
		return numPeopleSpinner;
	}
	
	public DinnerDishList getDishes() {
		return dishes;
	}
	
	public void updateCostLabel() {
		float newCost = thaDinnerModel.getTotalMenuPrice();
		totalCostLabel.setText("$" + String.valueOf(newCost));
		this.dishes.updateNumberOfPeople(thaDinnerModel.getNumberOfGuests());
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("update: " + arg);
		updateCostLabel();
	}
}
