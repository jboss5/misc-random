/*
	Jordan Bossman
	CSC 402
	Project #5
	Main file for showing the dialog and adding the functionality.
*/

#include <QApplication>
#include <QDialog>
#include "filegenh.h"

int main(int argc, char *argv[])
{
	QApplication app(argc, argv);
	FileGenerator *dialog = new FileGenerator();
	dialog->show();
	return app.exec();
}
