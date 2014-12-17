package ObjOrientedStuff;/* Jordan Bossman
 * CSC 360
 * Programming Assignment #2
 * Due: October 14, 2011
 * Object class for the classified staff. Contains information
 * on the classified staff.
 */

public class ClassifiedStaff extends Employee 
{
	private double weeklySalary;
	private String division;
	
	public ClassifiedStaff(String name, int id, boolean isWorking, double weeklySalary, String division)
	{
		//Creates a ClassifiedStaff object.
		super(name, id, isWorking);
		this.weeklySalary = weeklySalary;
		this.division = division;
	}
	
	public void setWeeklySalary(double weeklySalary)
	{
		//Sets the weekly salary.
		this.weeklySalary = weeklySalary;
	}
	
	public double getWeeklySalary()
	{
		//Returns the weekly salary.
		return weeklySalary;
	}
	
	public void setDivision(String division)
	{
		//Sets the division.
		this.division = division;
	}
	
	public String getDivision()
	{
		//Returns the division.
		return division;
	}
	
	public double getPay()
	{
		//Returns the pay. Paid double the weekly salary
		//for the two-week pay period.
		return 2 * weeklySalary;
	}
	
	public String toString()
	{
		//Prints out the name, id, work status, weekly salary and division.
		return String.format("%-20s%-10d%-6b%-10.2f%-20s", getName(), getId(), getWorkStatus(),
	                         weeklySalary, division);
	}

}
