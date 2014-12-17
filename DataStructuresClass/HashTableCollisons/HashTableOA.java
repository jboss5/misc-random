package HashTableCollisons; /**
 * HashTableOA.java
 * Jordan Bossman
 * CSC 364
 * 4/9/2012
 * Hash Table class that uses Double Hashing as its
 * collison resolution technique.
 */

import javax.swing.JOptionPane;

public class HashTableOA 
{
	private Object[] array; //The hash table array.
	private boolean[] delArray; //The boolean deletion array.
	
    public HashTableOA(int num, double load)
  	{
    	int length = nextPrime((int)(num / load)); //Length of the array based on the load factor.
    	
    	//If for some reason there was an issue getting the next prime, exit.
    	if(length == -1)
    	{
    		JOptionPane.showMessageDialog(null, "Error", "There has been an error... Exiting...",
    		  JOptionPane.ERROR_MESSAGE);
    	}
    	else
    	{
    		array = new Object[length]; //Creates the main hash array.
    		delArray = new boolean[length]; //Creates the deletion boolean array.
    	}
    }
    
    private int numRecords()
    {
    	//Returns the number of records in the hash table.
    	int num = 0; //Number of records.
    	for(int i = 0; i < array.length; i++)
    	{
    		if(array[i] != null)
    			num++;
    	}
    	
    	return num;
    }
    
    private boolean isPrime(int num)
    {
    	//Determines if the int passed in is prime or not.
    	if(num % 2 != 0 && num % 3 != 0 && num % 5 != 0 && num % 7 != 0)
    		return true;
    	else
    		return false;
    }
    
    private int nextPrime(int num)
    {
    	//Finds the next prime number based on the input number.
    	int end = num * 10; //Set an end so the loop doesn't accidentally create an infinite loop.
		for(int i = num + 1; i < end; i++)
		{
			if(isPrime(i))
				return i;
		}
		return -1;
    }
    
    private int bConcatVal(String key)
    {
    	//Performs Binary Concatentation of the string key passed in.
    	long element = 0; //Element number.
    	int up = 0; //Up counter.
    	int pow = key.length() - 1; //Down counter.
    	
    	while(up < key.length())
    	{
			//Binary Concatenation: Take each character in the key, mod by 64 to assign the numbers
    		//1-26 for each alphabetic character and the numbers 47-57 for any numerics. Then take that value
    		//and multiply it by 32^(end of string minus the character you're on).
    		element += ((key.charAt(up) % 64) * Math.pow(32, pow));
    		up++;
    		pow--;
    	}

    	return (int)(element % array.length);
    }
    
    private int keyVal(String key)
    {
    	//Returns the integer value of the key passed in. Not the hash of the key.
    	int val = 0; //Value of the key.

    	for(int up = 0; up < key.length(); up++)
    		val += key.charAt(up) % 64; //Mods each character by 64 to get alphabetics to 1-26 and numerics to 47-57.
    		
    	return val;
    }
    
    private int hash(String key)
    {
    	//Hashes the given key.
    	if(!isPrime(numRecords()))
    		resizeArray();
    	
    	int element = bConcatVal(key); //Element hash of the key.
    	int val = keyVal(key); //Value of the key.

    	//Double hashing collision resolution.
    	if(array[element] == null)
    		return element;
    	else
    	{
    		while(array[element] != null)
    		{
	   			element += (val % 7) + 1;
				if(element > array.length - 1)
    				element %= numRecords();
    		}
    		return element;
    	}	
    }
    
    private void resizeArray()
    {
    	//Resizes the boolean and hash table arrays.
    	int length = nextPrime(array.length); //Next prime number for length.
    	//int numRecords = numRecords(); //Number of records in the hash table.
    	Object[] temp = new Object[array.length]; //Temporary hash table.
    	boolean[] tempDel = new boolean[delArray.length]; //Temporary boolean array.
    	for(int i = 0; i < temp.length; i++)
    	{
    		temp[i] = array[i];
    		tempDel[i] = delArray[i];
    	}
    	
    	array = new Object[length]; //Create the new hash table with the next prime length.
    	delArray = new boolean[length]; //Create the new deletion array with the next prime length.
    	
    	for(int i = 0; i < temp.length; i++)
    	{
    		//Rehash each key to put it in its proper place with the new table length.
    		if(temp[i] != null)
    		{
    			String key = temp[i].toString(); //The key.
    			int element = bConcatVal(key); //The element number.
    			int val = keyVal(key); //The key value.

    			while(array[element] != null)
        		{
        			element += (val % 7) + 1;
    				if(element > array.length - 1)
    					element %= numRecords();
        		} 
        		array[element] = temp[i];
        		delArray[element] = tempDel[i];
    		}
    	}
    }
        
    public void insert(String key, Object data)
    {
    	//Hashes the given key and inserts the data at the returned element.
    	int element = hash(key); //Hashed element number.
    	array[element] = data;
    }
    
    public Object find(String key)
    {
    	//Finds the data at a given key.
    	int element = bConcatVal(key); //The hashed element number.
    	int val = keyVal(key); //The key value.
    	
    	if(array[element] != null)
    	{
   			if(((Comparable)array[element]).compareTo(key) == 0)
    			return array[element];
    	}

    	int start = 0; //Start number to determine if the loop has gone through all the spots in the array.
    	int numRecords = numRecords(); //The number of records.
    	while((start != array.length) || (array[element] != null && delArray[element] != false))
		{
   			element += (val % 7) + 1;
			if(element > array.length - 1)
				element %= numRecords;

				if((array[element] != null) && ((Comparable)array[element]).compareTo(key) == 0)
					return array[element];

			start++;
		} 
    	return null;
    }
    
    public boolean delete(String key)
    {
    	//Deletes data from the array based on the given key.
    	int element = bConcatVal(key); //Hashed element number.
    	int val = keyVal(key); //The key value.
    	
    	if(array[element] != null)
    	{
    		if(((Comparable)array[element]).compareTo(key) == 0)
    		{
    			array[element] = null;
    			delArray[element] = true;
    			return true;
    		}
    	}
    	
		int start = 0; //Start number to determine if the loop has gone through all the array elements.
    	int numRecords = numRecords(); //The number of records.
    	while((start != array.length) || (array[element] != null && delArray[element] != false))
		{
   			element += (val % 7) + 1;
			if(element > array.length - 1)
				element %= numRecords;

				if((array[element] != null) && ((Comparable)array[element]).compareTo(key) == 0)
				{
					array[element] = null;
					delArray[element] = true;
					return true;
				}
			start++;	
		}
    	return false;
    }
}