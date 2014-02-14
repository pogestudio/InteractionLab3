package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

public class ListIngredients {
	
	public static void OpenWindow(Set<Ingredient> Ingredients)
	{
		ListIngredients ingredientsInList = new ListIngredients();
		JScrollPane tableToShow = ingredientsInList.getTableForIngredients(Ingredients);
		 JFrame frame = new JFrame("Ingredients"); 
		 //JLabel textLabel = new JLabel("I'm a label in the window",SwingConstants.CENTER);
		tableToShow.setPreferredSize(new Dimension(500, 500));
		tableToShow.setMinimumSize(new Dimension(100, 100));

		 frame.getContentPane().add(tableToShow, BorderLayout.CENTER); 
		 //Display the window. 
		 frame.setLocationRelativeTo(null); 
		 frame.pack(); 
		 frame.setVisible(true); 
	}
	
	public JScrollPane getTableForIngredients(Set<Ingredient> Ingredients)
	{	

		final ArrayList<Ingredient> ingredientsForTable = new ArrayList<Ingredient>(Ingredients);		  
		
		      TableModel dataModel = new AbstractTableModel() {
		          /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				public int getColumnCount() { return 3; }
		          public int getRowCount() { return ingredientsForTable.size(); }
		          public Object getValueAt(int row, int col) {
		        	  Ingredient ingredientForRow = ingredientsForTable.get(row);
		        	  String stringToShow = null;
		        	  switch(col){
		        	  case 0:
		        	  {
		        		  stringToShow = ingredientForRow.getName();
		        		  break;
		        	  }
		        	  case 1:
		        	  {
		        		  stringToShow = String.valueOf(ingredientForRow.getQuantity());
		        		  break;
		        	  }
		        	  case 2:
		        	  {
		        		  stringToShow = String.valueOf(ingredientForRow.getPrice());
		        		  break;
		        	  }
		        	  }
		        	  return stringToShow; }
		      };
		      JTable table = new JTable(dataModel);
		      table.getColumnModel().getColumn(0).setHeaderValue("Ingredient");
		      table.getColumnModel().getColumn(1).setHeaderValue("Quantity");
		      table.getColumnModel().getColumn(2).setHeaderValue("Cost");
		      JScrollPane scrollpane = new JScrollPane(table);
		      return scrollpane;
	}

}


