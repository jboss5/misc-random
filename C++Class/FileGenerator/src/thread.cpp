/*
	Jordan Bossman
	CSC 402
	Project #5
	Implementation of the thread class.
	This builds a file with random a-z characters until the size is reached.
	It can be stopped (paused) or stopped (by finishing).
*/

#include "thread.h"
#include <QtCore>
#include <iostream>
#include <time.h>
#include <QApplication>

Thread::Thread()
{
	//Set the initial values of the flags, file pointer and seed the random number generator.
	stopped = false;
	progBar = NULL;
	size = 0;
	count = 0;
	file = NULL;
	srand(time(NULL));
}

Thread::~Thread()
{
	//Delete the progress bar pointer and wait for the thread.
	//Make sure the file is closed.
	if(file != NULL)
		fclose(file);
		
	delete(progBar);
	wait();
}

void Thread::run()
{
	//On run, append ".txt" to the file, then if it has been stopped; reopen the file for appending.
	//Otherwise open the file for write (creates it if not found or overwrites it if found).
	fname.append(".txt");
	
	if(stopped)
	{
		//The program was paused.
		file = fopen(fname.c_str(), "a");
		stopped = false; //Reset the stopped flag.
	}
	else
	{
		//Fresh run of the program.
		count = 0;
		file = fopen(fname.c_str(), "w");
	}
	
	//Different methods to run based on when they call the progress bar.
	//Helps to speed up runtime of the program, but causes a bug at 25 million+ bytes
	//of the progress bar resetting itself periodically.
	if(size < 1000)
		runSizeLow();
	else if(size > 10000)
		runSizeLowMed();
	else if(size > 100000)
		runSizeMed();
	else if(size > 500000)
		runSizeMedHigh();
	else if(size > 1500000)
		runSizeHigh();
	else if(size > 100000000)
		runSizeReallyHigh();
	
	//Make sure to close the file.
	fclose(file);
}

void Thread::runSizeLow()
{
	//Original run of building the file, designed for byte count of
	//under 1,000.
	int num = 0;
	while((count < size) && !stopped)
	{
		num = rand() % 26 + 97;
		putc(num, file);
		count++;
		//Update the progress bar.
		emit updateBar(100 * count / size);
		QApplication::processEvents();
	}
}

void Thread::runSizeLowMed()
{
	//Runs the build file, then updates progress bar every 500 bytes.
	buildFile(500);
}

void Thread::runSizeMed()
{
	//Runs the build file, then updates progress bar every 1000 bytes.
	buildFile(1000);
}

void Thread::runSizeMedHigh()
{
	//Runs the build file, then updates progress bar every 5000 bytes.
	buildFile(5000);
}

void Thread::runSizeHigh()
{
	//Runs the build file, then updates progress bar every 10000 bytes.
	buildFile(10000);
}

void Thread::runSizeReallyHigh()
{
	//Runs the build file, then updates progress bar every 250000 bytes.
	buildFile(250000);
}

void Thread::buildFile(int chance)
{
	//Builds the file and updates the progress bar at a certain number of bytes.
	int num = 0;
	while((count < size) && !stopped)
	{
		num = rand() % 26 + 97;
		putc(num, file);
		count++;
		if(count % chance == 0)
		{
			//Update the progress at every certain # of bytes.
			emit updateBar(100 * count / size);
			QApplication::processEvents();
		}
	}
}

void Thread::stop()
{
	//Stops the thread.
	stopped = true;
}

void Thread::setFileName(std::string str)
{
	//Sets the file name as the name passed in.
	fname = str;
}

void Thread::setFileSize(int newSize)
{
	//Sets the file size.
	size = newSize;
}

void Thread::setProgBar(QProgressBar *bar)
{
	//Sets the progress bar pointer.
	progBar = bar;
}

bool Thread::isStopped() const
{
	//Return if stopped or not.
	return stopped;
}

QProgressBar::QProgressBar * Thread::getProgressBar()
{
	//Returns the progress bar.
	return progBar;
}

int Thread::getSize()
{
	//Returns the file size.
	return size;
}
