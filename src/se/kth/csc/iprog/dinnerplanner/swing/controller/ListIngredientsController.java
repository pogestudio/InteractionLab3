package se.kth.csc.iprog.dinnerplanner.swing.controller;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.swing.view.ListIngredients;
public class ListIngredientsController implements Observer {
	
	DinnerModel thaDinnerModel;
	ListIngredients thaView;
	JFrame oldFrame;
	JScrollPane table;
	
	
	public ListIngredientsController (DinnerModel thaDinnerModel) {
		this.thaDinnerModel = thaDinnerModel; 
		thaDinnerModel.addObserver(this);
	}
	
	public void OpenWindowForDinner()
	{
		
		this.thaView = new ListIngredients();
		JFrame theFrame = getRegularFrame();
		updateTable();	
		
	}
	
	public void removeTable()
	{
		this.oldFrame.getContentPane().remove(this.table);
	}
	
	public void updateTable()
	{
		JFrame theFrame = this.oldFrame;
		
		this.table = thaView.getTableForIngredients(thaDinnerModel.getAllIngredients());
		this.table.setPreferredSize(new Dimension(500, 500));
		this.table.setMinimumSize(new Dimension(100, 100));
		 theFrame.getContentPane().add(this.table, BorderLayout.CENTER); 
		 theFrame.setLocationRelativeTo(null); 
			theFrame.pack();
	}
	
	private JFrame getRegularFrame()
	{
		 JFrame frame = new JFrame("Ingredients"); 
		 frame.pack(); 
		 frame.setVisible(true);
		 
		 this.oldFrame = frame;
		 return frame;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("upaaaaadatededweed");
		removeTable();
		updateTable();
		
		
	}
}

