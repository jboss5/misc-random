/*
	Jordan Bossman
	CSC 402
	Project #3
	LinkedList template class implementation.
*/

#include <stdio.h>

#ifndef LINKEDLIST_H
#define LINKEDLIST_H

//Struct that contains the information needed for nodes added to the LinkedList.
//The struct is a template type too.
template<typename Obj>
struct Node
{
	Obj * data;
	Node * next;
};

//Template class for LinkedList. Each "element" of the list contains a node defined above.
template<typename Obj>
class LinkedList
{
	public:
		LinkedList() //No-arg constructor.
			:head(NULL), length(0)
		{
		}

		const LinkedList<Obj> & operator=(const LinkedList<Obj> & rhs)
		{
			//Performs the operator= function on this object and an object passed in.
			if(this != &rhs)
			{
				Node<Obj> * temp = rhs.getFront();

				//Add each node from the rhs list to the current list.
				while(temp != NULL)
				{
					Obj & data = *(temp->data);
					add(&data);
					temp = temp->next;
				}
			}
		
			return *this;
		}

		~LinkedList()
		{
			//Deconstructor, deletes each node inside the list.
			Node<Obj> * current;
			Node<Obj> * temp = getFront();
			int i = 0;

			//While there are nodes left in the list.
			while(temp != NULL && i < length)
			{
				current = temp->next;
				delete temp;
				temp = current;
				i++;
			}
		}

		Node<Obj> * getFront() const
		{
			//Returns the head of the list.
			return head;
		}
		
		LinkedList<Obj> * add(Obj * newData)
		{
			//Add function to add the node at the end of the list.
			if(head == NULL)
			{
				head = new Node<Obj>;
				head->data = newData;
			}
			else
			{
				Node<Obj> * temp = findOpen();
				Node<Obj> * newNode = new Node<Obj>;
				newNode->data = newData;
				temp->next = newNode;
			}

			//Keep track of the length of the list.
			length++;
			return this;
		}

		LinkedList<Obj> * add(int index, Obj * newData)
		{
			//Add function to add a node at a specific location in the list.
			if(index == 0)
			{
				head = new Node<Obj>;
				head->data = newData;
			}
			else if(index >= length)
				add(newData);
			else
			{
				//Keep track of the parent, current and child for re-ordering of the nodes.
				Node<Obj> * parent = findNode(index - 1);
				Node<Obj> * child = parent->next;
				Node<Obj> * current;

				current->data = newData;
				current->next = child;
				parent->next = current;
			}

			length++;
			return *this;
		}

		LinkedList<Obj> * del(int index)
		{
			//Delete function to delete the given node.
			if(index == 0)
			{
				//Deletes the head but makes the head's child the new head.
				Node<Obj> * current = head->next;
				delete(head);
				head = current;
			}
			else if(index > 0)
			{
				//Keep track of the nodes for re-ordering so the current can be deleted.
				Node<Obj> * parent = findNodeAt(index - 1);
				Node<Obj> * current = findNodeAt(index);
				Node<Obj> * child = current->next;

				parent->next = child;
				delete(current);
			}
			else
				return NULL; //Return NULL if there was a problem.

			length--;
			return this;
		}

		Obj * atIndex(int index)
		{
			//Returns the data at the given index.
			Node<Obj> * temp = head;
			int i = 0;

			//While i is inside the bounds of the list.
			while(i < length)
			{
				if(i == index)
					return temp->data;
				else
				{
					temp = temp->next;
					i++;
				}
			}

			return temp->data;
		}
		
		int findIndex(const Obj & newData)
		{
			//Returns the index of a node given the data.
			Node * temp = head;
			int i = 0;

			//While there are nodes left in the list.
			while(temp != NULL)
			{
				if(temp->data == newData)
					return i;
				else
				{
					temp = temp->next;
					i++;
				}
			}
			
			return -1; //-1 indicates that the data isn't in the list.
		}

		int getLength()
		{
			//Returns the length of the list.
			return length;
		}

		bool isEmpty()
		{
			//If the head is NULL, then the list is empty.
			return (head == NULL);
		}

		Obj * getDataAtIndex(int index)
		{
			//Returns the data at the given index.
			Node<Obj> * temp = findNodeAt(index);
			return temp->data;
		}

		void printList()
		{
			//Prints out each of the nodes.
			//Assumes that each Obj type has the << ostream operator
			//overloaded in class implementation.
			Node<Obj> * temp = head;
			int i = 0;

			//While i is inside the bounds of the list.
			while(i < length)
			{
				std::cout << *(temp->data) << std::endl;
				temp = temp->next;
				i++;
			}
		}

	private:
		Node<Obj> * head; //The head of the list.
		int length; //The size of the list.

		Node<Obj> * findOpen()
		{
			//Finds the last node in the list.
			return findNodeWith(atIndex(length - 1));
		}

		Node<Obj> * findNodeWith(Obj * newData)
		{
			//Finds the node with the given data.
			Node<Obj> * temp = head;
			int i = 0;

			//While there are nodes left in the list.
			while(temp != NULL && i <= length)
			{
				if(temp->data == newData)
					return temp;

				temp = temp->next;
				i++;
			}

			return temp;
		}

		Node<Obj> * findNodeAt(int index)
		{
			//Finds the node at the given index.
			Node<Obj> * temp = head;

			if(index >= length - 1)
				return findOpen();
			else if(index >= 0)
				return findNodeWith(getDataAtIndex(index));
			else
				return NULL; //Return NULL if there was a problem.
		}
};

#endif