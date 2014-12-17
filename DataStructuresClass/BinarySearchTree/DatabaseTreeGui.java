package BinarySearchTree; /**
 * DatabaseTreeGui.java
 * Jordan Bossman
 * CSC 364
 * 2/12/2012
 * Creates and manages the GUI and its functions
 * for adding, finding and deleting order records
 * from the 'datafile.txt' file.
 */
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.FileNotFoundException;

public class DatabaseTreeGui extends JFrame
{
	private JTextField jtxtNum;         //JTextField for user input of the order number for addition.
	private JTextField jtxtDate;        //JTextField for user input of the order date.
	private JTextField jtxtDue;		    //JTextField for user input of the due date.
	private JTextField jtxtTaker;      //JTextField for user input of the order taker.
	private JTextField jtxtPrice;       //JTextField for user input of the order price.
	private JTextField jtxtDiscount; //JTextField for user input of the discount;
	private JTextField jtxtDel;          //JTextField for user input of the order number for deletion.
	private JTextField jtxtFind;        //JTextField for user input of the order number for finding a record.
	private static JFrame gui;         //Main GUI JFrame.
	private JPanel addPanel;         //JPanel holding all of the layouts, labels, buttons and text fields for adding.
	private JPanel delPanel;          //JPanel holding all of the layouts, labels, buttons and text fields for deleting.
	private JPanel findPanel;         //JPanel holding all of the layouts, labels, buttons and text fields for finding.
	private static BinarySearchTree bst; //Holding a reference to the binary search tree.
	private static Order orderArray[];       //Array containing all of the orders.

