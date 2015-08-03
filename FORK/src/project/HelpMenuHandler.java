package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class HelpMenuHandler implements ActionListener {
	/**
	 * This event handler only works when clicked on and pops up the instructions for making this program work
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == GUI.helpItem){
			String instructions = "Hello there. First off I would like to welcome you to F.O.R.K. and thank you for using this service. \n \n"
					+ "The following steps will show you on how to use this service: \n"
					+ "\t Step 1: Click on the Search tab.\n"
					+ "\t Step 2: Under the tab are options to show all of the restaurants thus far in the database or \n"
					+ "\t \t choose cuisine type to enter a cuisine type you would like to eat.\n"
					+ "\t Step 3: Or click on the allegry option to type in the cuisine type then allegry to see all the option without it.";
			JOptionPane.showMessageDialog(null, instructions);
		}
		
	}

}
