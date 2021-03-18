package dev.tomdotbat.jet.listeners.editorwindow.menubar.editmenu;

import dev.tomdotbat.jet.windows.EditorWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CopyListener implements ActionListener {
    public CopyListener(EditorWindow window) { //Constructs the copy text listener and attaches it to the editor window
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextArea textEntry = window.getTextEntry();

        String selectedText = textEntry.getSelectedText(); //Get the selected text, if nothing is selected do nothing
        if (selectedText == null) return;

        StringSelection selection = new StringSelection(selectedText); //Set the contents of the system clipboard to whatever is selected
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }

    private final EditorWindow window;
}