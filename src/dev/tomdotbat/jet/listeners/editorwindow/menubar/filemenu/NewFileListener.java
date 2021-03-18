package dev.tomdotbat.jet.listeners.editorwindow.menubar.filemenu;

import dev.tomdotbat.jet.windows.EditorWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewFileListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        new EditorWindow().setVisible(true); //Makes a new editor window
    }
}