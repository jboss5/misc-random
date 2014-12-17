package AVLSearchTree;

/**
 * Index.java
 * Jordan Bossman
 * CSC 364
 * 2/10/2012
 * Class file for an Index object. The index object
 * is the data for a BTNode in a binary search tree
 * for an order object.
 */

public class Index implements Comparable
{
	private String key; //The order number of the order linked to this object.
	private int element; //Element number of the order in the order array.

    public Index(String number, int index) 
    {
    	//Constructs an index object with the given information.
    	key = number;
    	element = index;
    }
    
    public String getKey()
    {
    	//Returns the order number stored in this object.
    	return key;
    }
    
    public int getElement()
    {
    	//Returns the element number of the array of where the order object
    	//with the stored key is housed.
    	return element;
    }
    
    public int compareTo(Object obj)
    {
    	//Compares the order number in this object with a passed in order number.
    	if(obj instanceof String)
			return key.compareTo((String)obj);
		else	
			throw new ClassCastException();
	}
}