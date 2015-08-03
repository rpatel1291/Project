package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 * This is used to respond to events that happen with in the Search Menu Tab
 * @author Ravi_Patel
 *
 */
public class SearchMenuHandler implements ActionListener {	
	
	@Override
	/*
	 *When event takes place the event triggered is tried to match with following options
	 */
	public void actionPerformed(ActionEvent event) {
		/* 
		 * If Search Item 1 is clicked then it will display the whole restaurant table
		 */
		if(event.getSource() == GUI.searchItem1){
			try {
				Database.stmt.execute("Select *"
						+ "From Restaurant"
						+ "Where"
						+ "Sort by 1");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error has occured");
			}
		}
		if(event.getSource() == GUI.searchItem2){
			String cuisine = JOptionPane.showInputDialog("Enter cuisine type: ").toLowerCase();
			String allergies = JOptionPane.showInputDialog("Enter allergies separated by ',': ").toLowerCase();
			try {
				Database.stmt.execute("Select *"
						+ "From Restaurants R, Cuisine C, RestaurantType RT, Items I"
						+ "Where R.RestaurantID = RT.RestaurantID AND C.CuisineID = RT.CuisineID AND"
							+ "I.RestaurantID = R.RestaurantID AND C.Cuisine_Type LIKE"+cuisine+" AND I.Item_Description NOT LIKE"+allergies);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error has occured");
			}
		}
	}
  
}
