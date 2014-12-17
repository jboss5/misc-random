/****************************************************************************
** Meta object code from reading C++ file 'filegenh.h'
**
** Created: Tue Apr 30 20:36:53 2013
**      by: The Qt Meta Object Compiler version 59 (Qt 4.4.3)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "../filegenh.h"
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'filegenh.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 59
#error "This file was generated using the moc from 4.4.3. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
static const uint qt_meta_data_FileGenerator[] = {

 // content:
       1,       // revision
       0,       // classname
       0,    0, // classinfo
       5,   10, // methods
       0,    0, // properties
       0,    0, // enums/sets

 // slots: signature, parameters, type, tag, flags
      15,   14,   14,   14, 0x08,
      24,   14,   14,   14, 0x08,
      34,   14,   14,   14, 0x08,
      42,   14,   14,   14, 0x08,
      49,   14,   14,   14, 0x08,

       0        // eod
};

static const char qt_meta_stringdata_FileGenerator[] = {
    "FileGenerator\0\0doStop()\0chkSize()\0"
    "start()\0stop()\0quit()\0"
};

const QMetaObject FileGenerator::staticMetaObject = {
    { &QDialog::staticMetaObject, qt_meta_stringdata_FileGenerator,
      qt_meta_data_FileGenerator, 0 }
};

const QMetaObject *FileGenerator::metaObject() const
{
    return &staticMetaObject;
}

void *FileGenerator::qt_metacast(const char *_clname)
{
    if (!_clname) return 0;
    if (!strcmp(_clname, qt_meta_stringdata_FileGenerator))
        return static_cast<void*>(const_cast< FileGenerator*>(this));
    if (!strcmp(_clname, "Ui::FileGenDialog"))
        return static_cast< Ui::FileGenDialog*>(const_cast< FileGenerator*>(this));
    return QDialog::qt_metacast(_clname);
}

int FileGenerator::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QDialog::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        switch (_id) {
        case 0: doStop(); break;
        case 1: chkSize(); break;
        case 2: start(); break;
        case 3: stop(); break;
        case 4: quit(); break;
        }
        _id -= 5;
    }
    return _id;
}
QT_END_MOC_NAMESPACE