    public DatabaseTreeGui() 
    {
    	//Creates and manages the GUI.
    	//Panel, buttons and layouts for the four add, delete, find, exit) buttons.
  		JPanel panel = new JPanel(); //JPanel for the buttons for proper layout.
    	JPanel btnPanel = new JPanel(new GridLayout(4,1)); //Main button panel.
    	JButton add = new JButton("Add a Record"); //The button for an "add".
    	add.setToolTipText("Adds a new record.");
    	add.addActionListener(
    		new ActionListener()
    		{
    			public void actionPerformed(ActionEvent e)
    			{
    				delPanel.setVisible(false);
    				findPanel.setVisible(false);
    				addPanel.setVisible(true);
    			}
    		});
    		
       	JButton del = new JButton("Delete a Record"); //The button for a "delete".
    	del.setToolTipText("Deletes an existing record.");
    	del.addActionListener(
    		new ActionListener()
    		{
    			public void actionPerformed(ActionEvent e)
    			{
    				addPanel.setVisible(false);
    				findPanel.setVisible(false);
    				delPanel.setVisible(true);
    			}
    		});
    		
    	JButton find = new JButton("Find a Record"); //The button for a "find".
    	find.setToolTipText("Finds an existing record.");
    	find.addActionListener(
    		new ActionListener()
    		{
    			public void actionPerformed(ActionEvent e)
    			{
    				delPanel.setVisible(false);
    				addPanel.setVisible(false);
    				findPanel.setVisible(true);
    			}
    		});
    		
    	JButton exit = new JButton("Exit"); //The button for an "exit".
    	exit.setToolTipText("Exits the program.");
    	exit.addActionListener(
    		new ActionListener()
    		{
    			public void actionPerformed(ActionEvent e)
    			{
    				addPanel.setVisible(false);
    				delPanel.setVisible(false);
    				findPanel.setVisible(false);
					exit(gui);
    			}
    		});

    	btnPanel.add(add);
    	btnPanel.add(del);
    	btnPanel.add(find);
    	btnPanel.add(exit);

		JPanel mainPanel = new JPanel(); //Panel holding all of the labels, text fields and buttons an "add", "delete" and "find"..
		mainPanel.setLayout(new OverlayLayout(mainPanel));
		
		addPanel = new JPanel();
		JPanel addRecordPanel = new JPanel(new GridLayout(6,2)); //Panel containing all of the items for an "add".
		JLabel labAdd = new JLabel("Please provide the information below to add a new record."); //Main "add" label.
		
	    JLabel ordNum = new JLabel("Enter the Order Number:"); //Order number label.
		addRecordPanel.add(ordNum);
		jtxtNum = new JTextField();
		addRecordPanel.add(jtxtNum);
		
		JLabel ordDate = new JLabel("Enter the Order Date (m/d/yyyy):"); //Order date label
		addRecordPanel.add(ordDate);
		jtxtDate = new JTextField();
		addRecordPanel.add(jtxtDate);
		
		JLabel dueDate = new JLabel("Enter the Due Date (m/d/yyyy):"); //Order due date label.
		addRecordPanel.add(dueDate);
		jtxtDue = new JTextField();
		addRecordPanel.add(jtxtDue);
		
		JLabel ordTaker = new JLabel("Enter the Order Taker:"); //Order taker label.
		addRecordPanel.add(ordTaker);
		jtxtTaker = new JTextField();
		addRecordPanel.add(jtxtTaker);
		
		JLabel price = new JLabel("Enter the Price:"); //Order price label.
		addRecordPanel.add(price);	
		jtxtPrice = new JTextField();
		addRecordPanel.add(jtxtPrice);
		
		JLabel discount = new JLabel("Discount Qualification (true/false):"); //Discount label.
		addRecordPanel.add(discount);
		jtxtDiscount = new JTextField();
		addRecordPanel.add(jtxtDiscount);
		
		JButton addRecord = new JButton("Add the Record"); //Button to perform the adding process.
		addRecord.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//Performs data validation and adding of a new record.
					addRecord(gui, jtxtNum, jtxtDate, jtxtDue, jtxtTaker, jtxtPrice, jtxtDiscount);
				}
			});
		addPanel.add(labAdd, BorderLayout.NORTH);
		addPanel.add(addRecordPanel, BorderLayout.CENTER);
		addPanel.add(addRecord, BorderLayout.SOUTH);
		addPanel.setVisible(false);
		mainPanel.add(addPanel, BorderLayout.CENTER);
		
		delPanel = new JPanel();
	    JLabel delLabel = new JLabel("Please enter the information below to delete a record."); //Main "delete" label.
    	JPanel delRecordPanel = new JPanel(new GridLayout(1,1)); //JPanel holding all of the items needed for a "delete".  				
    	JLabel delNum = new JLabel("Enter the Order Number:"); //Deletion order number label.
    	delRecordPanel.add(delNum);
    	jtxtDel = new JTextField();
    	delRecordPanel.add(jtxtDel);	
    		  	
    	JPanel jbtDelPanel = new JPanel(); //Contains the JButton for proper layout.
    	JButton delRecord = new JButton("Delete the Record"); //Delete record button.
    	jbtDelPanel.add(delRecord);
		delRecord.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//Performs data validation and deletion of a record.
					delRecord(gui, jtxtDel);
				}
			});    						
   		delPanel.add(delLabel, BorderLayout.NORTH);
  		delPanel.add(delRecordPanel, BorderLayout.CENTER);
  		delPanel.add(jbtDelPanel, BorderLayout.SOUTH);
  		delPanel.setVisible(false);	
  		mainPanel.add(delPanel, BorderLayout.CENTER);	
		
		findPanel = new JPanel();
		findPanel.setVisible(false);
		JLabel labFind = new JLabel("Please enter the information below to find a record."); //Main "find" label.
		JPanel findRecordPanel = new JPanel(new GridLayout(1,1)); //JPanel holding all of the items needed for a "find".
		JLabel findNum = new JLabel("Enter the Order Number:"); //Find order number label.
		jtxtFind = new JTextField();
		findRecordPanel.add(findNum);
		findRecordPanel.add(jtxtFind);
		
		JPanel jbtFindPanel = new JPanel(); //Contains the find button for proper layout.
		JButton findRecord = new JButton(" Find the Record "); //Find record button.
		jbtFindPanel.add(findRecord);
		findRecord.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//Performs data validation and finding of a record.
					findRecord(gui, jtxtFind);
				}
			});		
		findPanel.add(labFind, BorderLayout.NORTH);
		findPanel.add(findRecordPanel, BorderLayout.CENTER);
		findPanel.add(jbtFindPanel, BorderLayout.SOUTH);
		findPanel.setVisible(false);
		mainPanel.add(findPanel, BorderLayout.CENTER);
		
		panel.add(btnPanel);
    	add(panel, BorderLayout.NORTH);
    	add(mainPanel, BorderLayout.CENTER);
    }
    
    private void addRecord(JFrame gui, JTextField num, JTextField ordDate,
                                             JTextField ordDueDate, JTextField ordTaker, JTextField ordPrice,
                                             JTextField ordDiscount)
    {
    	//Method to validate input for an "add" and add the given record to the binary search tree.
    	String inpNum = num.getText(); //Gets the order number text from the JTextField.
    	if(!inpNum.matches("[A-Z]{3}[0-9]{5}"))
    	{
    		JOptionPane.showMessageDialog(gui, 
    			"Error: please check to make sure the order number has three uppercase alphabetic letters followed by five digits.", 
    			"Error", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	if(!isValidOrderDate(ordDate.getText()))
    	{
    			JOptionPane.showMessageDialog(gui, 
    				"Error: please make sure you have entered a valid order date (m/d/yyyy).", 
    				"Error", JOptionPane.ERROR_MESSAGE);
    			return;   	   	
    	}
    	
    	if(!isValidDueDate(ordDueDate.getText()))
    	{
    			JOptionPane.showMessageDialog(gui, 
    				"Error: please make sure you have entered a valid due date (m/d/yyyy).", 
    				"Error", JOptionPane.ERROR_MESSAGE);
    			return;   	   	
    	}

		String taker = ordTaker.getText(); //Gets the order taker text from the JTextField.
    	if(!taker.equals("White") && !taker.equals("Brown") && !taker.equals("Nelson") && 
    			!taker.equals("Harris") && !taker.equals("Weiner") && !taker.equals("Johnson"))
    	   {
    			JOptionPane.showMessageDialog(gui, 
    				"Error: please make sure the order taker is \"White\", \"Brown\", \"Harris\", \"Nelson\", \"Weiner\", or \"Johnson\".", 
    				"Error", JOptionPane.ERROR_MESSAGE);
    			return;   	   	
    	   }
    	
    	int price; //Holds the order price.
    	try
    	{
    	   	price = Integer.parseInt(ordPrice.getText());
        	if(price < 1000 || price >= 10000)
        		throw new NumberFormatException();
    	}
    	catch(NumberFormatException e)
    	{
    		JOptionPane.showMessageDialog(gui, 
    			"Error: please make sure the price field is filled out properly and is less than 10000, but greater than 1000.", 
    			"Error", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	boolean discount = false; //Holds the discount qualification for the order.
		String inpDiscount = ordDiscount.getText();
		if(inpDiscount.equalsIgnoreCase("true"))
			discount = true;
		else if(!inpDiscount.equalsIgnoreCase("false"))
		{
			JOptionPane.showMessageDialog(gui,
				"Error: please check to make sure the boolean for discount is in the format of \"true\" or \"false\".",
				"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Order newOrder = new Order(inpNum, ordDate.getText(), ordDueDate.getText(), taker, price, discount);
		int ind = getIndex(orderArray); //Gets the next available spot in the array to put the new order.
		if(ind == orderArray.length) //If for some reason an add causes the array to go out of bounds, this increases its size.
		{
			orderArray = increaseArraySize(orderArray);
			orderArray[ind] = newOrder;	
		}
		else
			orderArray[ind] = newOrder;
		
		Index index = new Index(inpNum, ind);
		
		bst.insert(index, inpNum);
		
		JOptionPane.showMessageDialog(gui, "Order added successfully!", "Order Added",
				JOptionPane.INFORMATION_MESSAGE);
		
		//Makes all of the JTextFields go blank for ease of another input.
		num.setText("");
		ordDate.setText("");
		ordDueDate.setText("");
		ordTaker.setText("");
		ordPrice.setText("");
		ordDiscount.setText("");
    }
    
    private boolean isValidOrderDate(String date)
    {
    	//Checks to see if the given date is a valid date and order date.
    	if(!isValidDate(date))
    		return false;
    	else
    	{
 			Calendar calendar = new GregorianCalendar(Calendar.getInstance().getTimeZone()); //Reference to today's date.
 			int month = calendar.get(Calendar.MONTH) + 1; //Holds the current month.	
 			int today = calendar.get(calendar.DAY_OF_MONTH); //Holds today's day number in the month.
    	
    		String[] dateInpString = date.split("/"); //Splits the user input by a "/".
    		int orderDay = Integer.parseInt(dateInpString[1]); //Gets the order day from user input.
    		int orderMonth = Integer.parseInt(dateInpString[0]); //Gets the order month from user input.
	    	
    		if(orderDay < today)
	    		return true;
    		else if(orderMonth < month)
	    		return true;
			else
	    		return false;
    	}
    }
    
    private boolean isValidDueDate(String date)
    {
    	//Checks to see if the given date is a valid date and due date.
    	if(!isValidDate(date))
    		return false;
    	else
    	{
 			GregorianCalendar calendar = new GregorianCalendar(Calendar.getInstance().getTimeZone()); //Reference to today's date.
	 		int month = calendar.get(Calendar.MONTH) + 1; //This month's number.
 			int today = calendar.get(calendar.DAY_OF_MONTH); //Today's number.
    	
    		String[] dateInpString = date.split("/"); //Splits the user inputed date by "/"s.
    		int dueDay = Integer.parseInt(dateInpString[1]); //The user inputed due day.
    		int dueMonth = Integer.parseInt(dateInpString[0]); //The user inputed due month.
    	
    		int endOfMonth = calendar.getActualMaximum(calendar.DAY_OF_MONTH); //Last day of current month.
			int validDay = today + 14; //Determines when the date is valid or not.
			
			if(validDay > endOfMonth)
			{
				validDay -= endOfMonth;
				if(dueDay >= validDay)
					return true;
				else if(dueMonth > month && dueDay < validDay)
					return true;
				else
					return false;
			}
    		else if(dueDay >= validDay && dueMonth >= month)
    			return true;
    		else if(dueDay < validDay && dueMonth > month)
    			return true;
			else
 				return false;
    	}
    }
    
    private boolean isValidDate(String testDate)
    {
    	//Checks to see if a given date is a valid date.
    	SimpleDateFormat date = new SimpleDateFormat("M/d/y");
    	date.setLenient(false);
    	try
    	{
			date.parse(testDate);
       	}
       	catch(ParseException e)
       	{
       		return false;
       	}
       	
       	return true;
    }
    
    private void delRecord(JFrame gui, JTextField num)
    {
    	//Performs data validation and a "delete".
    	String inpNum = num.getText(); //Order number from the JTextField.
    	if(!inpNum.matches("[A-Z]{3}[0-9]{5}"))
    	{
    		JOptionPane.showMessageDialog(gui, 
    			"Error: please check to make sure the order number has three uppercase alphabetic letters followed by five digits.", 
    			"Error", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	Object find = bst.find(inpNum); //Find the order number.
    	if(find == null) //If its not found.
    	{
    		JOptionPane.showMessageDialog(gui, 
    			String.format("The order number \"%s\" was not found, so it was not deleted.", inpNum), 
    			"Order Number Not Found", JOptionPane.INFORMATION_MESSAGE);
    	}
    	else //If it is found.
    	{
    		Order order = orderArray[((Index)find).getElement()]; //Gets the order object found from the array.
    		StringBuffer output = new StringBuffer(); //Output for the JOptionPane.
			output.append("Are you sure you want to delete this record permanently?\n\n");
    		output.append(String.format("Order Number: %s \n", order.getNumber()));
    		output.append(String.format("Order Date: %s \n", order.getOrderDate()));
    		output.append(String.format("Order Due Date: %s \n", order.getDueDate()));
    		output.append(String.format("Order Taker: %s \n", order.getOrderTaker()));
    		output.append(String.format("Order Price: %d \n", order.getPrice()));
    		output.append(String.format("Discount Qualification: %b \n", order.isDiscount()));
    		int option = JOptionPane.showConfirmDialog(gui,
    							output, "Order Number Found",
    							JOptionPane.YES_NO_OPTION);
    		num.setText(""); //Clears the JTextField for ease of new input.
    		
    		if(option == 0)
    		{   
    			boolean del = bst.delete(order.getNumber()); //Determines whether the delete was successful or not.

    			if(del)
    			{
    				JOptionPane.showMessageDialog(gui, "Record deleted successfully.", "Record Deleted",
    					JOptionPane.INFORMATION_MESSAGE);	
    				order.setNumber("********"); //Sets the order number "for deletion" by changing number to 8 asterisks.
    			}
    			else
    				JOptionPane.showMessageDialog(gui, "The record could not be found or could not be deleted.", "Record Not Deleted",
    					JOptionPane.INFORMATION_MESSAGE);	
    		}
    	}
    }
    
    private void findRecord(JFrame gui, JTextField num)
    {
    	//Performs the action of "finding a record".
    	String inpNum = num.getText(); //Order number from the JTextField.
    	if(!inpNum.matches("[A-Z]{3}[0-9]{5}"))
    	{
    		JOptionPane.showMessageDialog(gui, 
    			"Error: please check to make sure the order number has three uppercase alphabetic letters followed by five digits.", 
    			"Error", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	
    	Object find = bst.find(inpNum); //Finds the order number.
    	if(find == null) //If its not found.
    	{
    		JOptionPane.showMessageDialog(gui, 
    			String.format("The order number \"%s\" was not found.", inpNum), "Order Number Not Found",
    			JOptionPane.INFORMATION_MESSAGE);
    	}
    	else //If it is found.
    	{
    		Order order = (Order)orderArray[((Index)find).getElement()]; //Gets the order object found from the array.
      		StringBuffer output = new StringBuffer(); //Output for the JOptionPane.
      		output.append("Here is information of that order:\n\n");
    		output.append(String.format("Order Number: %s \n", order.getNumber()));
    		output.append(String.format("Order Date: %s \n", order.getOrderDate()));
    		output.append(String.format("Order Due Date: %s \n", order.getDueDate()));
    		output.append(String.format("Order Taker: %s \n", order.getOrderTaker()));
    		output.append(String.format("Order Price: %d \n", order.getPrice()));
    		output.append(String.format("Discount Qualification: %b \n", order.isDiscount()));
    		JOptionPane.showMessageDialog(gui,
    			output, "Order Number Found",
    			JOptionPane.INFORMATION_MESSAGE);
    		num.setText(""); //Clears the JTextField for ease of new input.
    	}
    }
    
    private void exit(JFrame gui)
    {
    	//Performs the actions upon exiting the program.
    	int option = JOptionPane.showConfirmDialog(gui, "Are you sure you want to exit?",
    						"Exit", JOptionPane.OK_CANCEL_OPTION); //Does the user want to exit or not?
    	
    	if(option == 0) //If they want to exit.
    	{
    		try
    		{
    			PrintWriter writer = new PrintWriter(new File("datafile.txt")); //Open up the data file for output.
    			for(int i = 0; i < orderArray.length; i++)
    			{
					Order order = orderArray[i];
	   				if(order != null && !order.getNumber().equals("********")) //Prints non-null and non-deleted records back to the file.
    					writer.println(order.toString());
    			}
    			writer.close();
    		}
    		catch(FileNotFoundException e)
    		{
    			JOptionPane.showMessageDialog(gui,
    				"The file could not be updated because it does not exist.", "Error",
    				JOptionPane.ERROR_MESSAGE);
    		}
    		gui.dispose(); //Closes the GUI.
    	}
    }
    
    private static Order[] increaseArraySize(Order[] array)
    {
    	//In case on program start up or an "add" causes the order array to go out of bounds, 
    	//this method doubles the array length to make sure it doesn't go out of bounds.
    	Order[] temp = new Order[array.length]; //Temp array.
    	int newLength = array.length * 2; //New array length.
    	
    	for(int i = 0; i < array.length; i++)
    		temp[i] = array[i];
    	
    	array = new Order[newLength];
    	
    	for(int i = 0; i < temp.length; i++)
    		array[i] = temp[i];
    	
    	return array;
    }
    
    private int getIndex(Order[] array)
    {
    	//Returns the next available index in the array.
    	int count; //Next available index in the array.
    	for(count = 0; count < array.length; count++)
    	{
    		if(array[count] == null)
    			return count;
    	}
    	
    	return count;
    }    
    
    private static void buildOrderArray(BinarySearchTree bst, Order[] orderArray, Scanner database)
    {
    	//Builds the order array based upon the datafile.txt records at the beginning of the program.
    	int ind = 0; //Current index of the array.
    	while(database.hasNext())
    	{
    		String nextLine = database.nextLine(); //Gets the next line.
    		String[] lineContents = nextLine.split("\t"); //Contains the content on the line.
    		
    		String orderNum = lineContents[0]; //Gets the order number.
    		String orderDate = lineContents[1]; //Gets the order date.
    		String dueDate = lineContents[2]; //Gets the order due date.
    		String orderTaker = lineContents[3]; //Gets the order taker.
    		String orderPrice = lineContents[4]; //Gets the order price.
    		String orderDiscount = lineContents[5]; //Gets the order discount qualification.
    		int price = Integer.parseInt(orderPrice); //Parses the price into an int value.
    		boolean discount = Boolean.parseBoolean(orderDiscount); //Parses the boolean into a boolean value.
    		Order newOrder = new Order(orderNum, orderDate, dueDate, orderTaker, price, discount); //Creates a new order.
    		
			if(ind == orderArray.length) //Check the size of the array in case it passed 400 values.
			{
				orderArray = increaseArraySize(orderArray);
				orderArray[ind] = newOrder;	
			}
			else
				orderArray[ind] = newOrder;
    		
    		Index index = new Index(orderNum, ind); //New index to add to the node of the binary search tree.
    		
    		bst.insert(index, orderNum);
    		ind++;
    	}
    	
    	database.close(); //Close the input file.
    }
    
    public static void main(String[] args)
    {
    	//Main class method to run the GUI.
    	try
    	{
	    	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    	}
    	catch(Exception e) { }

    	bst = new BinarySearchTree(); //Initializing the binary search tree.
    	orderArray = new Order[400]; //Initializing the order array.
    	try
    	{
    		Scanner database = new Scanner(new File("datafile.txt")); //Data file to read from.
    		buildOrderArray(bst, orderArray, database); //Builds the order array at the start of the program.
    	}
    	catch(FileNotFoundException e)
    	{
    		JOptionPane.showMessageDialog(null,
    			"There has been an error, please make sure that \"datafile.txt\" has been created and is in the correct location.",
    			"Error", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	gui = new DatabaseTreeGui(); //Creates the GUI.
    	gui.setTitle("Choose a File Option");
    	gui.setSize(385,325);
    	gui.setLocationRelativeTo(null);
    	gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	gui.setVisible(true);
    }
}