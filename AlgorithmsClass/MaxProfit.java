/*
 *	Jordan Bossman
 *	CSC464
 * 	MaxProfit.java
 * 
 * 	Utilizes the Knapsack problem to find the most profitable projects
 * 	to work on the given input file. This knapsack algorithm finds the
 * 	most profitable project that limits the constraints used. This will
 * 	only work on a knapsack problem with 3 constraints (4 sections including # of projects).
 */

import java.util.*;
import java.io.*;

public class MaxProfit 
{
	//Change these variables if any of the constraints or the input filename changes.
	private final static int LIQUIDITY = 24;
	private final static int OFFICE_WORK_WEEKS = 36;
	private final static int PROGRAMMER_WORK_WEEKS = 80;
	private final static String FILE_NAME = "KnapsackData.txt";
	
	public static void main(String[] args)
	{
		Scanner scan;
		try
		{
			scan = new Scanner(new File(FILE_NAME));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Cannot find the input file, exiting...");
			return;
		}
		
		//Initialize the lists that will contain all of the information from the data file.
		//Also add the initial entry to each list for the base case
		ArrayList<String> nameList = new ArrayList<String>();
		nameList.add("");
		ArrayList<Integer> liquidityList = new ArrayList<Integer>();
		liquidityList.add(0);
		ArrayList<Integer> laborList = new ArrayList<Integer>();
		laborList.add(0);
		ArrayList<Integer> progList = new ArrayList<Integer>();
		progList.add(0);
		ArrayList<Integer> netProfitList = new ArrayList<Integer>();
		netProfitList.add(0);
		
		//Build the lists
		while(scan.hasNext())
		{
			String[] contents = scan.nextLine().split(" ");
			nameList.add(contents[0]);
			liquidityList.add(Integer.parseInt(contents[1]));
			laborList.add(Integer.parseInt(contents[2]));
			progList.add(Integer.parseInt(contents[3]));
			netProfitList.add(Integer.parseInt(contents[4]));
		}
		scan.close();

		findMaxProfit(nameList, liquidityList, laborList, progList, netProfitList);
	}
	
	private static int[] convertToPrimitive(ArrayList<Integer> list)
	{
		//This method converts the given int ArrayList to a primitive int array.
		int[] temp = new int[list.size()];
		
		for(int i = 0; i < temp.length; i++)
		{
			temp[i] = list.get(i).intValue();
		}
		
		return temp;
	}
	
	private static void writeOutput(int[] indexes, int usedLiq, int usedOffWrkWks, int usedProgWrkWks, 
									ArrayList<String> names, ArrayList<Integer> liquidities, 
									ArrayList<Integer> officeLabor, ArrayList<Integer> progLabor, 
									ArrayList<Integer> profit)
	{
		//This method outputs the information to a file called "output.txt"
		File file;
		PrintWriter out;
		int totalProfit = 0;
		
		try 
		{
			file = new File("output.txt");
			out = new PrintWriter(file);
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("Error writing output file, exiting...");
			return;
		}
		
		out.println("Available resources -");
		out.println(String.format("    Liqudity: %d", LIQUIDITY));
		out.println(String.format("    Office Work: %d", OFFICE_WORK_WEEKS));
		out.println(String.format("    Programming Work: %d", PROGRAMMER_WORK_WEEKS));
		out.println("Selected Projects -");
		
		//Print each project's information that was chosen
		for(int i = 0; i < indexes.length; i++)
		{
			String name = names.get(indexes[i]);
			int liq = liquidities.get(indexes[i]);
			int off = officeLabor.get(indexes[i]);
			int prog = progLabor.get(indexes[i]);
			int prof = profit.get(indexes[i]);
			totalProfit += prof;
			
			out.println(String.format("    %s %d %d %d %d", name, liq, off, prog, prof));
		}
		
		out.println("Total resources used -");
		out.println(String.format("    Liquidity: %d", LIQUIDITY - usedLiq));
		out.println(String.format("    Office Work: %d", OFFICE_WORK_WEEKS - usedOffWrkWks));
		out.println(String.format("    Programming Work: %d", PROGRAMMER_WORK_WEEKS - usedProgWrkWks));
		out.println();
		out.println(String.format("Total Profit: %d", totalProfit));
		out.close();
	}
	
