/*
	Jordan Bossman
	CSC 402 
	Project #2
	Main function file for implementation testing
	of the Complex Number class. Tests all member
	functions of the class.
*/

#include "compnum.h";
#include<iostream>;
#include<string>;
using namespace std;

void displayMenu()
{
	//Displays the menu for the user.
	cout << "|----------------------------|" << endl;
	cout << "|           Menu             |" << endl;
	cout << "|----------------------------|" << endl;
	cout << "|   a : The first number.    |" << endl;
	cout << "|   b : The second number.   |" << endl;
	cout << "|----------------------------|" << endl;
	cout << "|   Arithmetic Operations    |" << endl;
	cout << "|   Examples: press e/E      |" << endl;
	cout << "|   Usage: a+b or a-b+b etc  |" << endl;
	cout << "|   Absolute value: |a etc   |" << endl;
	cout << "|   Only 1 abs val per entry |" << endl;
	cout << "|   Bools: == and !=         |" << endl;
	cout << "|----------------------------|" << endl;
	cout << "|   Exit/Cancel: q/Q         |" << endl;
	cout << "|   Display menu: m/M        |" << endl;
	cout << "|----------------------------|" << endl;
}

vector<string> splitOps(vector<string> list, string str)
{
	//Splits the given string into a vector that includes
	//all characters of the string except spaces.
	string temp = "", newStr = "";
	int last = str.length();

	for(int i = 0; i < last; i++)
	{
		temp = str.substr(i, 1);

		if(temp != " ")
			list.push_back(temp);
	}

	return list;
}


ComplexNumber getNext(vector<string> list, int index, const ComplexNumber & a, const ComplexNumber & b)
{
	//Grabs the object that is being operated on (the variable name after an operation).
	if((index + 1) >= list.size())
		return ComplexNumber(); //Return a blank object because of invalid input.

	string newStr = list[index + 1];

	if(newStr == "a")
		return a;
	else if(newStr == "b")
		return b;
	else
		return ComplexNumber(); //Return a blank object because of invalid input.
}

void doExpressions(vector<string> list, const ComplexNumber & a, const ComplexNumber & b)
{
	//Performs the given operations based on user input.
	string str = list[0];
	ComplexNumber num, next;

	//Builds the initial starting object based on first spot.
	if(str == "a")
		num = a;
	else if(str == "b")
		num = b;
	else if(str == "|")
	{
		//If its absolute value, print it out and return.
		num = getNext(list, 0, a, b);
		if((num == a || num == b) && list.size() <= 2)
			cout << "Answer: " << num.absVal() << endl;
		else
			cout << "Please make sure that you only do one absolute value at a time." << endl;
		return;
	}
	else
	{
		cout << "Please check to make sure that your expressions are correct and try again." << endl;
		return;
	}

	for(int i = 1; i < list.size(); i+=2)
	{
		//Goes through i and i+1 inside of the list to determine
		//which operation to perform on the given expressions.
		if(list[i+1] != "a" && list[i+1] != "b" && list[i+1] != "=" && list[i+1] != "!")
		{
			//Catch if any of the inputs are invalid.
			cout << "Please check to make sure that your expressions are correct and try again." << endl;
			return;
		}

		//Checks for addition, subtraction, division, multiplication, == and !=. 
		//Inside each statement, it grabs the next term (the variable name) and
		//performs the operation based on the object and operation.
		if(list[i] == "+")
		{
			next = getNext(list, i, a, b);
			num = num + next;
		}
		else if(list[i] == "-")
		{
			next = getNext(list, i, a, b);
			num = num - next;
		}
		else if(list[i] == "/")
		{
			next = getNext(list, i, a, b);
			num = num / next;
		}
		else if(list[i] == "*")
		{
			next = getNext(list, i, a, b);
			num = num * next;
		}
		else if(list[i] == "=" && list[i+1] == "=")
		{		
			//Perform == comparison then return.
			next = getNext(list, i - 2, a , b); //Grabs the object previous to the first = sign.
			ComplexNumber temp = getNext(list, i + 1, a, b); //Grabs the object passed the == sign.
			if(temp == a || temp == b)
				cout << next << " == " << temp << " => " << boolalpha << (next == temp) << endl;
			else
				cout << "Please check to make sure that your expressions are correct and try again." << endl;
			return;
		}
		else if(list[i] == "!" && list[i+1] == "=")
		{
			//Perform != comparison then return.
			next = getNext(list, i - 2, a , b); //Grabs the object previous to !.
			ComplexNumber temp = getNext(list, i + 1, a, b); //Grabs the object passed the !=.
			if(temp == a || temp == b)
				cout << next << " != " << temp << " => " << boolalpha << (next != temp) << endl;
			else
				cout << "Please check to make sure that your expressions are correct and try again." << endl;
			return;
		}
		else 
		{
			cout << "Please check to make sure that your expressions are correct and try again." << endl;
			return;
		}
	}

	//Print out the final answer to the user.
	cout << "Answer: " << num << endl;
}

void displayExamples()
{
	//Displays each function in the Complex Number class, along with
	//examples for the user to use in making his/her usage easier.
	ComplexNumber a(5.45, 3.75);
	ComplexNumber b(8.9923, 1.928);

	cout << "A few examples of the operations of a Complex Number:" << endl;
	cout << "-----------------------------------------------------" << endl;
	cout << "a = " << a;
	cout << endl << "b = " << b;
	cout << endl << "a + b = " << (a + b);
	cout << endl << "a + b * a / b = " << (a + b * a / b);
	cout << endl << "b / a = " << (b / a);
	cout << endl << "Absolute value of a: " << a.absVal();
	cout << endl << "Absolute value of b: " << b.absVal();
	cout << endl << "Does a == b? " << boolalpha << (a == b);
	cout << endl << "Does a != b? " << (a != b);
	cout << endl << "The real part of a is: " << a.getReal();
	cout << endl << "The imaginary part of a is: " << a.getImaginary();
	cout << endl << endl;
}

void error()
{
	//Function prints out a nice error message if the Complex Number is entered wrong.
	int i;
	cout << "Please make sure that you have correctly entered a positive imaginary number." << endl;
	cin >> i;
}

int main()
{
	//Main function for running a Complex Number class.
	char ch = ' ';
	string str = "";
	vector<string> list;

	//Grabs initial expressions at the start.
	ComplexNumber a, b;
	cout << "Enter the first expression: ";
	cin >> a;
	
	//Check to make sure a and b are valid numbers before continuing.
	if(a.isValid())
	{
		cout << "Enter the second expression: ";
		cin >> b;

		if(b.isValid())
		{
			cout << endl;
	
			//Displays the menu so the user knows when to start typing.
			displayMenu();
		
			do
			{
				//Loop keeps looping until there has been an error, or the
				//user signals that he/she wants to end by a q/Q.
				//E/e prints out examples and m/M re-prints the menu.
				cout << "--> ";
				getline(cin, str);
		
				if(str == "m" || str == "M")
					displayMenu();
				else if(str == "e" || str == "E")
					displayExamples();
				else
				{
					//If its not an exit, example or menu call; split the string
					//up and then perform the expression.
					list = splitOps(list, str);
					doExpressions(list, a, b);
					list.clear();
				}
			} while(!(str == "Q" || str == "q"));
		}
		else
			error();
	}
	else
		error();

	return 0;
}
