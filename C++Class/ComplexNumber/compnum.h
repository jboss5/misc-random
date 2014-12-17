/*
	Jordan Bossman
	CSC 402
	Project #2
	Header file for the implementation of a complex number class.
*/

#ifndef COMPNUM_H
#define COMPNUM_H

#include <iostream>;
#include <string>;
#include <vector>;
using namespace std;

class ComplexNumber
{
	public:
		//Constructors, operator= constructor & deconstructor.
		//Utilizies default copy constructor.
		ComplexNumber();
		ComplexNumber(double, double);
		const ComplexNumber & operator=(const ComplexNumber &);
		~ComplexNumber();

		//Absolute value function, print function and accessors.
		double absVal();
		double getReal() const;
		double getImaginary() const;
		void print(ostream &) const;

		//Returns whether the number is a valid Complex Number or not.
		bool isValid(); 

	private:
		//The data fields for this object plus mutators, which should not
		//be able to be accessed outside of the class.
		double real, imagine;
		void setReal(double);
		void setImaginary(double);
};

//Overloaded operations for use in comparing Complex Numbers.
ComplexNumber operator*(const ComplexNumber &, const ComplexNumber &);
ComplexNumber operator/(const ComplexNumber &, const ComplexNumber &);
ComplexNumber operator+(const ComplexNumber &, const ComplexNumber &);
ComplexNumber operator-(const ComplexNumber &, const ComplexNumber &);
bool operator==(const ComplexNumber &, const ComplexNumber &);
bool operator!=(const ComplexNumber &, const ComplexNumber &);
std::istream & operator>>(istream &, ComplexNumber &);
std::ostream & operator<<(ostream &, const ComplexNumber &);

#endif