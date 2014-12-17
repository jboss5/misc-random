/*
	Jordan Bossman
	CSC 402
	Project #1
	Huge Integer class implementation. Each huge integer is a
	40 digit int array with functions that include: add(), subt(),
	and predicate functions like isEqual(), isGreaterThan() etc...
	All constructors and functions that require a vector input assume
	that the vector will be of size 40. This is because I am assuming
	more functions will be called with HugeInteger objects passed in
	vs regular primitive arrays being passed in.
*/

#include "myheader.h";
#include <iostream>;
#include <vector>;

HugeInteger::HugeInteger()
	: LENGTH(40), hugeInt(LENGTH)
{
	//No-arg constructor for a HugeInteger object.
	setNeg(false);
	for(int i = 0; i < LENGTH; i++)
		hugeInt[i] = 0;
}

 HugeInteger::HugeInteger(int arr[]) 
	 : LENGTH(40), hugeInt(LENGTH)
{
	//Constructor for a HugeInteger object when passed in
	//an already created int array.
	setNeg(false);
	for(int i = 0; i < LENGTH; i++)
		hugeInt[i] = arr[i];
} 
 
HugeInteger::HugeInteger(std::vector<int> arr) 
	 : LENGTH(40), hugeInt(LENGTH)
{
	//Constructor for a HugeInteger object when passed in
	//an already created vector<int>.
	setNeg(false);
	for(int i = 0; i < LENGTH; i++)
		hugeInt[i] = arr[i];
}

HugeInteger::~HugeInteger()
{
	//Deconstructor for a HugeInteger object.
	//Nothing to delete here since I use a vector<int> for the hugeInteger.
}

const HugeInteger & HugeInteger::operator=(const HugeInteger & rhs)
{
	//Operater= constructor for a HugeInteger object.
	if(this != &rhs)
	{
		std::vector<int> arr = rhs.hugeInt;

		//Set this object to be negative if the rhs is negative.
		if(rhs.isNeg())
			setNeg(true);
		else
			setNeg(false);

		//Copy the array from the rhs to this hugeInt array.
		for(int i = 0; i < LENGTH; i++)
			hugeInt[i] = arr[i];
	}

	return *this;
}

void HugeInteger::output()
{
	//Output's the array from this object.
	if(isNeg())
		std::cout << '-';

	for(int i = 0; i < LENGTH; i++)
		std::cout << hugeInt[i];

	std::cout << std::endl;
}

void HugeInteger::add(HugeInteger obj)
{	
	//Determines which add/subtract function to use and the status of the negative flag.
	std::vector<int> temp = obj.hugeInt;

	if(obj.isNeg()) //If the object passed in is negative.
	{
		if(isNeg()) //If this object & the object passed in is negative.
		{
			setNeg(true);
			add(temp);
		}
		else //If this object is not negative & the object passed in is negative.
		{
			//Block determines if the answer will be negative or not depending
			//on whether this object's first digit is greater, equal to or less than
			//the other object's first digit.
			if(hugeInt[0] > temp[0])
			{
				setNeg(false);
				subt(temp);
			}
			else if(hugeInt[0] == temp[0])
			{
				if(isGreaterThan(obj))
				{
					setNeg(false);
					subt(temp);
				}
				else
				{
					setNeg(true);
					add(temp);
				}
			}
			else
			{
				setNeg(true);
				negAdd(temp);
			}
		}
	}
	else //The object passed in is positive.
	{
		if(isNeg()) //This object is negative and the one passed in is positive.
		{
			//Determines whether the answer will be positive or negative
			//based on the second object's first digit & this object's
			//first digit.
			if(temp[0] > hugeInt[0])
			{
				setNeg(false);
				negAdd(temp);
			}
			else
			{
				setNeg(true);
				subt(temp);
			}
		}
		else //Both objects are positive.
		{
			setNeg(false);
			add(temp);
		}
	}
}

void HugeInteger::add(std::vector<int> arr)
{
	//Function to add a passed in array to this object's array.
	int carry = 0;

	for(int i = LENGTH - 1; i > -1; i--)
	{
		//Add both array elements together plus the carry value.
		hugeInt[i] += (arr[i] + carry);
		if(hugeInt[i] >= 10) //If greater than or equal to 10, there is a carry.
		{
			carry = hugeInt[i] / 10; //Determine the carry value.
			hugeInt[i] -= 10; //Change this digit to be between 0-9.
		}
		else
			carry = 0;		
	}
}

void HugeInteger::negAdd(std::vector<int> arr)
{
	//Function subtracts the 40 digit array in this object from the array passed in.
	int first = 0, second = 0;
	bool carry = false;
	
	for(int i = LENGTH - 1; i > -1; i--)
	{
		first = arr[i];
		second = hugeInt[i];

		//If there was a carry, subtract 1 from the first number.
		if(carry)
			first--;

		if(first < second)
		{
			//If the first is less than the second digit, add 10 to the first
			//then subtract the second from it. Then the carry will subtract
			//1 from the next digit.
			hugeInt[i] = (first + 10) - second;
			carry = true;
		}
		else
		{
			hugeInt[i] = first - second;
			carry = false;
		}
	}
}

