/*
	Jordan Bossman
	CSC 402
	Project #5
	Main file to provide functionality to the program.
	The functionality is: the user chooses a name and size
	for a text file. Then, if the input is correct, is allowed to start.
	When the file is being built, it is done on a separate thread.
	The user can pause/stop/restart thread whenever he/she wants to.
*/

#include <QtGui>
#include <QProgressBar>
#include "filegenh.h"
#include <stdio.h>
#include <iostream>
using namespace std;

FileGenerator::FileGenerator(QWidget *parent):QDialog(parent)
{
	//Sets up the initial things needed for the program.
	setupUi(this);
	//Only allows 1 digit of 1-9, then 0 to 8 digits of 0-9.
	QRegExp reg("[1-9][0-9]{0,8}");
	sizeEdit->setValidator(new QRegExpValidator(reg, this));
	
	//Is the program paused at the start? No, so make it false to start.
	paused = false;
	
	//Make a new thread and then link all of the signals and slots.
	thread = new Thread();
	connect(sizeEdit, SIGNAL(textChanged(QString)), this, SLOT(chkSize()));
	connect(startBtn, SIGNAL(clicked()), this, SLOT(start()));
	connect(stopBtn, SIGNAL(clicked()), this, SLOT(stop()));
	connect(quitBtn, SIGNAL(clicked()), this, SLOT(quit()));
	connect(thread, SIGNAL(finished()), this, SLOT(doStop()));
	connect(thread, SIGNAL(updateBar(int)), progressBar, SLOT(setValue(int)));
}

void FileGenerator::chkSize()
{
	//Checks to make sure that the name does not have a %, @, $, !, ^, &, *, (, ), ,, or . in it.
	QRegExp reg("[%@$!^&*(),.]");
	if(nameEdit->text() != NULL && !(nameEdit->text().contains(reg)))
		startBtn->setEnabled(sizeEdit->hasAcceptableInput());
}

void FileGenerator::doStop()
{
	//On a stop, either from pausing or thread completion,
	//set the label back to "IDLE" and reset the buttons/lineEdits.
	labIdle->setText("IDLE");
	startBtn->setEnabled(true);
	stopBtn->setEnabled(false);
	nameEdit->setEnabled(true);
	sizeEdit->setEnabled(true);
	
	//If it is when the thread completes, and not from a pause; clear
	//the lineEdits and reset the progress bar.
	if(!paused)
	{
		nameEdit->setText("");
		sizeEdit->setText("");
		progressBar->reset();
	}
}

void FileGenerator::start()
{	
	//Starts the thread for creating the file.
	labIdle->setText("RUNNING");
	startBtn->setEnabled(false);
	stopBtn->setEnabled(true);
	nameEdit->setEnabled(false);
	sizeEdit->setEnabled(false);
	
	//If not paused, then start for the first time; otherwise resume.
	if(!paused)
	{
		QString::QString temp = sizeEdit->text();
		size = temp.toInt();
		
		//Give the thread the file size, name and progress bar, then start it.
		thread->setFileSize(size);
		thread->setFileName(nameEdit->text().toStdString());
		thread->setProgBar(progressBar);
		thread->start();
	}
	else
	{
		//If the thread was paused, resume it.
		paused = false;
		thread->start();
	}
}

void FileGenerator::stop()
{
	//When the stop button is pressed, think of it as a pause.
	labIdle->setText("IDLE");
	stopBtn->setEnabled(false);
	startBtn->setEnabled(true);
	nameEdit->setEnabled(true);
	sizeEdit->setEnabled(true);
	
	//Stop the thread.
	thread->stop();
	paused = true;
}

void FileGenerator::quit()
{
	//Stop the thread, delete the thread and close the program.
	thread->stop();
	delete(thread);
	close();
}
