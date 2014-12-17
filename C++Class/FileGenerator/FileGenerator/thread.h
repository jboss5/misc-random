/*
	Jordan Bossman
	CSC 402
	Project #5
	Header file declaring a thread class for building of a file.
*/

#ifndef THREAD_H
#define THREAD_H
#include <QThread>
#include <QProgressBar>
#include <stdlib.h>
#include <string>

class Thread : public QThread
{
	Q_OBJECT
	
	public:
		Thread();
		~Thread();
		void stop();
		//Sets up default information needed by the thread.
		void setFileName(std::string);
		void setFileSize(int);
		void setProgBar(QProgressBar *);
		bool isStopped() const;
		QProgressBar::QProgressBar * getProgressBar();
		int getSize();
	
	signals:
		void updateBar(int);
	
	protected:
		void run();
		
	private:
		//Variables for bookeeping of the thread.
		volatile bool stopped;
		QProgressBar::QProgressBar *progBar;
		int size;
		FILE *file;
		std::string fname;
		int count;
		//Methods for different byte sizes.
		void buildFile();
		void buildFile(int);
		void runSizeLow();
		void runSizeLowMed();
		void runSizeMed();
		void runSizeMedHigh();
		void runSizeHigh();
		void runSizeReallyHigh();


};

#endif