	private static void findMaxProfit(ArrayList<String> names, ArrayList<Integer> liquidities, 
									 ArrayList<Integer> officeLabor, ArrayList<Integer> progLabor, 
									 ArrayList<Integer> profit)
	{
		//This method finds the best profit based on the constraints below
		final int PROJECTS = names.size() - 1;
		
		//Add 1 to each constraint so we can use the maximum as an array index
		Pair[][][][] knapsack = new Pair[LIQUIDITY + 1][OFFICE_WORK_WEEKS + 1][PROGRAMMER_WORK_WEEKS + 1][PROJECTS + 1];

		for(int m = 1; m < PROJECTS + 1; m++)
		{
			for(int i = 1; i < LIQUIDITY + 1; i++)
			{
				for(int j = 1; j < OFFICE_WORK_WEEKS + 1; j++)
				{
					for(int k = 1; k < PROGRAMMER_WORK_WEEKS + 1; k++)
					{
						int currentLiq = liquidities.get(m);
						int currentOffWks = officeLabor.get(m);
						int currentProgWks = progLabor.get(m);
						
						//Check to make sure that each value does not cause any negatives, if it does: exclude it.
						if(currentLiq > i || currentOffWks > j || currentProgWks > k)
						{
							knapsack[i][j][k][m] = knapsack[i][j][k][m - 1];
						}
						else
						{	
							//Grab the updated constraints with adding this project
							currentLiq = i - liquidities.get(m);
							currentOffWks = j - officeLabor.get(m);
							currentProgWks = k - progLabor.get(m);
							
							//If the project at m-1 happens to be null assume it is a project with 0 profit
							if(knapsack[i][j][k][m - 1] == null)
								knapsack[i][j][k][m - 1] = new Pair(false, 0);
							
							//Value with excluding current project
							int val1 = knapsack[i][j][k][m - 1].getMaxProfit();
							
							//If the project at m-1 with the updated constraints happens to be null is a project with 0 profit
							if(knapsack[currentLiq][currentOffWks][currentProgWks][m - 1] == null)
								knapsack[currentLiq][currentOffWks][currentProgWks][m - 1] = new Pair(false, 0);
							
							//Value with including current project
							int val2 = profit.get(m) + knapsack[currentLiq][currentOffWks][currentProgWks][m - 1].getMaxProfit();
							
							if(val2 > val1)
							{
								//Add the current project
								if(knapsack[i][j][k][m] == null)
									knapsack[i][j][k][m] = new Pair(true, val2);
								else
								{
									knapsack[i][j][k][m].setMaxProfit(val2);
									knapsack[i][j][k][m].setTakeValue(true);
								}
							}
							else
							{
								//Exclude the current project
								if(knapsack[i][j][k][m] == null)
									knapsack[i][j][k][m] = new Pair(false, val1);
								else
								{
									knapsack[i][j][k][m].setMaxProfit(val1);
									knapsack[i][j][k][m].setTakeValue(false);
								}
							}
						}
					}
				}
			}
		}
		
		ArrayList<Integer> output = new ArrayList<Integer>();
		
		//Count backwards from the end of the knapsack to grab the ones we chose
		int i = LIQUIDITY, j = OFFICE_WORK_WEEKS, k = PROGRAMMER_WORK_WEEKS;
		for(int m = PROJECTS - 1; m > 0; m--)
		{
			int tempI = i - liquidities.get(m);
			int tempJ = j - officeLabor.get(m);
			int tempK = k - progLabor.get(m);
				
			//Chose the ones that are flagged true and do not violate any of the constraint values
			if(knapsack[i][j][k][m].shouldTake() && tempI >= 0 && tempJ >= 0 && tempK >= 0)
			{
				output.add(m);
				i = tempI;
				j = tempJ;
				k = tempK;				
			}
		}
		
		//Sort the array and output the information
		int[] outputArray = convertToPrimitive(output);
		Arrays.sort(outputArray);
		writeOutput(outputArray, i, j, k, names, liquidities, officeLabor, progLabor, profit);
	}
}
