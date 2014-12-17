/*
	Jordan Bossman
	CSC 402
	Project #3
	Date class declaration header file.
*/

#ifndef DATE_H
#define DATE_H

#include <iostream>;

class Date
{
	public:
		Date(); //No-arg constructor.
		Date(int, int, int); //Use the user's day, month, year combo.
		const Date & operator=(const Date &);
		~Date();
		int getDay() const; //Gets the day.
		int getMonth() const; //Gets the month.
		int getYear() const; //Gets the year.
		void setDay(int); //Sets the day.
		void setMonth(int); //Sets the month.
		void setYear(int); //Sets the year.

	private:
		int day; //Day variable.
		int month; //Month variable.
		int year; //Year variable.
};

std::istream & operator>>(std::istream &, Date &); //cin operator overload.
std::ostream & operator<<(std::ostream &, const Date &); //cout operator overload.

#endif