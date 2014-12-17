package HashTableCollisons; /**
 * CollResoComparison.java
 * Jordan Bossman
 * CSC 364
 * 4/9/2012
 * Compares two different hash table collison resolution techniques:
 * separate chaining and double hashing. The program records the time
 * it takes for each technique to complete 1,000,000 finds.
 */

import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import java.util.Random;
import java.util.Scanner;

public class CollResoComparison 
{
    public static void main(String[] args) 
    {
    	Random rand = new Random(); //Random number to obtain random Order Numbers.
    	Scanner scan = null; //Scanner to read from the file.
    	File file = new File("project5.txt"); //The file to read from.
    	int length = getArrayLength(file); //Number of Order Numbers in the file.
    	String[] numArray = new String[length]; //The array of Order Numbers.
    	int[] timeArray = new int[8]; //The array of the time values for each find.
    	try
    	{
    		scan = new Scanner(file);
    	}
    	catch(FileNotFoundException e)
    	{
			JOptionPane.showMessageDialog(null, "Error", "The file \"project5.txt\" cannot be found. Closing...", 
    			JOptionPane.ERROR_MESSAGE);
    	}
    	int ind = 0; //Current index of the array.
    	while(scan.hasNext())
    	{
    		String nextLine = scan.nextLine(); //Gets the next line.
    		String[] lineContents = nextLine.split("\t"); //Contains the content on the line.
    		
    		numArray[ind] = lineContents[0];
    		ind++;
    	}
    	scan.close();
		
		int index = 0; //Index of the time array.
		for(int i = 0; i < 2; i++)
		{
			for(double k = 0.4; k < 1.1; k+=0.2) //Load factors: 0.4, 0.6, 0.8 and 1.0.
			{
				if(i == 0)
				{
					HashTableOA ht = new HashTableOA(length, k); //Hash table using Open Addressing.
					timeArray[index] = runHashTableOA(ht, file, rand, numArray, length);				
					index++;
				}
				else
				{
					HashTableSC ht = new HashTableSC(length, k); //Hash table using Separate Chaining.
					timeArray[index] = runHashTableSC(ht, file, rand, numArray, length);				
					index++;			
				}
			}
		}
		
		//Write the times to the file.
		try
		{
			File output = new File("outputTimes.txt");
			PrintWriter writer = new PrintWriter(output);
			writer.println("\t0.4\t0.6\t0.8\t1.0");
			writer.println(String.format("Double Hashing\t%d\t%d\t%d\t%d", timeArray[0], timeArray[1], timeArray[2], timeArray[3]));
			writer.println(String.format("Separate Chaining\t%d\t%d\t%d\t%d", timeArray[4], timeArray[5], timeArray[6], timeArray[7]));
			writer.close();
			
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Error with printing the output file. Closing...",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
    }
    
	private static int runHashTableOA(HashTableOA ht, File file, Random rand, String[] array, int length)
	{
		//Runs the 1,000,000 finds for the Open Addressing hash table.
		Scanner scan = null; //File reader.
 		try
 		{
 			 scan = new Scanner(file);
 		}
 		catch(FileNotFoundException e)
 		{
			JOptionPane.showMessageDialog(null, "Error", "The file \"project5.txt\" cannot be found. Closing...", 
 				JOptionPane.ERROR_MESSAGE);
 		}
 		
 		//Creates the Order Objects for the file.
 		while(scan.hasNext())
	  	{
    		String[] lineContents = scan.nextLine().split("\t");
		  		
    		String num = lineContents[0]; //Order Number.
    		String date = lineContents[1]; //Order Date.
    		String dueDate = lineContents[2]; //Order Due Date.
    		String taker = lineContents[3]; //Order taker.
    		int price = Integer.parseInt(lineContents[4]); //Order price.
    		boolean discount = Boolean.parseBoolean(lineContents[5]); //Order discount.
    		Order order = new Order(num, date, dueDate, taker, price, discount); //The Order Object stored in the Hash Table.
    		ht.insert(num, order);
    	}
    	scan.close();
    	
		long time1 = System.currentTimeMillis(); //First logged time.
		for(int j = 0; j < 1000000; j++)
		{
			int randNum = rand.nextInt(length); //Random order number.
			Object found = ht.find(array[randNum]); //Find the order number in the hash table.
			if(found == null || ((Comparable)found).compareTo(array[randNum]) != 0) 
			{
				//If the order number is not found or was wrong, print out the order number.
				System.out.println("Order Number " + array[randNum] + " not found in Open Addressing hash table.");
			}
		}
		long time2 = System.currentTimeMillis(); //Second logged time.
		
		return (int)(time2 - time1);		
	}
	
	private static int runHashTableSC(HashTableSC ht, File file, Random rand, String[] array, int length)
	{
		//Runs the 1,000,000 finds for the Separate Chaining hash table.
		Scanner scan = null; //File reader.
 		try
 		{
 			 scan = new Scanner(file);
 		}
 		catch(FileNotFoundException e)
 		{
			JOptionPane.showMessageDialog(null, "Error", "The file \"project5.txt\" cannot be found. Closing...", 
 				JOptionPane.ERROR_MESSAGE);
 		}
  	
  		while(scan.hasNext())
	  	{
    		String[] lineContents = scan.nextLine().split("\t");
		  		
    		String num = lineContents[0]; //Order number.
    		String date = lineContents[1]; //Order date.
    		String dueDate = lineContents[2]; //Order due date.
    		String taker = lineContents[3]; //Order taker.
    		int price = Integer.parseInt(lineContents[4]); //Order price.
    		boolean discount = Boolean.parseBoolean(lineContents[5]); //Order discount.
    		Order order = new Order(num, date, dueDate, taker, price, discount); //Order object stored in the hash table.
    		ht.insert(num, order);
    	}
    	scan.close();
    	
		long time1 = System.currentTimeMillis(); //First time.
		for(int j = 0; j < 1000000; j++)
		{
			int randNum = rand.nextInt(length); //Random order number from the array.
			Object found = ht.find(array[randNum]); //Find the order number in the hash table.
			if(found == null || ((Comparable)found).compareTo(array[randNum]) != 0) 
			{
				//If the order number is not found or was wrong, print out the order number.
				System.out.println("Order Number " + array[randNum] + " not found in Separate Chaining hash table.");
			}
		}
		long time2 = System.currentTimeMillis(); //Second time.
		
		return (int)(time2 - time1);				
	}
	
    private static int getArrayLength(File file)
    {
    	//Finds the number of Order Numbers in the given file.
    	Scanner scan = null; //File reader.
    	try
    	{
    		scan = new Scanner(file);    		
    	}
    	catch(FileNotFoundException e)
    	{
			JOptionPane.showMessageDialog(null, "Error", "The file \"project5.txt\" cannot be found. Closing...", 
    			JOptionPane.ERROR_MESSAGE);
    	}
		
		int length = 0; //Number of Order Numbers.
    	while(scan.hasNextLine())
    	{
    		length++;
    		scan.nextLine();
    	}
    	scan.close();
    	
    	return length;
    }
}