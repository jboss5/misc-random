package ObjOrientedStuff;/* Jordan Bossman
 * CSC 360
 * Programming Assignment #2
 * Due: October 14, 2011
 * Main class to print out the pay amount for each employee
 * from the given text file "allemployees.txt".
 */

import java.io.*;
import java.util.Scanner;

public class Program4Demo 
{
	static Employee[] sortEmployees(Scanner input)
	{
		//Reads the files and places them into an array of Employee.
		int numStudEmp = input.nextInt();
		int numClassStaff = input.nextInt();
		int numFaculty = input.nextInt();
		
		//These values relate to the index of the temporary array that store the values
		//from the text file that are passed to the constructor to make the objects. 
		//These are constants so if later on the setup of the text file is changed,
		//the indexes can be easily changed.
		final int NAME = 0;
		final int ID = 1;
		final int WORK_STATUS = 2;
		final int HOURS_WORKED = 3;
		final int WORK_STUDY = 4;
		final int PAY_RATE = 5;
		final int WEEKLY_SALARY = 3;
		final int DIVISION = 4;
		final int ANNUAL_SALARY = 3;
		final int WEEKS_PER_YEAR = 4;
		final int DEPARTMENT = 5;
		
		//Temporary array to hold the contents of the read in information.
		String[] tempArray;
		String inputLine;
		int empIndex = 0;
		Employee[] employees = new Employee[numStudEmp + numClassStaff + numFaculty];
		input.nextLine();
		
		//Loops through the student employees, creates the objects and adds them to the Employee array.
		for(int i = 0; i < numStudEmp; i++)
		{
			inputLine = input.nextLine();
			tempArray = inputLine.split(",");
			employees[empIndex] = new StudentEmployee(tempArray[NAME], Integer.parseInt(tempArray[ID]), Boolean.parseBoolean(tempArray[WORK_STATUS]),
												      Integer.parseInt(tempArray[HOURS_WORKED]), Boolean.parseBoolean(tempArray[WORK_STUDY]), 
											          Double.parseDouble(tempArray[PAY_RATE]));
			empIndex++;
		}
		
		//Loops through the classified staff, creates the objects and adds them to the Employee array.
		for(int k = 0; k < numClassStaff; k++)
		{
			inputLine = input.nextLine();
			tempArray = inputLine.split(",");
			employees[empIndex] = new ClassifiedStaff(tempArray[NAME], Integer.parseInt(tempArray[ID]), Boolean.parseBoolean(tempArray[WORK_STATUS]), 
												      Double.parseDouble(tempArray[WEEKLY_SALARY]), tempArray[DIVISION]);
			empIndex++;
		}
		
		//Loops through the faculty, creates the objects and adds them to the Employee array.
		for(int j = 0; j < numFaculty; j++)
		{
			inputLine = input.nextLine();
			tempArray = inputLine.split(",");
			employees[empIndex] = new Faculty(tempArray[NAME], Integer.parseInt(tempArray[ID]), Boolean.parseBoolean(tempArray[WORK_STATUS]), 
                                              Double.parseDouble(tempArray[ANNUAL_SALARY]), Integer.parseInt(tempArray[WEEKS_PER_YEAR]), 
                                              tempArray[DEPARTMENT]);
			empIndex++;
		}
				
		return employees;
	}
	
	static void printEmployees(Employee[] empList)
	{
		//Prints out the information in a neat and clean format.
		for(int i = 0; i < empList.length; i++)
			System.out.println(empList[i].toString());
		
		System.out.printf("\n\n%32s", "Pay for two-week pay period");
		System.out.printf("\n%32s\n", "======================================");
		
		for(int k = 0; k < empList.length; k++)
		{
			if(empList[k].getWorkStatus())
				System.out.printf("%-30s$%.2f\n",empList[k].getName(),empList[k].getPay());
		}
	}
	
	public static void main(String[] args) 
	{
		//Reads the file containing employee information, sorts the employees,
		//prints out their toString() methods, and then prints out the
		//pay for two-week pay period for all working employees.
		try
		{
			Scanner input = new Scanner(new File("allemployees.txt"));
			Employee[] empList = sortEmployees(input);
			input.close();
			
			printEmployees(empList);
		}
		catch(FileNotFoundException e)
		{
			System.err.print(e);
		}

	}

}
