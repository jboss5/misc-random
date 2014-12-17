/*
	Jordan Bossman
	CSC 402
	Project #3
	Main function implementation of a Queue template class.
	Demonstrates the uses of the functions inside of
	the Queue class.
*/

#include "queue.h";
#include "date.h";
#include <stdio.h>;
#include <stdlib.h>;
#include <time.h>;
#include <vector>;
#include <iostream>;
#include <string>;

using namespace std;

void printDateExamples()
{
	//Creates a few random dates and uses all of the Queue functions.
	srand(time(NULL));
	int day = 0, month = 0, year = 0;

	Queue<Date> * dates = new Queue<Date>();
	Date * list[5];
	for(int i = 0; i < 5; i++)
	{
		day = rand() % 25 + 1;
		month = rand() % 12 + 1;
		year = rand() % 2000 + 1;
		list[i] = new Date(day, month, year);
		dates->enqueue(list[i]);
	}

	//Function display.
	cout << "Printed queue of Dates: " << endl;
	dates->print();
	cout << "Is the queue empty? " << boolalpha << dates->isEmpty() << endl;
	cout << "Size of the queue: " << dates->size() << endl;
	cout << "Dequeue the first 2: " << endl;
	cout << *dates->dequeue() << " ";
	cout << *dates->dequeue() << endl;
	cout << "Adding 9/9/1999 to the queue... ";
	dates->enqueue(new Date(9,9,1999));
	cout << endl << "Printing queue again... " << endl;
	dates->print();
	cout << "Clearing the queue... " << endl;
	dates->clear();
	cout << "Is the queue now empty? " << dates->isEmpty() << endl;

	delete dates;
}

void printIntExamples()
{
	//Creates random integers and prints out each Queue function.
	srand(time(NULL));

	Queue<int> * ints = new Queue<int>();
	int * list[5];
	for(int i = 0; i < 5; i++)
	{
		list[i] = new int(rand() % 100000 + 1);
		ints->enqueue(list[i]);
	}

	//Function display.
	cout << "Printed queue of Integers: " << endl;
	ints->print();
	cout << "Is the queue empty? " << boolalpha << ints->isEmpty() << endl;
	cout << "Size of the queue: " << ints->size() << endl;
	cout << "Dequeue the first 2: " << endl;
	cout << *ints->dequeue() << " ";
	cout << *ints->dequeue() << endl;
	cout << "Adding 9584 to the queue... ";
	ints->enqueue(new int(9584));
	cout << endl << "Printing queue again... " << endl;
	ints->print();
	cout << "Clearing the queue... " << endl;
	ints->clear();
	cout << "Is the queue now empty? " << ints->isEmpty() << endl;

	delete ints;
}

void printMenu()
{
	//Prints out the menu for the program.
	cout << "|-----------------------|" << endl;
	cout << "|         Menu          |" << endl;
	cout << "|-----------------------|" << endl;
	cout << "|      e - enqueue      |" << endl;
	cout << "|      d - dequeue      |" << endl;
	cout << "|      s - size         |" << endl;
	cout << "|      c - clear        |" << endl;
	cout << "|      p - print        |" << endl;
	cout << "|      i - is empty?    |" << endl;
	cout << "|      m - menu         |" << endl;
	cout << "|      x - examples     |" << endl;
	cout << "|      z - quit         |" << endl;
	cout << "|-----------------------|" << endl;
}

