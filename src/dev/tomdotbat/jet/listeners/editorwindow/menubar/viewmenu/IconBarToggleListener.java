package dev.tomdotbat.jet.listeners.editorwindow.menubar.viewmenu;

import dev.tomdotbat.jet.preferences.PreferenceManager;
import dev.tomdotbat.jet.windows.EditorWindow;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class IconBarToggleListener implements ItemListener {
    public IconBarToggleListener(EditorWindow window) { //Constructs the icon bar toggle listener
        this.window = window;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        boolean state = e.getStateChange() == ItemEvent.SELECTED; //Is the toggle on or off?

        window.getIconBar().setVisible(state);
        PreferenceManager.getInstance().setShowIconBar(state);
    }

    private final EditorWindow window;
}