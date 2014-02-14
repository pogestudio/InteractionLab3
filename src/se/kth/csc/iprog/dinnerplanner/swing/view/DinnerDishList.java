package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import se.kth.csc.iprog.dinnerplanner.model.Dish;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

public class DinnerDishList extends JList {

	private static final long serialVersionUID = 1L;
	private static JButton testbutton;
	private static JPanel testpanel;
	private static JLabel testimage;
	private static int numberOfPeople = 1;
	
	public interface DinnerListListener {
		void onChanged();
		void onAdded(Dish dish);
		void onRemoved(Dish dish);
	}
	
	private ArrayList<DinnerListListener> listeners;

	protected void onChange() {
		for(DinnerListListener l : listeners)
			l.onChanged();
	}
	protected void onAdd(Dish dish) {
		for(DinnerListListener l : listeners)
			l.onAdded(dish);
	}
	protected void onRemoved(Dish dish) {
		for(DinnerListListener l : listeners)
			l.onRemoved(dish);
	}
	
	public void addListener(DinnerListListener listener) {
		listeners.add(listener);
	}
	
	private DinnerModel chosenModel;

	public static class CellRenderer extends JPanel implements ListCellRenderer {
		private static final long serialVersionUID = 1L;

		private JLabel text;
		private JLabel image;
		JButton button;
		ImageIcon icon;
		
		public CellRenderer() {
		    setOpaque(true);

			this.setLayout(new FlowLayout());
			
		    text = new JLabel("errororoeroeroeror", JLabel.CENTER);
		    text.setPreferredSize(new Dimension(100, 60));
		    image = new JLabel("",JLabel.LEFT);
		    image.setPreferredSize(new Dimension(80, 60));
		    image.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		    
		    button = new JButton("X");
		    testimage = image;
		    testbutton = button;
		    testpanel = this;
		    add(text);
			this.add(image);			
			this.add(text);		
			this.add(button);
		  }
		
		@Override
		public Component getListCellRendererComponent(
				JList list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
		
			Dish dish = (Dish)value;
			text.setText("<html>" + dish.getName() + "<br>Cost: $" + dish.getPrice() * numberOfPeople + "</html>");
			image.setIcon(new ImageIcon("images/"+((Dish)value).getImage()));

		    if (isSelected) {
		      setBackground(list.getSelectionBackground());
		      setForeground(list.getSelectionForeground());
		    } else {
		      setBackground(list.getBackground());
		      setForeground(list.getForeground());
		    }
		    return this;
		}
	}

	public DinnerDishList(DefaultListModel model) {
		super(model);
	    listeners = new ArrayList<DinnerListListener>();

		setCellRenderer(new CellRenderer());
		setDropMode(DropMode.INSERT);
		setDragEnabled(false);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setTransferHandler(new DishReceiverHandler(model));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				clickButton(event.getPoint());
			}
		});
	}
	
	
	public void setDinnerModel(DinnerModel model)
	{
		this.chosenModel = model;
	}
	
	public DinnerModel getDinnerModel()
	{
		return this.chosenModel;
	}
	
	@SuppressWarnings("deprecation")
	private void clickButton(Point point) {
	    int index = locationToIndex(point);
		
		DefaultListModel m = (DefaultListModel)getModel();
	
		point.y -= testpanel.getPreferredSize().height * index;
		
		if(testbutton.getBounds().inside(point.x, point.y)) {
			onRemoved((Dish)m.getElementAt(index));
			onChange();
			m.remove(index);
		}
		if(testimage.getBounds().inside(point.x, point.y)) {
			DishDetails.OpenWindow((Dish)m.getElementAt(index));
		}
	}
	
	public void updateNumberOfPeople(int numberOfPeople) {
		DinnerDishList.numberOfPeople = numberOfPeople;
		
		this.repaint();
	}

}
