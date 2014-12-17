/*
	Jordan Bossman
	CSC 402
	Project #1
	Main file for testing of a HugeInteger class.
	Creates a few HugeInteger objects and tests out each of
	the functions in the class.
*/

#include "myheader.h"
#include <iostream>;
#include <time.h>;
using namespace std;

void printObj(HugeInteger obj1, HugeInteger obj2, HugeInteger obj3,
			HugeInteger obj4, HugeInteger obj5)
{
	//Function prints out all of the objects passed in.
	cout << "obj1: ";
	obj1.output();
	cout << "obj2: ";
	obj2.output();
	cout << "obj3: ";
	obj3.output();
	cout << "obj4: ";
	obj4.output();
	cout << "obj5: ";
	obj5.output();
	cout << endl;
}

int main()
{
	//Arrays to pass into HugeInteger objects for testing.
	int arr1[40], arr2[40];
	std::vector<int> arr3(40); //Vector to test the vector constructor.

	srand(time(NULL));

	for(int i = 0; i < 40; i++)
	{
		//Randomize two arrays for passing into HugeInteger objects.
		arr1[i] = rand() % 10;
		arr2[i] = rand() % 10;
		arr3[i] = rand() % 10;
	}

	//Create each HugeInteger object using each method.
	//Methods include: 1 arg, no arg and copy constructor/operator=.
	HugeInteger obj1(arr1);
	HugeInteger obj2(arr2);
	HugeInteger obj3(arr3);
	HugeInteger obj4 = obj2;
	HugeInteger obj5;

	//Set one of them to be negative show that implementation.
	obj3.setNeg(true);

	//Print out each object.
	printObj(obj1, obj2, obj3, obj4, obj5);

	//Perform some arithmetic on the objects.
	cout << "obj1 + obj2 = ";
	obj1.add(obj2);
	obj1.output();
	cout << "obj2 - obj3 = ";
	obj2.subt(obj3);
	obj2.output();
	cout << "obj4 + obj5 = ";
	obj4.add(obj5);
	obj4.output();
	cout << endl;
	
	//Print out objects again to see new values.
	printObj(obj1, obj2, obj3, obj4, obj5);

	//Perform some predicate functions on the objects, and set one to be negative.
	//Cout flag "boolalpha" used to print out the booleans as "true" or "false"
	//verse 1 or 0 that is normally printed.
	cout << boolalpha; //Flag to change boolean outputs to true/false instead of 1/0.
	cout << "obj1 > obj2 => " << obj1.isGreaterThan(obj2) << endl; 
	cout << "obj3 < obj2 => " << obj3.isLessThan(obj2) << endl;
	cout << "obj2 >= obj3 => " << obj2.isGreaterThanOrEqualTo(obj3) << endl;
	cout << "obj4 = obj2 => " << obj4.isEqualTo(obj2) << endl;
	cout << "obj4 != obj3 => " << obj4.isNotEqualTo(obj3) << endl;
	cout << "obj3 is negative = " << obj3.isNeg() << endl;
	cout << "Is obj5 zero? " << obj5.isZero() << endl;
	cout << "Set obj4 to be negative... ";
	obj4.setNeg(true);
	cout << "Is obj4 negative now? " << obj4.isNeg() << endl;
	cout << "obj4: ";
	obj4.output();

	int i;
	cin >> i;
	return 0;
}