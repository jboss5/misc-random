/*
 *	Jordan Bossman
 *	CSC464
 *	Pair.java
 *
 *	This is a class that simulates a Pair of values for the Knapsack problem. 
 *	Each pair consists of it's profit value and a boolean flag on whether
 *	or not the knapsack algorithm chose to put it in the bag.
 */

public class Pair 
{
	private boolean shouldTake; //Flag value for whether or not this project was taken
	private int maxProfit; //Profit value for this project
	
	public Pair(boolean shouldTake, int profit)
	{
		this.shouldTake = shouldTake;
		this.maxProfit = profit;
	}
	
	public boolean shouldTake()
	{
		return this.shouldTake;
	}
	
	public void setTakeValue(boolean val)
	{
		this.shouldTake = val;
	}
	
	public int getMaxProfit()
	{
		return this.maxProfit;
	}
	
	public void setMaxProfit(int profit)
	{
		this.maxProfit = profit;
	}
}
