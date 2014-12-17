/*
	Jordan Bossman
	CSC 402
	Project #5
	Header file containing all the data members/methods needed
	for creating the application.
*/

#ifndef FILEGEN_H
#define FILEGEN_H
#include <QDialog>
#include "thread.h"
#include "ui_hw5gui.h"
#include <string>

class FileGenerator : public QDialog, public Ui::FileGenDialog
{
	Q_OBJECT
	
	public:
		FileGenerator(QWidget *parent = 0);
	
	private slots:
		//Slots for use in the program.
		void doStop();
		void chkSize();
		void start();
		void stop();
		void quit();
		
	private:
		//Bookeeping variables.
		bool paused;
		Thread *thread;
		std::string fname;
		int size;
	
};

#endif
