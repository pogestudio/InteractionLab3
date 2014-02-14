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

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.IDinnerModel;

public class DishReceiverHandler extends TransferHandler {
	private static final long serialVersionUID = 1L;
    private final DataFlavor localObjectFlavor;
    private JList source;
    private int[] indices;
    private int addIndex = -1; //Location where items were added
    private int addCount; //Number of items added.
    private DefaultListModel dinner;
    
    public DishReceiverHandler(DefaultListModel dinner) {
        super();
        localObjectFlavor = new ActivationDataFlavor(Object[].class, DataFlavor.javaJVMLocalObjectMimeType, "Array of items");
        this.dinner = dinner;
    }
	@Override
	protected Transferable createTransferable( JComponent c) {
        source = (JList)c;
        indices = source.getSelectedIndices();
        @SuppressWarnings("deprecation") Object[] transferedObjects = source.getSelectedValues();
        return new DataHandler(transferedObjects, localObjectFlavor.getMimeType());
	}
	
	@Override
	public boolean importData(TransferSupport info) {
	    if(!info.isDrop()) {
	    	return false;
	    }
	    
	    
        JList target = (JList)info.getComponent();
        JList.DropLocation dl = (JList.DropLocation)info.getDropLocation();
        DefaultListModel listModel = (DefaultListModel)target.getModel();
        int index = dl.getIndex();
        //boolean insert = dl.isInsert();
        int max = listModel.getSize();
        if(index<0 || index>max) {
            index = max;
        }
        addIndex = index;
        

        try{
            Object[] values = (Object[])info.getTransferable().getTransferData(localObjectFlavor);
            for(int i=0;i<values.length;i++) {
                int idx = index++;

                Dish d = (Dish)values[i];
                
                try{
                for(int j = 0; j < dinner.getSize(); ++j) {
                	Dish d2 = (Dish)dinner.get(j);
                	if(d2.getType() == d.getType()) {

                    	((DinnerDishList)target).onRemoved(d2);
                		dinner.remove(j);
                		break;
                	}
                }
                if(idx > dinner.getSize())
                	idx = dinner.getSize();

            	((DinnerDishList)target).onAdd(d);
               	listModel.add(idx, values[i]);
               	target.addSelectionInterval(idx, idx);
                }
                catch(Exception e) {
                	e.printStackTrace();
                }
                
               
            }
            addCount = target.equals(source) ? values.length : 0;
            return true;
        }catch(UnsupportedFlavorException ufe) {
            ufe.printStackTrace();
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }finally{
        	((DinnerDishList)target).onChange();
        	target.repaint();
        }
        return false;
	}
	
    @Override public int getSourceActions(JComponent c) {
        return MOVE; //TransferHandler.COPY_OR_MOVE;
    }
    
    @Override public boolean canImport(TransferSupport info) {
        return info.isDrop() && info.isDataFlavorSupported(localObjectFlavor);
    }
}
