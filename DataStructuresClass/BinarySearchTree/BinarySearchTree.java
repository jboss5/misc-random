package BinarySearchTree;

/**
 * BinarySearchTree.java
 * Jordan Bossman
 * CSC 364
 * 2/10/2012
 * Class for managing, inserting nodes and
 * deleting nodes for a Binary Search Tree
 * data structure.
 */

public class BinarySearchTree
{
	private BTNode root; //Root node of this binary search tree.

    public BinarySearchTree()
    {
    	//Creates a new binary search tree.
    }
    
    public void insert(Object data, String orderNum)
    {
    	//Method to insert data into the correct location in the tree.
    	if(root == null) //If the root hasn't been filled, fill it with a new node.
    		root = new BTNode(data);
    	else
    	{
    		BTNode currentNode = root;
    		while(currentNode != null)
    		{
    			//If the data is less than the root, go to the left node.
    			if(((Comparable)currentNode.getData()).compareTo(orderNum) > 0)
    			{
    				if(currentNode.getLeft() == null)
    				{   
    					BTNode newNode = new BTNode(data);
    					currentNode.setLeft(newNode);
    					return;
    				}
    				else
    					currentNode = currentNode.getLeft();
    			}
    			//If the data is greater than the root, go to the left node.
    			else
    			{
    				if(currentNode.getRight() == null)
    				{   
    					BTNode newNode = new BTNode(data);
    					currentNode.setRight(newNode);
    					return;
    				}
    				else
    					currentNode = currentNode.getRight();
    			}
    		}
    	}
    }
     
    private boolean delete(Object data, BTNode newNode)
    {
    	//Method that deletes a node based on how many children it has.
    	//If it has no children, just delete the node. If it has one child,
    	//just assign the child to the parent of this node. If it has two
    	//children, figure out the highest left sub branch value and swap it
    	//with the node to be deleted. Then recursively delete to take that
    	//node out of the tree starting at the node you just swapped.
		BTNode currentNode = newNode; //The node the program is currently on.
		BTNode parentNode = newNode; //The parent node of the current node.
		boolean found = false; //Whether the node has been found yet or not.
		
		//Find the node to be deleted and its parent node.
		while(!found)
		{
			if(((Comparable)currentNode.getData()).compareTo(data) == 0)
				found = true;
    		else if(((Comparable)currentNode.getData()).compareTo(data) < 0)
    		{
    			parentNode = currentNode;
   	 			currentNode = currentNode.getRight();
    		}
    		else
    		{
    			parentNode = currentNode;
    			currentNode = currentNode.getLeft();
    		}
		}
		
		//If the node has no children.
		if(currentNode.getRight() == null && currentNode.getLeft() == null)
		{
			if(((Comparable)parentNode.getData()).compareTo(data) < 0)
				parentNode.setRight(null);
			else
				parentNode.setLeft(null);
				
			return true;
		}
		
		//If the node has one child.
		else if((currentNode.getRight() != null && currentNode.getLeft() == null) ||
				(currentNode.getLeft() != null && currentNode.getRight() == null))
		{
			if(currentNode.getRight() != null)
    		{
				if(((Comparable)parentNode.getData()).compareTo(data) < 0)
					parentNode.setRight(currentNode.getLeft());
				else
					parentNode.setLeft(currentNode.getRight());
    		}
    		else
    		{
				if(((Comparable)parentNode.getData()).compareTo(data) < 0)
					parentNode.setLeft(currentNode.getLeft());
				else
					parentNode.setRight(currentNode.getRight());
    		}
    		return true;
   		 }
   		 
   		//If the node has two children.
   		else if(currentNode.getRight() != null && currentNode.getLeft() != null)
	    {
    		BTNode highestNode = currentNode.getLeft();
    		BTNode node = currentNode.getLeft();
  			while(node != null) //Find the highest valued node in the left sub branch.
   			{
				if(node.getRight() != null)
					node = node.getRight();
				else
				{
					highestNode = node;
					break;
				}
   			}
    		currentNode.setData(highestNode.getData());
    		highestNode.setData(data);
    		return delete(data, highestNode);
	    }
	    
    	return false;			
    }
    public boolean delete(Object data)
    {
		//This method requires a given object and calls a new delete
		//method that starts at the given node, finds the node housing
		//that object and deletes it out of the tree.
    	if(find(data) == null) //If the value at the start isn't in the tree.
    		return false;
    	else
    		return delete(data, root); //Start the delete off at the root.
    }
    
    public Object find(Object data)
    {
    	//Method that finds the data in the tree. It returns the data if it
    	//finds it, or returns null to signify the data is not in this tree.
    	BTNode current = root;
    	while(current != null)
    	{
    		//If the data is equal to the data of this node, return the data;
    		//otherwise, determine whether to move to the left or right node.
    		if(((Comparable)current.getData()).compareTo(data) == 0)
    			return current.getData();
    		else if(((Comparable)current.getData()).compareTo(data) > 0)
				current = current.getLeft();
    		else
    			current = current.getRight();
    	}
    	//Return null if not found.
    	return null;
    }
    
    public void clear()
    {
    	//Sets the root equal to null, which destroys the entire tree.
    	root = null;
    }    
}