package dev.tomdotbat.jet.listeners.editorwindow.menubar.viewmenu.zoommenu;

import dev.tomdotbat.jet.windows.EditorWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetZoomListener implements ActionListener {
    public ResetZoomListener(EditorWindow window) { //Constructs a zoom level reset listener and attaches the editor window
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.setZoomLevel(1); //Set the zoom level back to its default value
    }

    private final EditorWindow window;
}