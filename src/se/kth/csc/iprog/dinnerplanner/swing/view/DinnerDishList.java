package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.ListSelectionModel;

import se.kth.csc.iprog.dinnerplanner.model.Dish;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class DinnerDishList extends JList implements Observer {

	private static final long serialVersionUID = 1L;
	private static JButton testbutton;
	private static JPanel testpanel;
	private static JLabel testimage;
	private static int numberOfPeople = 1;
	
	public interface DinnerListListener {
		void onAdded(Dish dish);
		void onRemoved(Dish dish);
	}

	private ArrayList<DinnerListListener> listeners;

	protected void onAdded(Dish dish) {
		for (DinnerListListener l : listeners)
			l.onAdded(dish);
	}

	protected void onRemoved(Dish dish) {
		for (DinnerListListener l : listeners)
			l.onRemoved(dish);
	}

	public void addListener(DinnerListListener listener) {
		listeners.add(listener);
	}
	
	private DinnerModel model;

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

	public DinnerDishList(DinnerModel model) {
		super(new DefaultListModel());
	    listeners = new ArrayList<DinnerListListener>();
	    this.model = model;
	    this.model.addObserver(this);

		setCellRenderer(new CellRenderer());
		setDropMode(DropMode.ON);
		setDragEnabled(false);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setTransferHandler(new DishReceiverHandler((DefaultListModel)getModel()));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				clickButton(event.getPoint());
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	private void clickButton(Point point) {
	    int index = locationToIndex(point);
		
		DefaultListModel m = (DefaultListModel)getModel();
	
		point.y -= testpanel.getPreferredSize().height * index;
		
		if(testbutton.getBounds().inside(point.x, point.y)) {
			onRemoved((Dish)m.getElementAt(index));
		}
		if(testimage.getBounds().inside(point.x, point.y)) {
			DishDetails.OpenWindow((Dish)m.getElementAt(index));
		}
	}
	
	public void updateNumberOfPeople(int numberOfPeople) {
		DinnerDishList.numberOfPeople = numberOfPeople;
		
		this.repaint();
	}
	@Override
	public void update(Observable o, Object arg) {
		DefaultListModel m = (DefaultListModel)this.getModel();
		
		m.clear();
		for(int i = 1; i <= 3; ++i)
		{
			Dish d = model.getSelectedDish(i);
			if(d != null)
				m.addElement(d);
		}
	}

}
