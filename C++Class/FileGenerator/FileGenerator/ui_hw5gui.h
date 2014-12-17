/********************************************************************************
** Form generated from reading ui file 'hw5gui.ui'
**
** Created: Tue Apr 30 19:08:40 2013
**      by: Qt User Interface Compiler version 4.4.3
**
** WARNING! All changes made in this file will be lost when recompiling ui file!
********************************************************************************/

#ifndef UI_HW5GUI_H
#define UI_HW5GUI_H

#include <QtCore/QVariant>
#include <QtGui/QAction>
#include <QtGui/QApplication>
#include <QtGui/QButtonGroup>
#include <QtGui/QDialog>
#include <QtGui/QGridLayout>
#include <QtGui/QHBoxLayout>
#include <QtGui/QLabel>
#include <QtGui/QLineEdit>
#include <QtGui/QProgressBar>
#include <QtGui/QPushButton>
#include <QtGui/QVBoxLayout>
#include <QtGui/QWidget>

QT_BEGIN_NAMESPACE

class Ui_FileGenDialog
{
public:
    QWidget *layoutWidget;
    QVBoxLayout *verticalLayout_2;
    QHBoxLayout *horizontalLayout_2;
    QGridLayout *gridLayout;
    QLabel *labName;
    QLineEdit *nameEdit;
    QLabel *labSize;
    QLineEdit *sizeEdit;
    QVBoxLayout *verticalLayout;
    QLabel *labIdle;
    QProgressBar *progressBar;
    QHBoxLayout *horizontalLayout;
    QPushButton *startBtn;
    QPushButton *stopBtn;
    QPushButton *quitBtn;

    void setupUi(QDialog *FileGenDialog)
    {
    if (FileGenDialog->objectName().isEmpty())
        FileGenDialog->setObjectName(QString::fromUtf8("FileGenDialog"));
    FileGenDialog->resize(391, 118);
    layoutWidget = new QWidget(FileGenDialog);
    layoutWidget->setObjectName(QString::fromUtf8("layoutWidget"));
    layoutWidget->setGeometry(QRect(10, 10, 371, 101));
    verticalLayout_2 = new QVBoxLayout(layoutWidget);
    verticalLayout_2->setObjectName(QString::fromUtf8("verticalLayout_2"));
    verticalLayout_2->setContentsMargins(0, 0, 0, 0);
    horizontalLayout_2 = new QHBoxLayout();
    horizontalLayout_2->setObjectName(QString::fromUtf8("horizontalLayout_2"));
    gridLayout = new QGridLayout();
    gridLayout->setObjectName(QString::fromUtf8("gridLayout"));
    labName = new QLabel(layoutWidget);
    labName->setObjectName(QString::fromUtf8("labName"));
    QFont font;
    font.setPointSize(12);
    labName->setFont(font);

    gridLayout->addWidget(labName, 0, 0, 1, 1);

    nameEdit = new QLineEdit(layoutWidget);
    nameEdit->setObjectName(QString::fromUtf8("nameEdit"));

    gridLayout->addWidget(nameEdit, 0, 1, 1, 1);

    labSize = new QLabel(layoutWidget);
    labSize->setObjectName(QString::fromUtf8("labSize"));
    labSize->setFont(font);
    labSize->setLayoutDirection(Qt::RightToLeft);

    gridLayout->addWidget(labSize, 1, 0, 1, 1);

    sizeEdit = new QLineEdit(layoutWidget);
    sizeEdit->setObjectName(QString::fromUtf8("sizeEdit"));

    gridLayout->addWidget(sizeEdit, 1, 1, 1, 1);


    horizontalLayout_2->addLayout(gridLayout);

    verticalLayout = new QVBoxLayout();
    verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
    labIdle = new QLabel(layoutWidget);
    labIdle->setObjectName(QString::fromUtf8("labIdle"));
    QFont font1;
    font1.setPointSize(18);
    labIdle->setFont(font1);

    verticalLayout->addWidget(labIdle);

    progressBar = new QProgressBar(layoutWidget);
    progressBar->setObjectName(QString::fromUtf8("progressBar"));
    progressBar->setValue(0);

    verticalLayout->addWidget(progressBar);


    horizontalLayout_2->addLayout(verticalLayout);


    verticalLayout_2->addLayout(horizontalLayout_2);

    horizontalLayout = new QHBoxLayout();
    horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
    horizontalLayout->setSizeConstraint(QLayout::SetDefaultConstraint);
    startBtn = new QPushButton(layoutWidget);
    startBtn->setObjectName(QString::fromUtf8("startBtn"));
    startBtn->setEnabled(false);
    startBtn->setAutoDefault(false);

    horizontalLayout->addWidget(startBtn);

    stopBtn = new QPushButton(layoutWidget);
    stopBtn->setObjectName(QString::fromUtf8("stopBtn"));
    stopBtn->setEnabled(false);
    stopBtn->setAutoDefault(false);

    horizontalLayout->addWidget(stopBtn);

    quitBtn = new QPushButton(layoutWidget);
    quitBtn->setObjectName(QString::fromUtf8("quitBtn"));

    horizontalLayout->addWidget(quitBtn);


    verticalLayout_2->addLayout(horizontalLayout);


    retranslateUi(FileGenDialog);

    QMetaObject::connectSlotsByName(FileGenDialog);
    } // setupUi

    void retranslateUi(QDialog *FileGenDialog)
    {
    FileGenDialog->setWindowTitle(QApplication::translate("FileGenDialog", "File Generator", 0, QApplication::UnicodeUTF8));
    labName->setText(QApplication::translate("FileGenDialog", "File Name", 0, QApplication::UnicodeUTF8));
    labSize->setText(QApplication::translate("FileGenDialog", "File Size", 0, QApplication::UnicodeUTF8));
    labIdle->setText(QApplication::translate("FileGenDialog", "IDLE", 0, QApplication::UnicodeUTF8));
    startBtn->setText(QApplication::translate("FileGenDialog", "START", 0, QApplication::UnicodeUTF8));
    stopBtn->setText(QApplication::translate("FileGenDialog", "STOP", 0, QApplication::UnicodeUTF8));
    quitBtn->setText(QApplication::translate("FileGenDialog", "QUIT", 0, QApplication::UnicodeUTF8));
    Q_UNUSED(FileGenDialog);
    } // retranslateUi

};

namespace Ui {
    class FileGenDialog: public Ui_FileGenDialog {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_HW5GUI_H
