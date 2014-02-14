package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

@SuppressWarnings("rawtypes")
public class DishSenderHandler extends TransferHandler {
	private static final long serialVersionUID = 1L;
    private final DataFlavor localObjectFlavor;
	private JList source;
  
    public DishSenderHandler() {
        super();
        localObjectFlavor = new ActivationDataFlavor(Object[].class, DataFlavor.javaJVMLocalObjectMimeType, "Array of items");
    }
	@Override
	protected Transferable createTransferable(JComponent c) {
        source = (JList)c;
        @SuppressWarnings("deprecation") Object[] transferedObjects = source.getSelectedValues();
        return new DataHandler(transferedObjects, localObjectFlavor.getMimeType());
	}
	
	@Override
	public boolean importData(TransferSupport info) {
        return false;
	}
	
    @Override public int getSourceActions(JComponent c) {
        return MOVE;
    }
    
    @Override public boolean canImport(TransferSupport info) {
        return false;
    }
}
