package HashTableCollisons;

/**
 * Order.java
 * Jordan Bossman
 * CSC 364
 * 4/9/2012
 * ADT class file for an Order object.
 * Tracks the order number, date, due date,
 * person who took the order, the price and
 * whether the order has  discount or not.
 */

public class Order implements Comparable
{
	private String number; //Order number of this order.
	private String orderDate; //Order date of this order.
	private String dueDate; //Order due date of this order.
	private String orderTaker; //Order taker of this order.
	private int price; //Price of this order.
	private boolean discount; //Discount qualification of this order.

    public Order(String number, String orderDate, String dueDate,
                            String orderTaker, int price, boolean discount)
    {
    	//Constructs an order object with the given information.
    	this.number = number;
    	this.orderDate = orderDate;
    	this.dueDate = dueDate;
    	this.orderTaker = orderTaker;
    	this.price = price;
    	this.discount = discount;
    }
    
    public void setNumber(String number)
    {
    	//Sets the order number in this order object with the passed in order number.
    	this.number = number;
    }
    
    public String getNumber()
    {
    	//Gets the order number of this order object.
    	return number;
    }
    
    public String getOrderDate()
    {
    	//Gets the order date of this object.
    	return orderDate;
    }
    
    public String getDueDate()
    {
    	//Gets the due date of this order object.
    	return dueDate;
    }
    
    public String getOrderTaker()
    {
    	//Gets the order taker of this order object.
    	return orderTaker;
    }
    
    public int getPrice()
    {
    	//Gets the price of this order object.
    	return price;
    }
    
    public boolean isDiscount()
    {
    	//Gets the discount qualification of this order object.
    	return discount;
    }
    
    public int compareTo(Object obj)
    {
    	//Compares the Order Number in this object with the string order number passed in.
    	if(obj instanceof String)
    		return number.compareTo((String)obj);
    	else
    		throw new ClassCastException();
    }
    
    public String toString()
    {
    	//Returns the Order Number that represents this object.
    	return number;
    }
}