/*
	Jordan Bossman
	CSC 402
	Project #1
	Header file for HugeInteger class and main test file.
*/
#ifndef MYHEADER_H
#define MYHEADER_H
#include <vector>;
#include <iostream>;

class HugeInteger
{
	public:
		HugeInteger();
		HugeInteger(int[]); //Constructor with an array provided, assuming array length is 40.
		HugeInteger(std::vector<int>); //Constructor with a vector provided, assuming length of 40.
		~HugeInteger();
		const HugeInteger & operator=(const HugeInteger &);
		void output();
		void add(HugeInteger);
		void add(std::vector<int>); //Assuming vector length is 40, also used to add two objects.
		void subt(HugeInteger);
		void subt(std::vector<int>); //Assuming vector length is 40, also used to subt two objects.
		void setNeg(bool);
		//Predicate functions for equality, comparison, zero and negative.
		bool isEqualTo(HugeInteger);
		bool isNotEqualTo(HugeInteger);
		bool isGreaterThan(HugeInteger);
		bool isLessThan(HugeInteger);
		bool isGreaterThanOrEqualTo(HugeInteger);
		bool isLessThanOrEqualTo(HugeInteger);
		bool isZero();
		bool isNeg() const;
	private:
		const int LENGTH; //Constant value for the 40 digit length.
		std::vector<int> hugeInt; //The actual "HugeInteger" itself.
		bool negFlag;
		void negAdd(std::vector<int>); //Reverse add for easier addition with negatives.
};
#endif