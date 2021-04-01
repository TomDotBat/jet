package dev.tomdotbat.jet.listeners.editorwindow.menubar.viewmenu.zoommenu;

import dev.tomdotbat.jet.preferences.PreferenceManager;
import dev.tomdotbat.jet.windows.EditorWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZoomOutListener implements ActionListener {
    public ZoomOutListener(EditorWindow window) { //Constructs a zoom in listener and attaches the editor window
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        float zoomLevel = window.getZoomLevel() - 0.15f; //Decreases the zoom level by 15%
        window.setZoomLevel(zoomLevel);
        PreferenceManager.getInstance().setZoomLevel(zoomLevel);
    }

    private final EditorWindow window;
}