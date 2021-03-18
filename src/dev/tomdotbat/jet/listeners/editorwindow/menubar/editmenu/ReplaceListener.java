package dev.tomdotbat.jet.listeners.editorwindow.menubar.editmenu;

import dev.tomdotbat.jet.windows.EditorWindow;
import dev.tomdotbat.jet.windows.ReplaceWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplaceListener implements ActionListener {
    public ReplaceListener(EditorWindow window) { //Constructs the replace text listener and attaches it to the editor window
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new ReplaceWindow(window); //Open the replace window
    }

    private final EditorWindow window;
}