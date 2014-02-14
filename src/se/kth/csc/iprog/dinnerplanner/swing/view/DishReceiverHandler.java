package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

import se.kth.csc.iprog.dinnerplanner.model.Dish;

@SuppressWarnings("rawtypes")
public class DishReceiverHandler extends TransferHandler {
	private static final long serialVersionUID = 1L;
	private final DataFlavor localObjectFlavor;
	private JList source;
	private DefaultListModel dinner;

	public DishReceiverHandler(DefaultListModel dinner) {
		super();
		localObjectFlavor = new ActivationDataFlavor(Object[].class,
				DataFlavor.javaJVMLocalObjectMimeType, "Array of items");
		this.dinner = dinner;
	}

	@Override
	protected Transferable createTransferable(JComponent c) {
		source = (JList) c;
		@SuppressWarnings("deprecation")
		Object[] transferedObjects = source.getSelectedValues();
		return new DataHandler(transferedObjects,
				localObjectFlavor.getMimeType());
	}

	@Override
	public boolean importData(TransferSupport info) {
		if (!info.isDrop()) {
			return false;
		}
		DinnerDishList target = (DinnerDishList) info.getComponent();

		try {
			Object[] values = (Object[]) info.getTransferable().getTransferData(localObjectFlavor);
	
			for (int i = 0; i < values.length; i++) {

				Dish d = (Dish) values[i];
				try {
					for (int j = 0; j < dinner.getSize(); ++j) {
						Dish d2 = (Dish) dinner.get(j);
						if (d2.getType() == d.getType()) {
							target.onRemoved(d2);
							break;
						}
					}
					target.onAdded(d);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return true;
		} catch (UnsupportedFlavorException ufe) {
			ufe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} 
		return false;
	}

	@Override
	public int getSourceActions(JComponent c) {
		return MOVE; 
	}

	@Override
	public boolean canImport(TransferSupport info) {
		return info.isDrop() && info.isDataFlavorSupported(localObjectFlavor);
	}
}
