package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	public SearchPanel() {

		JTextField text =  new JTextField("");
		text.setPreferredSize(new Dimension(250, 25));
		this.add(text);
	}
}
