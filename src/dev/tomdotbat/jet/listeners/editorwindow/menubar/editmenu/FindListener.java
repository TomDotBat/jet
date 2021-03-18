package dev.tomdotbat.jet.listeners.editorwindow.menubar.editmenu;

import dev.tomdotbat.jet.windows.EditorWindow;
import dev.tomdotbat.jet.windows.FindWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindListener implements ActionListener {
    public FindListener(EditorWindow window) { //Constructs the find text listener and attaches it to the editor window
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new FindWindow(window); //Open the find window
    }

    private final EditorWindow window;
}