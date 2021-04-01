package dev.tomdotbat.jet.listeners.editorwindow.menubar.filemenu;

import dev.tomdotbat.jet.preferences.PreferenceManager;
import dev.tomdotbat.jet.windows.EditorWindow;
import dev.tomdotbat.jet.windows.SaveWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitListener implements ActionListener {
    public ExitListener(EditorWindow window, boolean saveBefore) { //Constructs the exit listener and attaches the editor window
        this.window = window;
        this.saveBefore = saveBefore; //Should we make sure the file is saved before exiting?
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String curText = window.getText();
        String latestSavedText = window.getDocument().getBody();

        if (!saveBefore || curText.equals(latestSavedText)) {
            PreferenceManager.getInstance().savePreferences();
            window.dispose();
        }
        else new SaveWindow(window);
    }

    private final EditorWindow window;
    private final boolean saveBefore;
}