void HugeInteger::subt(HugeInteger obj)
{
	//Determines which methods to use for subtraction and what the negative
	//flag will be set to for the answer.
	std::vector<int> temp = obj.hugeInt;

	if(isNeg()) //This object is negative.
	{
		if(obj.isNeg()) //Both object's are negative.
		{
			setNeg(true);
			add(temp);
		}
		else //This object is negative and the object passed in is positive.
		{
			//Determines what the negative flag for the answer will be by 
			//what the first digits of the object's are.
			if(temp[0] > hugeInt[0])
			{
				setNeg(false);
				negAdd(temp);
			}
			else
			{
				setNeg(true);
				subt(temp);
			}
		}
	}
	else //This object is positive.
	{
		if(obj.isNeg()) //The object is positive & the one passed in is negative.
		{
			setNeg(false);
			add(temp);
		}
		else //Both are positive.
		{
			//Determines negative flag based on the first digits of each number.
			if(temp[0] > hugeInt[0])
			{
				setNeg(true);
				negAdd(temp);
			}
			else
			{
				setNeg(false);
				subt(temp);
			}
		}
	}
}

void HugeInteger::subt(std::vector<int> arr)
{
	//Main subtraction function. Subtracts the passed in array from this object's array.
	int first = 0, second = 0;
	bool carry = false;

	for(int i = LENGTH - 1; i > -1; i--)
	{
		first = hugeInt[i];
		second = arr[i];

		//If there was a carry, subtract 1 from the first digit.
		if(carry)
			first--;
		
		if(first < second)
		{
			//If the first is less than the second digit, add 10 to the first
			//then subtract the second from it. Then the carry will subtract
			//1 from the next digit.
			hugeInt[i] = (first + 10) - second;
			carry = true;
		}
		else
		{
			hugeInt[i] = first - second;
			carry = false;
		}
	}
}

bool HugeInteger::isEqualTo(HugeInteger obj)
{
	//Determines if the array in this object is the same as the
	//array in the passed in object.
	std::vector<int> arr = obj.hugeInt;

	for(int i = 0; i < LENGTH; i++)
		if(hugeInt[i] != arr[i])
			return false;

	return true;
}

bool HugeInteger::isNotEqualTo(HugeInteger obj)
{
	//Determines if the array in this object is not equal to
	//the array in the object passed in.
	return !isEqualTo(obj);
}

bool HugeInteger::isGreaterThan(HugeInteger obj)
{
	//Determines if the array in this object is greater than
	//the array in the object passed in by going through
	//each digit until the digits in each array are 
	//not equal to eachother. Then determines, based on
	//those digits, which number is greater than the other.
	std::vector<int> arr = obj.hugeInt;

	//Based on negativity, determine greater than or not
	//before any looping.
	if(obj.isNeg() && !isNeg())
		return true;
	else if(!obj.isNeg() && isNeg())
		return false;
	else
	{
		//Loop through each digit until you come to one that
		//is not equal.
		int i = 0;
		while(hugeInt[i] == arr[i] && i < LENGTH)
			i++;
	
		//If this object's digit is less than the passed in object's digit 
		//and both objects are positive.
		if(hugeInt[i] < arr[i] && !(isNeg() && obj.isNeg()))
			return false;
		else
			return true;
	}
}

bool HugeInteger::isLessThan(HugeInteger obj)
{
	//Determines if the array in this object is less than the
	//array in the object passed in.
	return !isGreaterThan(obj) && isNotEqualTo(obj);
}

bool HugeInteger::isGreaterThanOrEqualTo(HugeInteger obj)
{
	//Determines if the array in this object is greater than
	//equal to the array of the object passed in.
	if(isNeg() && obj.isNeg())
		return !(isLessThan(obj));
	else
		return (isGreaterThan(obj) || isEqualTo(obj));
}

bool HugeInteger::isLessThanOrEqualTo(HugeInteger obj)
{
	//Determines if the array in this object is less than or
	//equal to the array in the object passed in.
	return (isLessThan(obj) && isEqualTo(obj));
}

bool HugeInteger::isZero()
{
	//Checks to see if the hugeInt in this object is zero or not.
	for(int i = 0; i < LENGTH; i++)
		if(hugeInt[i] != 0)
			return false;

	return true;
}

bool HugeInteger::isNeg() const
{
	//Returns the value of the negative flag.
	return negFlag;
}

void HugeInteger::setNeg(bool val)
{
	//Sets the value of the negative flag.
	negFlag = val;
}