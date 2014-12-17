package AVLSearchTree;

/**
 * AVLTree.java
 * Jordan Bossman
 * CSC 364
 * 2/25/2012
 * Class for finding, inserting, deleting and 
 * balancing an AVL Tree.
 */

public class AVLTree 
{
	private AVLNode root;  //Root node of the tree.

    public AVLTree() 
    {
    }
    
    public void insert(Object data, String key)
    {
    	//Inserts the data with the given string key in its correct position.
    	//After insertion, checks to make sure the tree is balanced.
    	if(root == null)
    		root = new AVLNode(data);
    	else
    	{
    		AVLNode currentNode = root;  //Sets the current node as the root to start off.
    		while(currentNode != null)
    		{
    			if((((Comparable)currentNode.getData()).compareTo(key)) > 0)
    			{
    				if(currentNode.getLeft() == null)
    				{
    					AVLNode newNode = new AVLNode(data);  //New AVLNode added to the tree.
    					newNode.setParent(currentNode);
    					currentNode.setLeft(newNode);
    					checkBalance(newNode);
    					return;
    				}
    				else
    					currentNode = currentNode.getLeft();
    			}
    			else
    			{
    				if(currentNode.getRight() == null)
    				{
    					AVLNode newNode = new AVLNode(data);  //New AVLNode added to the tree.
    					newNode.setParent(currentNode);
    					currentNode.setRight(newNode);
    					checkBalance(newNode);
    					return;
    				}
    				else
    					currentNode = currentNode.getRight();
    			}
    		}
    	}
    }
   
    private void checkBalance(AVLNode node)
    {
    	//Checks to see if the AVL tree is in balance, if it is not it performs a rotation to the shortest
    	//sub tree starting at the current node. 
    	if(node != null)
		{
        	int rightDepth = getDepth(node.getRight(), 0); //Depth of the right sub tree starting at the current node.
        	int leftDepth = getDepth(node.getLeft(), 0);  //Depth of the left sub tree starting at the current node.
        	
		   	if(Math.abs(rightDepth - leftDepth) > 1)
        	{
        		if(rightDepth < leftDepth)
        			rightRotate(node);
        		else
           			leftRotate(node);
        	}
		   	else
		    	checkBalance(node.getParent());
		}
    }

	
    private void rightRotate(AVLNode node)
    {
    	//Right side rotation with the given AVLNode in an AVL tree.
    	AVLNode left = node.getLeft();  //Left child node of the current node.
		AVLNode lRight = left.getRight();  //Right child node of the left child of the current node.

		left.setParent(node.getParent());
		if(node == root)
			root = left;
		else if(node == node.getParent().getLeft())
			node.getParent().setLeft(left);
		else
			node.getParent().setRight(left);
		node.setParent(left);
		left.setRight(node);
		node.setLeft(lRight);  
		if(lRight != null)
			lRight.setParent(node);

		checkBalance(left.getParent());
    }
    
    private void leftRotate(AVLNode node)
    {
    	//Left side rotation with the given AVLNode in an AVL tree.
    	AVLNode right = node.getRight();  //Right child node of the current node.
		AVLNode rLeft = right.getLeft();  //Left child node of the right child node of the current node.
			
		right.setParent(node.getParent());
		if(node == root)
			root = right;
		else if(node == node.getParent().getLeft())
			node.getParent().setLeft(right);
		else
			node.getParent().setRight(right);
		node.setParent(right);
		right.setLeft(node);
		node.setRight(rLeft);  
		if(rLeft != null)
			rLeft.setParent(node);

		checkBalance(right.getParent());
    }
    
    
	private int getDepth(AVLNode node, int num)
	{
		//Accumulates the depth of a branch from the node given.
		if(node == null)
			return num;
		
		if(node.getLeft() == null)
			return getDepth(node.getRight(), num + 1);
		else if(node.getRight() == null)
			return getDepth(node.getLeft(), num + 1);
		//If both children are not null determine which sub branch is the longest and recurse down it.
		else
		{
			if(getDepth(node.getLeft(), 0) < getDepth(node.getRight(), 0))
				return getDepth(node.getLeft(), num + 1);
			else
				return getDepth(node.getRight(), num + 1);
		}
	}
	
    public boolean delete(String key)
    {
    	//Deletes a node from the tree with the given key.
    	if(find(key) == null)
    		return false;
    	else
    		return delete(key, root);
    }
    
    private boolean delete(Object data, AVLNode newNode)
    {
    	//Deletes the node with the given data from the AVL tree. 
    	//After deletion, checks to make sure the tree is still balanced.
		AVLNode currentNode = newNode; //The node the program is currently on.
		boolean found = false; //Whether the node has been found yet or not.
		
		//Find the node to be deleted.
		while(!found)
		{
			if(((Comparable)currentNode.getData()).compareTo(data) == 0)
				found = true;
    		else if(((Comparable)currentNode.getData()).compareTo(data) < 0)
   	 			currentNode = currentNode.getRight();
    		else
    			currentNode = currentNode.getLeft();
		}
		
		AVLNode parent = currentNode.getParent(); //Parent of the current node.
		
		//If the node has no children.
		if(currentNode.getRight() == null && currentNode.getLeft() == null)
		{
			if(((Comparable)parent.getData()).compareTo(data) < 0)
				parent.setRight(null);
			else
				parent.setLeft(null);
				
			checkBalance(currentNode);
			return true;
		}
		
		//If the node has one child.
		else if((currentNode.getRight() != null && currentNode.getLeft() == null) ||
				(currentNode.getLeft() != null && currentNode.getRight() == null))
		{
			if(currentNode.getRight() != null)
    		{
				if(((Comparable)parent.getData()).compareTo(data) < 0)
					parent.setRight(currentNode.getLeft());
				else
					parent.setLeft(currentNode.getRight());
    		}
    		else
    		{
				if(((Comparable)currentNode.getParent().getData()).compareTo(data) < 0)
					parent.setLeft(currentNode.getLeft());
				else
					parent.setRight(currentNode.getRight());
    		}
    		
			checkBalance(currentNode);
			return true;
   		 }
   		 
   		//If the node has two children.
   		else if(currentNode.getRight() != null && currentNode.getLeft() != null)
	    {
    		AVLNode highestNode = currentNode.getLeft(); //Highest node in the left sub branch.
    		AVLNode node = currentNode.getLeft();  //The current node.
    		//Find the highest valued node in the left sub branch.
  			while(node != null) 
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
    
    public Object find(String key)
    {
    	//Finds a node in the AVL tree with the given string key.
    	//Returns the data housed in that node or null if not found.
    	AVLNode currentNode = root;  //The current node.
    	
    	while(currentNode != null)
    	{
    		if((((Comparable)currentNode.getData()).compareTo(key)) == 0)
    			return currentNode.getData();
    		else if((((Comparable)currentNode.getData()).compareTo(key)) > 0)
    			currentNode = currentNode.getLeft();
    		else
    			currentNode = currentNode.getRight();
    	}
    	
    	return null;
    }
    
    public void clear()
    {
    	//Clears the AVL tree.
    	root = null;
    }
}