void integers()
{
	//If the user chooses integer, perform the integer functions.
	Queue<int> * ints = new Queue<int>();
	vector<int> list;
	int num = 0;

	cout << "Please enter your ints to fill the queue (-1 to quit): " << endl;
	
	//Fills the vector with all of the integers the user enters (except -1).
	do
	{
		cout << "-> ";
		cin >> num;
	
		if(num != -1)
			list.push_back(num);
	} while(num != -1);

	//Adds the integers to the queue.
	for(int i = 0; i < list.size(); i++)
		ints->enqueue(&list[i]);

	char c = ' ';

	cout << "Queue filled." << endl << "Please enter your commands." << endl;

	//Lets the user choose which Queue function to use on the Queue; 'm' presents
	//the menu function and those are the characters to use.
	do
	{
		cout << "-> ";
		cin >> c;

		switch(c)
		{
			case 'e':
				//Enqueue a new integer.
				cout << "Enter a number to add: ";
				cin >> num;
				ints->enqueue(&num);
				break;
			case 'd':
				//Dequeue and print out the dequeued integer.
				cout << "Dequeuing... Data: " << *ints->dequeue() << endl;
				break;
			case 's':
				//Size of the queue.
				cout << "The size of the queue: " << ints->size() << endl;
				break;
			case 'c':
				//Clears the queue.
				cout << "Clearing the queue... ";
				ints->clear();
				cout << endl;
				break;
			case 'p':
				//Prints the queue.
				cout << "Printed list: " << endl;
				ints->print();
				break;
			case 'i':
				//Is the queue empty?
				cout << "Is the queue empty? " << boolalpha << ints->isEmpty() << endl;
				break;
			case 'm':
				//Prints the menu.
				printMenu();
				break;
			case 'x':
				//Prints the examples for integers.
				printIntExamples();
				break;
			default:
				cout << "Please make sure you've entered a correct character, or z again to quit. " << endl;
		}
	} while(c != 'z');

	//Delete the queue.
	delete ints;
}

void dates()
{
	//If the user chooses dates, use this function for the inputs.
	Queue<Date> * dates = new Queue<Date>();
	vector<Date> list;
	Date d;
	bool valid = true;

	cout << "Please enter your dates to fill the queue (-1/-1/-1 to quit): " << endl;
	
	//Grab each date the user enters, -1/-/1/-1 says to stop.
	do
	{
		cout << "-> ";
		cin >> d;

		if(d.getDay() == -1 && d.getMonth() == -1 && d.getYear() == -1)
			valid = false;
		else
			list.push_back(d);

	} while(valid);

	//Adds each date from the vector to the queue.
	for(int i = 0; i < list.size(); i++)
		dates->enqueue(&list[i]);

	char c = ' ';

	cout << "Queue filled." << endl << "Please enter your commands." << endl;

	//Each character stands for a function the user wants to perform on the queue.
	do
	{
		cout << "-> ";
		cin >> c;

		switch(c)
		{
			case 'e':
				//Enqueue a date.
				cout << "Enter a number to add: ";
				cin >> d;
				dates->enqueue(&d);
				break;
			case 'd':
				//Dequeue a date and print it out.
				cout << "Dequeuing... Data: " << *dates->dequeue() << endl;
				break;
			case 's':
				//Size of the queue.
				cout << "The size of the queue: " << dates->size() << endl;
				break;
			case 'c':
				//Clears the queue.
				cout << "Clearing the queue... ";
				dates->clear();
				cout << endl;
				break;
			case 'p':
				//Prints out the queue.
				cout << "Printed list: " << endl;
				dates->print();
				break;
			case 'i':
				//Is the queue empty?
				cout << "Is the queue empty? " << boolalpha << dates->isEmpty() << endl;
				break;
			case 'm':
				//Prints out the menu.
				printMenu();
				break;
			case 'x':
				//Prints the examples with the dates.
				printDateExamples();
				break;
			default:
				cout << "Please make sure you've entered a correct character, or z again to quit. " << endl;
		} 
	} while(c != 'z');

	//Delete the date queue.
	delete dates;
}

int main()
{
	//Main function that allows the user to choose a queue of integers or dates.
	char c = ' ';
	bool valid = false;

	//Allows the user to enter 'i' or 'd' and sends it to the correct spot.
	//If not i/d, then the user is asked to reinput.
	while(!valid)
	{		
		cout << "Queue of ints or Dates? (i/d)   ";
		cin >> c;
		
		switch(c)
		{
			case 'i':
				//User wants to do a queue of integers.
				integers();
				valid = true;
				break;
			case 'd':
				//User wants to do a queue of dates.
				dates();
				valid = true;
				break;
			default:
				cout << "Please try again....." << endl;
				valid = false;
		}
	}
	
	//Pause the program for display purposes.
	int i;
	cin >> i;
	return 1;
}