package ObjOrientedStuff;/* Jordan Bossman
 * CSC 360
 * Programming Assignment #2
 * Due: October 14, 2011
 * Object class for subclass StudentEmployee. Records information specific about
 * student employees.
 */

public class StudentEmployee extends Employee 
{
	private int hoursWorked;
	private boolean isWorkStudy;
	private double payRate;
	
	public StudentEmployee(String name, int id, boolean isWorking, int hoursWorked, boolean isWorkStudy, double payRate)
	{
		//Creates a StudentEmployee object.
		super(name, id, isWorking);
		this.isWorkStudy = isWorkStudy;
		this.hoursWorked = hoursWorked;
		this.payRate = payRate;
	}
	
	public void setHoursWorked(int hoursWorked)
	{
		//Sets the hours worked.
		this.hoursWorked = hoursWorked;
	}
	
	public int getHoursWorked()
	{
		//Returns the hours worked.
		return hoursWorked;
	}
	
	public void setWorkStudy(boolean isWorkStudy)
	{
		//Sets if the student is on a work study.
		this.isWorkStudy = isWorkStudy;
	}
	
	public boolean getWorkStudy()
	{
		//Returns if the student is on a work study.
		return isWorkStudy;
	}
	
	public void setPayRate(double payRate)
	{
		//Sets the pay rate.
		this.payRate = payRate;
	}
	
	public double getPay()
	{
		//Returns the pay.
		return hoursWorked * payRate;
	}
	
	public String toString()
	{
		//Prints out the name, id, work status, hours worked, work study and pay rate.
		return String.format("%-20s%-10d%-6b%-10d%-6b%6.2f", getName(), getId(), getWorkStatus(),
	                         hoursWorked, isWorkStudy, payRate);
	}
	
}
