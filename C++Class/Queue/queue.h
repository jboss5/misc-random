/*
	Jordan Bossman
	CSC 402
	Project #3
	Queue template class implmentation.
*/

#include "linkedlist.h";
#include <iostream>;

#ifndef QUEUE_H
#define QUEUE_H

template<typename Obj>
class Queue
{
	public:
		Queue()
			:list(new LinkedList<Obj>()), length(0)
		{
			//No-arg constructor.
		}

		const Queue & operator=(const Queue & rhs)
		{
			//Performs the operator= function on this object and an object passed in.
			//Utilizes the operator= of the LinkedList.
			if(this != &rhs)
				list = rhs.getList();
		
			return *this;
		}

		~Queue()
		{
			//Deconstructor, tell the list to perform its deconstructor.
			delete list;
		}

		void enqueue(Obj * data)
		{
			//Adds a node to the list with the given data.
			list = list->add(data);
			length++;
		}

		Obj * dequeue()
		{
			//Pops off the node at index[0] in the list and returns the data.
			Obj * temp = list->atIndex(0);
			list->del(0);
			length--;
			return temp;
		}

		int size()
		{
			//Returns the list's size.
			return list->getLength();
		}

		bool isEmpty()
		{
			//Is the list empty?
			return list->isEmpty();
		}

		void print()
		{
			//Prints the list out.
			list->printList();
		}

		LinkedList<Obj> & getList()
		{
			//Returns the LinkedList.
			return list;
		}

		void clear()
		{
			//Clears the list by deconstructing it, then making a new list.
			delete list;
			list = new LinkedList<Obj>();
			length = 0; //Reset the length.
		}
	
	private:
		LinkedList<Obj> * list; //List that is the queue.
		int length; //Length of the queue.
};


#endif