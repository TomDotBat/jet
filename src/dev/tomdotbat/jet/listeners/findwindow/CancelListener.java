package dev.tomdotbat.jet.listeners.findwindow;

import dev.tomdotbat.jet.windows.EditorWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelListener implements ActionListener {
    public CancelListener(JDialog dialog, EditorWindow editorWindow) { //Constructs the cancel listener
        this.dialog = dialog;
        this.editorWindow = editorWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dialog.dispose();
        editorWindow.getTextEntry().getHighlighter().removeAllHighlights();
    }

    private final JDialog dialog;
    private final EditorWindow editorWindow;
}