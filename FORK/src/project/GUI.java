package project;
import java.awt.Color;

import javax.swing.*;

import java.io.IOException;
import java.net.*;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	/*
	 * Global Variables Declarations
	 * 	Constants variables - HEIGHT and WIDTH
	 * 	Public variables used in *MenuHandler Classes - window, textArea, homeItem1, homeItem2, helpItem
	 *  Private variables JMenu menu, JMenuBar menuBar
	 */
	private static final int HEIGHT=600, WIDTH=900;
	public static JFrame window;
	public static JEditorPane textArea;
	private JMenu menu;
	private JMenuBar menuBar;
	public static JMenuItem homeItem1, homeItem2, searchItem1, searchItem2, searchItem3, helpItem;
	/**
	 * This constructor method has NO parameters. It calls on the constructor method that passes two parameters (String,String).
	 * First string parameter, "HOME- ..", passed is used to set the window title.
	 * Second string parameter, ".../HTMLPages/index.html", passed is used to set the textArea a static webpage.
	 * @throws IOException
	 */
	public GUI() throws IOException{
		this("HOME- WELCOME TO FOOD ORDERED RIGHT KAREFULLY","file:///Applications/eclipse/workspace/FORK/src/project/HTMLPages/index.html");
		@SuppressWarnings("unused")
		Process process = new Process();
		@SuppressWarnings("unused")
		Database database = new Database();
	}//Constructor with NO Parameters
	/**
	 * This constructor method has two parameters (String title,String pageName).
	 * First string parameter, title, passed is used to set the window title.
	 * Second string parameter, pageName, passed is used to locate the static HTML page and set to the textArea.
	 * @param title
	 * @param pageName
	 * @throws IOException
	 */
	public GUI(String title ,String pageName) throws IOException{
		window = new JFrame(); // declare JFrame window as new JFrame object
		setUpGUI(window);// send window object to method
		window.setTitle(title);// set string title as window's title
		menuBar = new JMenuBar();// declare JMenuBar menuBar as new JMenuBar object
		createMenuBar(menuBar); // send menuBar to method
		window.add(menuBar,"North");// attaches the menuBar to the top of the window
		URL pageContent = new URL(pageName);// create URL object called pageContent which takes in a String and parses it as a URL
		textArea = new JEditorPane(pageContent); // 
		textArea.setEditable(false);
		window.add(textArea);
		window.setVisible(true);
	}//Constructor with 2 Parameters
	/**
	 * This method is used to take in the JFrame object and create a frame of size 900 x 600;
	 * @param window
	 */
	private void setUpGUI(JFrame window){
		window.setSize(WIDTH, HEIGHT);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//setUpGUI
	/**
	 * 
	 * This method takes in JMenuBar object and adds different JMenu objects as well as different JMenuItem objects.
	 * First JMenu object is Home which contains following JMenuItems: About Us and Exit.
	 * Second JMenu object is .
	 * The Last JMenu object is Help which contains the JMenuItem, Instructions.
	 * 
	 * @param menuBar
	 */
	private void createMenuBar(JMenuBar menuBar){
		menuBar.setBackground(Color.gray);//Setting the color of the menuBar to gray
		
		/*
		 *  Creating new Tab called Home on the JMenuBar object
		 *  When clicking on the Home tab the page will load back to the homepage (./HTMLPages/index.html) page
		 */
		menu = new JMenu("Home"); 
		menu.setBackground(Color.gray);
		menuBar.add(menu);
		/*
		 *  Under the Home tab create a new MenuItem called homeItem1 labeled About Us and when clicking on it will load the
		 *  HTMLPages/aboutus.html page into the textArea
		 */
		homeItem1= new JMenuItem("About Us");
		HomeMenuHandler homeItemHandler = new HomeMenuHandler();
		homeItem1.addActionListener(homeItemHandler);
		menu.add(homeItem1);
		//Creates a horizontal line between About Us and Exit 
		menu.addSeparator();
		/*
		 * Under the About Us MenuItem there is a horizontal line and under that create a new MenuItem called homeItem2 labeled
		 * as Exit when clicked on will exit the program and close the window 
		 */
		homeItem2= new JMenuItem("Exit");
		homeItemHandler = new HomeMenuHandler();
		homeItem2.addActionListener(homeItemHandler);
		menu.add(homeItem2);
		/*
		 * Creating new Tab called Search on the JMenuBar object
		 */
		menu = new JMenu("Search");
		menu.setBackground(Color.gray);
		menuBar.add(menu);
		/*
		 * Under the Search tab is an option to display the all the restaurant
		 */
		searchItem1 = new JMenuItem("Show All");
		SearchMenuHandler searchItemHandler = new SearchMenuHandler();
		searchItem1.addActionListener(searchItemHandler);
		menu.add(searchItem1);
		menu.addSeparator();
		/*
		 * Under the Show All MenuItem there is a horizontal line and under that create a new MenuItem called
		 * a new MenuItem labeled Cuisine Type when clicked on will pop open a screen to enter cuisine type. 
		 */
		searchItem2 = new JMenuItem("Cuisine Type");
		searchItemHandler = new SearchMenuHandler();
		searchItem2.addActionListener(searchItemHandler);
		menu.add(searchItem2);
		menu.addSeparator();
		/*
		 * Under the Cuisine Type MenuItem there is a horizontal line and under that create a new MenuItem called
		 * a new MenuItem labeled Allergies when clicked on will pop open a screen to enter cuisine type. 
		 */
		searchItem3 = new JMenuItem("Allergies");
		searchItemHandler = new SearchMenuHandler();
		searchItem3.addActionListener(searchItemHandler);
		menu.add(searchItem3);
		/*
		 * Creating new Tab called Help on the JMenuBar object
		 */
		menu = new JMenu("Help");
		menu.setBackground(Color.gray);
		helpItem= new JMenuItem("Instructions");
		HelpMenuHandler helpItemHandler = new HelpMenuHandler();
		helpItem.addActionListener(helpItemHandler);
		menu.add(helpItem);
		menuBar.add(menu);	
		
		
	}// createMenuBar
}//GUI
