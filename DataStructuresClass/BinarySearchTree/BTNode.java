package BinarySearchTree;

/**
 * BTNode.java
 * Jordan Bossman
 * CSC 364
 * 2/10/2012
 * Class file for a Binary Tree node.
 */

public class BTNode
{	
	private Object data; //Data stored in this node.
	private BTNode left; //Reference to the left child of this node.
	private BTNode right; //Reference to the right child of this node.
	
    public BTNode(Object data) 
    {
    	//Constructs a BTNode object. Also assigns the given data to be
    	//the data of this object.
    	this.data = data;
    }
    
    public Object getData()
    {
    	//Gets the data of the current node.
    	return data;
    }
    
    public void setData(Object newData)
    {
    	//Sets the data of the current node.
    	data = newData;
    }
    
    public BTNode getLeft()
    {
    	//Gets the left child node of this node.
    	return left;
    }
    
    public void setLeft(BTNode node)
    {
    	//Sets the left child node of this node.
    	left = node;
    }
    
    public BTNode getRight()
    {
    	//Gets the right child node of this node.
    	return right;
    }
    
    public void setRight(BTNode node)
    {
        //Sets the right child node of this node.
    	right = node;
    }
}