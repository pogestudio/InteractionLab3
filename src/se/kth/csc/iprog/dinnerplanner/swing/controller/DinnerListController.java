package se.kth.csc.iprog.dinnerplanner.swing.controller;

import java.awt.event.ActionListener;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.swing.view.DinnerListView;

public class DinnerListController implements ChangeListener{

	private DinnerListView view;
	private DinnerModel model;

	public DinnerListController(DinnerListView view, DinnerModel model) {
		this.view = view;
		this.model = model;
		
		this.view.getNumPeopleSpinner().addChangeListener(this);
	}

	@Override
	public void stateChanged(ChangeEvent evt) {
		int value = (Integer) ((JSpinner)evt.getSource()).getValue();
		model.setNumberOfGuests(value);
	}

}