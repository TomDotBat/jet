package dev.tomdotbat.jet.listeners.editorwindow.menubar.formatmenu;

import dev.tomdotbat.jet.windows.EditorWindow;
import dev.tomdotbat.jet.windows.FontWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FontListener implements ActionListener {
    public FontListener(EditorWindow window) { //Constructs the font menu listener and attaches the editor window
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new FontWindow(window); //Create a font editor window
    }

    private final EditorWindow window;
}