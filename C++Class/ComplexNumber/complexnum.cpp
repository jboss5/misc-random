/*
	Jordan Bossman
	CSC 402
	Project #2
	Class file for the implementation of a complex number class.
*/

#include "compnum.h";
#include <math.h>;
#include <stdlib.h>;
#include <iostream>;
#include <string>;
#include <vector>;
using namespace std;

ComplexNumber::ComplexNumber()
	:real(0.0), imagine(0.0)
{
	//No-arg constructor.
}

ComplexNumber::ComplexNumber(double a, double b)
	:real(a), imagine(b)
{
	//Constructor with both real and imaginary provided.
}

ComplexNumber::~ComplexNumber()
{
	//Deconstructor, use the default.
}

void ComplexNumber::setReal(double val)
{
	//Mutator for the real portion of a Complex Number.
	ComplexNumber::real = val;
}

void ComplexNumber::setImaginary(double val)
{
	//Mutator for the imaginary portion of a Complex Number.
	ComplexNumber::imagine = val;
}

const ComplexNumber & ComplexNumber::operator=(const ComplexNumber & rhs)
{
	//Performs the operator= function on this object and an object passed in.
	if(this != &rhs)
	{
		real = rhs.getReal();
		imagine = rhs.getImaginary();
	}

	return *this;
}

double ComplexNumber::absVal()
{
	//Computes the absolute value of the complex number expression in this object.
	//Formula: square root(a^2 + b^2).

	double a = pow(real, 2);
	double b = pow(imagine, 2);

	double answer = a + b;
	answer = sqrt(answer);

	return answer;
}

double ComplexNumber::getReal() const
{
	//Returns the real portion of this complex number expression.
	return real;
}

double ComplexNumber::getImaginary() const
{
	//Returns the imaginary portion of this complex number expression.
	return imagine;
}

void ComplexNumber::print(ostream & out) const
{
	//Prints out a Complex Number.
	out << "(" << getReal() << " + " << getImaginary() << "i)";
}

bool ComplexNumber::isValid()
{
	//Checks to see if the current Complex Number is positive or not.
	if(real < 0.0 || imagine < 0.0)
		return false;
	else
		return true;
}

ComplexNumber operator*(const ComplexNumber & lhs, const ComplexNumber & rhs)
{
	//Inline multiplication override method.
	//Multiplication formula: (a*c – b*d, a*d + b*c).
	double a = lhs.getReal();
	double b = lhs.getImaginary();
	double c = rhs.getReal();
	double d = rhs.getImaginary();

	double first = ((a * c) - (b * d));
	double second = ((a * d) + (b * c));

	ComplexNumber answer(first, second);
	return answer;
}

ComplexNumber operator/(const ComplexNumber & lhs, const ComplexNumber & rhs)
{
	//Inline division override method.
	//Division formula: ((a*c+b*d)/(c^2+d^2), (b*c–a*d)/(c^2+d^2)).
	double a = lhs.getReal();
	double b = lhs.getImaginary();
	double c = rhs.getReal();
	double d = rhs.getImaginary();

	double first = ((a * c) + (b * d));
	double second = ((b * c) - (a * d));

	first /= (pow(c,2) + pow(d,2));
	second /= (pow(c,2) + pow(d,2));

	ComplexNumber answer(first, second);
	return answer;
}

ComplexNumber operator+(const ComplexNumber & lhs, const ComplexNumber & rhs)
{
	//Inline addition override method.
	//Adds two Complex Numbers together.
	double first = lhs.getReal() + rhs.getReal();
	double second = lhs.getImaginary() + rhs.getImaginary();

	ComplexNumber answer(first, second);
	return answer;
}

ComplexNumber operator-(const ComplexNumber & lhs, const ComplexNumber & rhs)
{
	//Inline subtraction override method.
	//Subtracts two Complex Numbers.
	double first = lhs.getReal() - rhs.getReal();
	double second = lhs.getImaginary() - rhs.getImaginary();

	ComplexNumber answer(first, second);
	return answer;
}

bool operator==(const ComplexNumber & lhs, const ComplexNumber & rhs)
{
	//Boolean == operation overload, determines if two
	//Complex Numbers are equal to eachother.
	if(lhs.getReal() == rhs.getReal())
		if(lhs.getImaginary() == lhs.getImaginary())
			return true;

	return false;
}

bool operator!=(const ComplexNumber & lhs, const ComplexNumber & rhs)
{
	//Boolean != operation overload, determines if two
	//Complex Numbers are not equal to each other. Exact opposite
	//of the == function.
	return !(lhs == rhs);
}

istream & operator>>(istream & in, ComplexNumber & val) 
{
	//Inline input override method.
	string str;
	vector<string> list;

	getline(in, str);

	//After getting the line input from the user, split
	//the string and grab the contents.
	int last = str.length();
	string newStr = "";
	string temp = "";

	for(int i = 0; i < last; i++)
	{
		//Fills a vector of strings with each character of the string.
		temp = str.at(i);
		if(temp == "+" || temp == "i")
		{
			list.push_back(newStr);
			newStr = " ";
		}

		if(temp != " ")
			newStr.append(temp);
	}

	//Cast each character to a double from the string.
	double a = atof(list[0].c_str());
	double b = atof(list[1].c_str());

	val = ComplexNumber(a, b);
	return in;
}

ostream & operator<<(ostream & out, const ComplexNumber & val)
{
	//Inline output override method.
	val.print(out);
	return out;
}





