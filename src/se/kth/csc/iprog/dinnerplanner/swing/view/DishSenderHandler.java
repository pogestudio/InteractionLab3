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

public class DishSenderHandler extends TransferHandler {
	private static final long serialVersionUID = 1L;
    private final DataFlavor localObjectFlavor;
    private JList source;
    private int[] indices;
    private int addIndex = -1; //Location where items were added
    private int addCount; //Number of items added.
  
    public DishSenderHandler() {
        super();
        localObjectFlavor = new ActivationDataFlavor(Object[].class, DataFlavor.javaJVMLocalObjectMimeType, "Array of items");

    }
	@Override
	protected Transferable createTransferable(JComponent c) {
        source = (JList)c;
        indices = source.getSelectedIndices();
        @SuppressWarnings("deprecation") Object[] transferedObjects = source.getSelectedValues();
        return new DataHandler(transferedObjects, localObjectFlavor.getMimeType());
	}
	
	@Override
	public boolean importData(TransferSupport info) {

  
        return false;
	}
	
    @Override public int getSourceActions(JComponent c) {
        return MOVE; //TransferHandler.COPY_OR_MOVE;
    }
    
    @Override public boolean canImport(TransferSupport info) {
        return false;
    }
}
