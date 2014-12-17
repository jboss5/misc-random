package ObjOrientedStuff;/* Jordan Bossman
 * CSC 360
 * Programming Assignment #2
 * Due: October 14, 2011
 * Object class for employees.
 */

public class Employee 
{
	private String name;
	private int id;
	private boolean isWorking;
	
	public Employee(String name, int id, boolean isWorking)
	{
		//Constructs an Employee object.
		this.name = name;
		this.id = id;
		this.isWorking = isWorking;
	}
	
	public String getName()
	{
		//Returns the name of the employee.
		return name;
	}
	
	public int getId()
	{
		//Returns the ID of the employee.
		return id;
	}
	
	public boolean getWorkStatus()
	{
		//Returns the work status of an employee.
		return isWorking;
	}
	
	public void setWorkStatus(boolean status)
	{
		//Sets the work status of an employee.
		isWorking = status;
	}
	
	public double getPay()
	{
		//This will be overridden in subclasses. Returns 0 because no one knows
		//how much a general employee is paid, only a specific employee.
		return 0.0;
	}
	
	public String toString()
	{
		//Default constructor just prints out the name, id and work status.
		return String.format("Name: %s Id: %d Work Status: %b", name, id, isWorking);
	}
}
