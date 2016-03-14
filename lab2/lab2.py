from PyQt4 import QtCore, QtGui, uic
import sys
from processing import convert

qtCreatorFile = "lab2.ui"
sourceImageFile = "lab2_sample.jpg"
changedImageFile = "lab2result.jpg"

Ui_MainWindow, QtBaseClass = uic.loadUiType(qtCreatorFile)


class MyApp(QtGui.QMainWindow, Ui_MainWindow):
    def __init__(self):
        QtGui.QMainWindow.__init__(self)
        Ui_MainWindow.__init__(self)
        self.setupUi(self)
        self.lineEditBeforeR.textChanged.connect(self.edit_text_br)
        self.lineEditBeforeB.textChanged.connect(self.edit_text_bb)
        self.lineEditBeforeG.textChanged.connect(self.edit_text_bg)
        self.lineEditAfterR.textChanged.connect(self.edit_text_ar)
        self.lineEditAfterB.textChanged.connect(self.edit_text_ab)
        self.lineEditAfterG.textChanged.connect(self.edit_text_ag)
        self.lineEditBeforeR.setText(str(0))
        self.lineEditBeforeB.setText(str(0))
        self.lineEditBeforeG.setText(str(0))
        self.lineEditAfterR.setText(str(0))
        self.lineEditAfterB.setText(str(0))
        self.lineEditAfterG.setText(str(0))
        self.dialBeforeR.valueChanged.connect(self.dial_br)
        self.dialBeforeG.valueChanged.connect(self.dial_bg)
        self.dialBeforeB.valueChanged.connect(self.dial_bb)
        self.dialAfterR.valueChanged.connect(self.dial_ar)
        self.dialAfterG.valueChanged.connect(self.dial_ag)
        self.dialAfterB.valueChanged.connect(self.dial_ab)
        self.pixmap_after = QtGui.QPixmap(sourceImageFile)
        self.pixmap_before = QtGui.QPixmap(sourceImageFile)
        self.labelImageBefore.installEventFilter(self)
        self.labelImageAfter.installEventFilter(self)
        self.labelImageAfter.setPixmap(self.pixmap_after.scaled(
                self.labelImageAfter.width(),
                self.labelImageAfter.height(),
                QtCore.Qt.KeepAspectRatio))
        self.labelImageBefore.setPixmap(self.pixmap_before.scaled(
                self.labelImageBefore.width(),
                self.labelImageBefore.height(),
                QtCore.Qt.KeepAspectRatio))
        self.pushButton.clicked.connect(self.change_colors)

    def change_colors(self):
        try:
            radius = float(self.lineEditRadius.text())
            source = (
                int(self.lineEditBeforeR.text()),
                int(self.lineEditBeforeG.text()),
                int(self.lineEditBeforeB.text()))
            target = (
                int(self.lineEditAfterR.text()),
                int(self.lineEditAfterG.text()),
                int(self.lineEditAfterB.text()))
            convert(sourceImageFile, source, radius, target, changedImageFile)
            self.pixmap_after = QtGui.QPixmap(changedImageFile)
            self.labelImageAfter.setPixmap(self.pixmap_after.scaled(
                self.labelImageAfter.width(),
                self.labelImageAfter.height(),
                QtCore.Qt.KeepAspectRatio))
        except ValueError:
            pass

    def eventFilter(self, widget, event):
        if event.type() == QtCore.QEvent.Resize and widget is self.labelImageBefore:
            self.labelImageBefore.setPixmap(self.pixmap_before.scaled(
                self.labelImageBefore.width(), self.labelImageBefore.height(),
                QtCore.Qt.KeepAspectRatio))
            return True
        elif event.type() == QtCore.QEvent.Resize and widget is self.labelImageAfter:
            self.labelImageAfter.setPixmap(self.pixmap_after.scaled(
                self.labelImageAfter.width(), self.labelImageAfter.height(),
                QtCore.Qt.KeepAspectRatio))
            return True
        return QtGui.QMainWindow.eventFilter(self, widget, event)

    def edit_text_br(self):
        try:
            a = int(self.lineEditBeforeR.text())
            if a < 0:
                a = 0
                self.lineEditBeforeR.setText(str(a))
            elif a > 255:
                a = 255
                self.lineEditBeforeR.setText(str(a))
            self.dialBeforeR.setValue(a)
        except ValueError:
            pass

    def edit_text_bb(self):
        try:
            a = int(self.lineEditBeforeB.text())
            if a < 0:
                a = 0
                self.lineEditBeforeB.setText(str(a))
            elif a > 255:
                a = 255
                self.lineEditBeforeB.setText(str(a))
            self.dialBeforeB.setValue(a)
        except ValueError:
            pass

    def edit_text_bg(self):
        try:
            a = int(self.lineEditBeforeG.text())
            if a < 0:
                a = 0
                self.lineEditBeforeG.setText(str(a))
            elif a > 255:
                a = 255
                self.lineEditBeforeG.setText(str(a))
            self.dialBeforeG.setValue(a)
        except ValueError:
            pass

    def edit_text_ar(self):
        try:
            a = int(self.lineEditAfterR.text())
            if a < 0:
                a = 0
                self.lineEditAfterR.setText(str(a))
            elif a > 255:
                a = 255
                self.lineEditAfterR.setText(str(a))
            self.dialAfterR.setValue(a)
        except ValueError:
            pass

    def edit_text_ab(self):
        try:
            a = int(self.lineEditAfterB.text())
            if a < 0:
                a = 0
                self.lineEditAfterB.setText(str(a))
            elif a > 255:
                a = 255
                self.lineEditAfterB.setText(str(a))
            self.dialAfterB.setValue(a)
        except ValueError:
            pass

    def edit_text_ag(self):
        try:
            a = int(self.lineEditAfterG.text())
            if a < 0:
                a = 0
                self.lineEditAfterG.setText(str(a))
            elif a > 255:
                a = 255
                self.lineEditAfterG.setText(str(a))
            self.dialAfterG.setValue(a)
        except ValueError:
            pass

    def dial_br(self):
        self.lineEditBeforeR.setText(str(self.dialBeforeR.value()))

    def dial_bb(self):
        self.lineEditBeforeB.setText(str(self.dialBeforeB.value()))

    def dial_bg(self):
        self.lineEditBeforeG.setText(str(self.dialBeforeG.value()))

    def dial_ar(self):
        self.lineEditAfterR.setText(str(self.dialAfterR.value()))

    def dial_ab(self):
        self.lineEditAfterB.setText(str(self.dialAfterB.value()))

    def dial_ag(self):
        self.lineEditAfterG.setText(str(self.dialAfterG.value()))


if __name__ == "__main__":
    app = QtGui.QApplication(sys.argv)
    window = MyApp()
    window.show()
    sys.exit(app.exec_())
