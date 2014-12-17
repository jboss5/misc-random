package ObjOrientedStuff;/* Jordan Bossman
 * CSC 360
 * Programming Assignment #2
 * Due: October 14, 2011
 * Object class for faculty. Contains all the information about faculty.
 */

public class Faculty extends Employee 
{
	private double annualSalary;
	private int weeksPerYear;
	private String department;
	
	public Faculty(String name, int id, boolean isWorking, double annualSalary, int weeksPerYear, String department)
	{
		//Creates a faculty object.
		super(name, id, isWorking);
		this.annualSalary = annualSalary;
		this.weeksPerYear = weeksPerYear;
		this.department = department;		
	}
	
	public double getAnnualSalary()
	{
		//Returns the annual salary.
		return annualSalary;
	}
	
	public void setAnnualSalary(double annualSalary)
	{
		//Sets the annual salary.
		this.annualSalary = annualSalary;
	}
	
	public int getWeeksPerYear()
	{
		//Returns the weeks worked per year.
		return weeksPerYear;
	}

	public void setWeeksPerYear(int weeksPerYear)
	{
		//Sets the weeks worked per year.
		this.weeksPerYear = weeksPerYear;
	}
	
	public String getDepartment()
	{
		//Returns the department the faculty works in.
		return department;
	}
	
	public void setDepartment(String department)
	{
		//Sets the department the faculty works in.
		this.department = department;
	}
	
	public double getPay()
	{
		//Returns the pay. Double the weekly salary 
		//divided by the number of weeks worked per year.
		return (annualSalary * 2) / weeksPerYear;
	}
	
	public String toString()
	{
		//Prints out name, id, work status, annual salary, weeks worked per year and the department.
		return String.format("%-20s%-10d%-6b%-10.2f%-7d%-20s", getName(), getId(),
				             getWorkStatus(), annualSalary, weeksPerYear, department);
	}
}
