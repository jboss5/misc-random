package HashTableCollisons; /**
 * HashTableSC.java
 * Jordan Bossman
 * CSC 364
 * 4/9/2012
 * Hash Table class that uses Separate Chaining
 * as its collison resolution technique.
 */

import java.util.LinkedList;

public class HashTableSC 
{
	private LinkedList[] listArray; //Array of LinkedLists.
	
    public HashTableSC(int num, double load) 
    {
    	int length = (int)(num / load); //Length of the array based on the load size.
    	listArray = new LinkedList[length];	//Creates the array of LinkedLists.
    }

    private int hash(String key)
    {
    	//Hashes the String parameter using Binary Concatenation.
    	long element = 0; //Element number.
    	int up = 0; //Up counter in the key.
    	int pow = key.length() - 1; //Down counter in the key.
    	
    	while(up < key.length())
    	{
    		//Binary Concatenation: Take each character in the key, mod by 64 to assign the numbers
    		//1-26 for each alphabetic character and the numbers 47-57 for any numerics. Then take that value
    		//and multiply it by 32^(end of string minus the character you're on).
    		element += ((key.charAt(up) % 64) * Math.pow(32, pow));
    		up++;
    		pow--;
    	}

    	element %= listArray.length;
    	
    	//Separate Chaining collision resolution.
    	if(listArray[(int)element] == null)
    		listArray[(int)element] = new LinkedList();
    	
    	return (int)element;
    }
    
    public void insert(String key, Object data)
    {
    	//Inserts the given data to the spot in the LinkedList array based on the hashed key.
    	int element = hash(key); //Hash of the key.
   		listArray[element].add(data);
    }
    
    public Object find(String key)
    {
    	//Finds an element in the LinkedList array based on the hashed key.
		int element = hash(key); //Hash of the key.
		LinkedList list = listArray[element]; //First LinkedList object at the beginning of that row.
		if(list == null)
			return null;
		else
		{
			for(int index = 0; index < list.size(); index++)
			{
				if(((Comparable)list.get(index)).compareTo(key) == 0)
					return list.get(index);
			}
		}
		return null;
    }
    
    public boolean delete(String key)
    {
    	//Deletes a record from the LinkedList array based on the hashed key.
    	int element = hash(key); //Hash of the key.
    	LinkedList list = listArray[element]; //First LinkedList object at the beginning of that row.
    	if(list != null)
    	{
    		for(int index = 0; index < list.size(); index++)
    		{
    			if(((Comparable)list.get(index)).compareTo(key) == 0)
    			{
    				list.remove(index);
    				return true;
    			}
    		}
    	}
   		return false;
    }
}