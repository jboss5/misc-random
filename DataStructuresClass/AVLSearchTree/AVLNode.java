package AVLSearchTree;

/**
 * AVLNode.java
 * Jordan Bossman
 * CSC 364
 * 2/25/2012
 * ADT class that creates an AVLNode.
 */

public class AVLNode 
{
	private Object data;  //Data stored in this node.
	private AVLNode left;  //Left child node of this node.
	private AVLNode right;  //Right child node of this node.
	private AVLNode parent;  //Parent node of this node.
	
    public AVLNode(Object data)
    {
    	//Creates an AVLNode object and sets the data this node holds.
    	this.data = data;
    } 
	
	public Object getData()
	{
		//Returns the data in this node.
		return data;
	}
	
	public void setData(Object data)
	{
		//Sets the data in this node.
		this.data = data;
	}
	
	public AVLNode getLeft()
	{
		//Gets the left child node of this node.
		return left;
	}
	
	public void setLeft(AVLNode node)
	{
		//Sets the left child node of this node.
		left = node;
	}
	
	public AVLNode getRight()
	{
		//Gets the right child node of this node.
		return right;
	}
	
	public void setRight(AVLNode node)
	{
		//Sets the right child node of this node.
		right = node;
	}
	
	public AVLNode getParent()
	{
		//Gets the parent node of this node.
		return parent;
	}
	
	public void setParent(AVLNode node)
	{
		//Sets the parent node of this node.
		parent = node;
	}
}