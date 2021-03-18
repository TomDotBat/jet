package dev.tomdotbat.jet.listeners.editorwindow.menubar.editmenu;

import dev.tomdotbat.jet.windows.EditorWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasteListener implements ActionListener {
    public PasteListener(EditorWindow window) { //Constructs the paste text listener and attaches it to the editor window
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextArea textEntry = window.getTextEntry();

        String pasteText = null;
        try {
            pasteText = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor); //Try to get text to paste from the clipboard
        }
        catch (Exception ignored) {}

        if (pasteText == null) return; //Don't paste nothing

        if (textEntry.getSelectedText() == null) { //Insert the text at the current cursor position if nothing is selected
            textEntry.insert(pasteText, textEntry.getCaretPosition());
        }
        else { //If something is selected upon paste we should replace it
            textEntry.replaceSelection(pasteText);
        }
    }

    private final EditorWindow window;
}
