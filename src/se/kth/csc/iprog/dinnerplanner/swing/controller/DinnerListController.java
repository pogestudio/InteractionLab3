package se.kth.csc.iprog.dinnerplanner.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;
import se.kth.csc.iprog.dinnerplanner.swing.view.DinnerListView;
import se.kth.csc.iprog.dinnerplanner.swing.view.DinnerPrepView;
import se.kth.csc.iprog.dinnerplanner.swing.view.ListIngredients;

public class DinnerListController implements ChangeListener {

	private DinnerListView view;
	private DinnerModel model;

	public DinnerListController(DinnerListView view, final DinnerModel model) {
		this.view = view;
		this.model = model;

		this.view.getNumPeopleSpinner().addChangeListener(this);

		this.view.addPreparationButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (model != null) {
					DinnerPrepView.OpenWindow(model);
				}
			}
		});
		this.view.addIngredientsButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (model != null) {
					ListIngredientsController lController = new ListIngredientsController(model);
					lController.OpenWindowForDinner();
				}
			}
		});
	}

	@Override
	public void stateChanged(ChangeEvent evt) {
		int value = (Integer) ((JSpinner) evt.getSource()).getValue();
		model.setNumberOfGuests(value);
	}

}