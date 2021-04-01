package dev.tomdotbat.jet.listeners.editorwindow.menubar.viewmenu;

import dev.tomdotbat.jet.preferences.PreferenceManager;
import dev.tomdotbat.jet.windows.EditorWindow;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class StatusBarToggleListener implements ItemListener {
    public StatusBarToggleListener(EditorWindow window) { //Constructs the toolbar toggle listener and attaches the editor window
        this.window = window;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        boolean state = e.getStateChange() == ItemEvent.SELECTED; //Is the toggle on or off?

        window.getStatusBar().setVisible(state);
        PreferenceManager.getInstance().setShowStatusBar(state);
    }

    private final EditorWindow window;
}