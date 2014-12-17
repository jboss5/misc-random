/*
	Jordan Bossman
	CSC 402
	Project #3
	Date class implementation file.
*/

#include "date.h";
#include <iostream>;
#include <exception>;
#include <string>;
#include <stdlib.h>;
#include <vector>;

using namespace std;
Date::Date()
	:day(1), month(1), year(1970)
{
	//Default date: Jan 1, 1970.
}

Date::Date(int day, int month, int year)
	:day(day), month(month), year(year)
{
	//Allows the user to choose their own day, month & year.
}

const Date & Date::operator=(const Date & rhs)
{
	//Performs the operator= function on this object and an object passed in.
	if(this != &rhs)
	{
		//Copies the m/d/y from the rhs to this object.
		day = rhs.getDay();
		month = rhs.getMonth();
		year = rhs.getYear();
	}

	return *this;
}

Date::~Date()
{
	//Deconstructor, do nothing because of primitive data members.
}

int Date::getDay() const
{
	//Returns the day.
	return day;
}

int Date::getMonth() const
{
	//Returns the month.
	return month;
}

int Date::getYear() const
{
	//Returns the year.
	return year;
}

void Date::setDay(int newDay) throw (out_of_range)
{
	//Sets the day; makes sure that the day is not negative or beyond 31.
	if(newDay <= 0 || newDay > 31)
		throw out_of_range("The new day entered is out of range.");

	day = newDay;
}

void Date::setMonth(int newMonth) throw (out_of_range)
{
	//Sets the month; makes sure the month is not negative or beyond 12.
	if(newMonth <= 0 || newMonth > 12)
		throw out_of_range("The new day entered is out of range.");

	month = newMonth;
}

void Date::setYear(int newYear) throw (out_of_range)
{
	//Sets the year; makes sure the year is not negative.
	if(newYear <= 0)
		throw out_of_range("The new day entered is out of range.");

	year = newYear;
}

ostream & operator<<(ostream & out, const Date & date)
{
	//Inline output override method.
	out << date.getMonth() << "/" << date.getDay() << "/" << date.getYear();
	return out;
}

istream & operator>>(istream & in, Date & date)
{
	//Inline input override method.
	string str;
	vector<string> list;

	//Enter the string.
	cin >> str;

	int last = str.length();
	string temp = "";
	string newStr = "";

	//Parse the date string.
	for(int i = 0; i < last; i++)
	{
		temp = str.at(i);
		if(temp == "/")
		{
			list.push_back(newStr);
			newStr.clear();
		}
		if(temp != " " && temp != "/")
			newStr.append(temp);
	}

	list.push_back(newStr);

	//Add each m/d/y from the vector.
	int m = atoi(list[0].c_str());
	int d = atoi(list[1].c_str());
	int y = atoi(list[2].c_str());

	//Make the new date.
	date = Date(d,m,y);

	return in;
}