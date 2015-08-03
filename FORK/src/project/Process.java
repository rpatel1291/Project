package project;
import java.util.regex.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class Process {
	/*
	 * Global variables:
	 * 	Final String: USER_AGENT, ORIGINAL_FILE, FIRST_PROCESS
	 * 	String Array: restaurant
	 *	BufferedReader reader
	 *	PrintWriter output, output2
	 * 
	 */
	private static final String USER_AGENT="Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_4) AppleWebKit/600.7.12 "
			+ "(KHTML, like Gecko) Version/8.0.7 Safari/600.7.12";
	private static final String ORIGINAL_FILE ="./src/project/InputWebPage.txt";
	private static final String FIRST_PROCESS ="./src/project/FirstProcess.txt";
	private static String[] restaurants = {
				"http://www.allmenus.com/ny/new-york/248742-johns/menu/",
				"http://www.allmenus.com/ny/new-york/47374-a-taste-of-seafood/menu/",
				"http://www.allmenus.com/ny/new-york/3035-le-marais/menu/",
				"http://www.allmenus.com/ny/new-york/249747-petite-abeille/menu/",
				"http://www.allmenus.com/ga/atlanta-decatur/281304-fortune-cookie/menu/",
				"http://www.allmenus.com/ga/atlanta/278792-goodfellas-pizza-and-wings/menu/",
				"http://www.allmenus.com/ga/atlanta/308971-jacks-pizza--wings/menu/",
				"http://www.allmenus.com/ca/southeast/83168-la-pizza-loca/menu/",
				"http://www.allmenus.com/ca/los-angeles/271002-guelaguetza-restaurant/menu/"
			};
	private BufferedReader reader;
	private PrintWriter output, output2;
	/**
	 * 	Constructor that takes in no parameters and calls constructor that takes in a String array parameter
	 */
	public Process(){
		this(restaurants);
	}//Process with no parameters
	/**
	 * Constructor that takes in one String array parameter that is a URL to a restaurant's menu 
	 * @param webaddress
	 */
	public Process(String[] webaddress){
		for(int i = 0; i < webaddress.length; i++){
		try{
			reader = read(webaddress[i]);
			output = new PrintWriter(ORIGINAL_FILE);
			output2 = new PrintWriter(FIRST_PROCESS);
			readWhole(reader, output);
			BufferedReader readAgain = new BufferedReader(new FileReader(ORIGINAL_FILE));
			firstProcess(readAgain, output2);
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Error");
		}
		}
	}//Process with 1 String parameter
	/**
	 * This method takes in two parameters one of type BufferedReader and the other type PrintWriter.
	 * The BufferedReader is the collection of HTML tags from the input URL.
	 * The PrintWriter is the output file in which the content within the tags will be saved.
	 * @param original
	 * @param output
	 */
	public static void firstProcess(BufferedReader original, PrintWriter output){
		/*
		 * The different type of content needed for the database are distributed throughout the different tags.
		 * Each content has a specific HTML syntax to matching 
		 * After each line is properly identified it is saved to a text file.
		 */
		String beforeHTMLTag = "\\s*<\\w+\\s?[^>]*>";
		String afterHTMLTag = "</\\w+[^>]*>";
		Pattern restaurantName = Pattern.compile("<(h1)[^>]+>(.*?)</\\1>");
		Pattern restaurantAddress = Pattern.compile("<(span)\\s(class=).*\\s(itemprop=\"streetAddress\")>(.*?)</\\1>");
		Pattern	restaurantCity = Pattern.compile("<(span)\\s(class=).*\\s(itemprop=\"addressLocality\")>(.*?)</\\1>");
		Pattern restaurantState = Pattern.compile("<(span)\\s(class=).*\\s(itemprop=\"addressRegion\")>(.*?)</\\1>");
		Pattern restaurantZip = Pattern.compile("<(span)\\s(class=).*\\s(itemprop=\"postalCode\")>(.*?)</\\1>");
		Pattern cuisine = Pattern.compile("<(ul)>(.*?)</\\1>");
		Pattern menuCategory = Pattern.compile("<(h3)>(.*?)</\\1>");
		Pattern categoryDescription = Pattern.compile("<(p)>(.*?)</\\1>");
		Pattern menuItem = Pattern.compile("<(span)\\s*(class=\"name\")>(.*?)</\\1>");
		Pattern itemPrice = Pattern.compile("<(span)\\s*(class=\"price\")>(.*?)</\\1>");
		Pattern itemDescription = Pattern.compile("<(p)\\s*(class=\"description\")>(.*?)</\\1>");
		try{
			String line = original.readLine();
			while(line != null){
				Matcher matchRestName = restaurantName.matcher(line);
				Matcher matchRestAdd = restaurantAddress.matcher(line);
				Matcher matchRestCity = restaurantCity.matcher(line);
				Matcher matchRestState = restaurantState.matcher(line);
				Matcher matchRestZip = restaurantZip.matcher(line);
				Matcher matchCuisine = cuisine.matcher(line);
				Matcher matchMenuCat = menuCategory.matcher(line);
				Matcher matchCatDescr = categoryDescription.matcher(line);
				Matcher matchMenuItem = menuItem.matcher(line);
				Matcher matchItemPrice = itemPrice.matcher(line);
				Matcher matchItemDescr = itemDescription.matcher(line);
				
				if(matchRestName.find()){
					String Name = line.replaceAll(beforeHTMLTag, " ");
					Name = Name.replaceAll(afterHTMLTag, "\n");
					output.println(Name.toString());
				}
				if(matchRestAdd.find()){
					String tag = line.replaceAll(beforeHTMLTag, " ");
					tag = tag.replaceAll(afterHTMLTag, "\n");
					output.println(tag.toString());
				}
				if(matchRestCity.find()){
					String tag = line.replaceAll(beforeHTMLTag, " ");
					tag = tag.replaceAll(afterHTMLTag, "\n");
					output.println(tag.toString());	
				}
				if(matchRestState.find()){
					String tag = line.replaceAll(beforeHTMLTag, " ");
					tag = tag.replaceAll(afterHTMLTag, "\n");
					output.println(tag.toString());	
				}
				if(matchRestZip.find()){
					String tag = line.replaceAll(beforeHTMLTag, " ");
					tag = tag.replaceAll(afterHTMLTag, "\n");
					output.println(tag.toString());
				}
				if(matchCuisine.find()){
					String tag = line.replaceAll(beforeHTMLTag, "");
					tag = tag.replaceAll(afterHTMLTag, "\n");
					output.println(tag.toString());
				}
				if(matchMenuCat.find()){
					String tag = line.replaceAll(beforeHTMLTag, "Menu Category ");
					tag = tag.replaceAll(afterHTMLTag, "\n");
					output.println(tag.toString());
					
				}
				if(matchCatDescr.find()){
					String tag = line.replaceAll(beforeHTMLTag, " ");
					tag = tag.replaceAll(afterHTMLTag, "\n");
					output.println(tag.toString());
				}
				if(matchMenuItem.find()){
					String tag = line.replaceAll(beforeHTMLTag, "Item ");
					tag = tag.replaceAll(afterHTMLTag, "\n");
					output.println(tag.toString());
				}
				if(matchItemPrice.find()){
					String tag = line.replaceAll(beforeHTMLTag, "Price: ");
					tag = tag.replaceAll(afterHTMLTag, "\n");
					output.println(tag.toString());
				}
				if(matchItemDescr.find()){
					String tag = line.replaceAll(beforeHTMLTag, "Description ");
					tag = tag.replaceAll(afterHTMLTag, "\n");
					output.println(tag.toString());
				}
				line = original.readLine();
			}
		}
		catch(Exception error){
			System.out.println("error");
		}
	}//firstProcess
	/**
	 * This method takes in two parameters one of type BufferedReader and the other type PrintWriter.
	 * The output will save the HTML tags with content. 
	 * @param reader
	 * @param output
	 */
	public static void readWhole(BufferedReader reader, PrintWriter output){
		try{
			String line = reader.readLine();
			while(line != null){
				output.println(line);
				line = reader.readLine();
			}
		}
		catch(Exception error){
			JOptionPane.showMessageDialog(null, "Sorry.");
		}
	}//readWhole
	/**
	 * This method connects to the URL
	 * @param sURL
	 * @return
	 * @throws Exception
	 */
	public static InputStream getURLInputStream(String sURL) throws Exception {
        URLConnection oConnection = (new URL(sURL)).openConnection();
        oConnection.setRequestProperty("User-Agent", USER_AGENT);
        return oConnection.getInputStream();
    } // getURLInputStream
	/**
	 * This method reads the web content 
	 * @param url
	 * @return
	 * @throws Exception
	 */
    public static BufferedReader read(String url) throws Exception {
        InputStream content = (InputStream)getURLInputStream(url);
        return new BufferedReader (new InputStreamReader(content));
    } // read
	
	
}
