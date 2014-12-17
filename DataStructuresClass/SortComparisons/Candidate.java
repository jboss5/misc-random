package SortComparisons;

/**
 * Candidate.java
 * CSC 364
 * Jordan Bossman
 * 3/20/2012
 * ADT class for candidate objects.
 */

public class Candidate implements Comparable
{
	private int serial; //Serial number for this Candidate.
	private String string; //String for this Candidate.
	
    public Candidate(int serial, String string)
    {
    	this.serial = serial;
    	this.string = string;
    }
    
    public int getSerial()
    {
    	return serial;
    }
    
    public void setSerial(int serial)
    {
    	this.serial = serial;
    }
    
    public String getString()
    {
    	return string;
    }
    
    public void setString(String string)
    {
    	this.string = string;
    }
    
    public String toString()
    {
    	return "Serial: " + serial + " String: " + string;
    }
    
    public int compareTo(Object obj)
    {
    	//Compares the serial number of this Candidate with the given Candidate's serial number.
    	if(obj instanceof Candidate)
    		return serial - ((Candidate)obj).getSerial();
    	else
    		throw new ClassCastException();
    }    
}