package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JEditorPane;
/**
 * This class is listening for any click event that happens under the Home menu tab.
 * @author Ravi_Patel
 *
 */
public class HomeMenuHandler implements ActionListener {
	/*
	 * Global Variable
	 * 	String aboutUsPage - the location of the AboutUs.html
	 */
	private String aboutUsPage ="file:///Applications/eclipse/workspace/FORK/src/project/HTMLPages/AboutUs.html";
	@Override
	/**
	 * This method is takes action when the mouse clicks, the event, on an object under the Home tab.
	 */
	public void actionPerformed(ActionEvent event) {
		/*
		 * Once event is taken place the getSource() method return the object the event has taken placed on.
		 * The returned object is compared to the GUI's homeItem1 object. If they match the if condition is true and
		 * the textArea takes in the AboutUs.html and the window displays the About Us page.
		 */
		if(event.getSource() == GUI.homeItem1){
			GUI.window.setTitle("About Us");
			URL page = null;
			try {
				page = new URL(aboutUsPage);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} 
			try {
				GUI.textArea = new JEditorPane(page);
			} catch (IOException e) {
				e.printStackTrace();
			}
			GUI.textArea.setEditable(false);
			GUI.window.add(GUI.textArea);
			GUI.window.setVisible(true);
		}
		/*
		 * Once event is taken place the getSource() method return the object the event has taken placed on.
		 * The returned object matched GUI's homeItem2 object then the system will close the window and the program.
		 */
		if(event.getSource() == GUI.homeItem2){
			System.exit(1);
		}
		
	}

}
