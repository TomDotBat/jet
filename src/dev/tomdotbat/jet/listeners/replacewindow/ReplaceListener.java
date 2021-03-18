package dev.tomdotbat.jet.listeners.replacewindow;

import dev.tomdotbat.jet.listeners.findwindow.FindListener;
import dev.tomdotbat.jet.windows.EditorWindow;
import dev.tomdotbat.jet.windows.ReplaceWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplaceListener extends FindListener {
    public ReplaceListener(EditorWindow editorWindow, ReplaceWindow replaceWindow) { //Constructs the replace listener
        super(editorWindow, replaceWindow);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String replacementText = ((ReplaceWindow) findWindow).getReplacementText(); //Use the replace window (stored as a find window) to get the replacement text by casting it back
        JTextArea documentEntry = editorWindow.getTextEntry();

        findNextOccurrence(documentEntry.getDocument(), findWindow.getSearchTerm(), findWindow.getDirection(), findWindow.getCaseSensitivity(), (pos, len) -> { //Run this code on all occurrences
            try {
                documentEntry.scrollRectToVisible(documentEntry.modelToView(pos)); //Scrolls to a specific part of the document, see: https://stackoverflow.com/a/13438455
            }
            catch (Exception ignored) {}

            try {
                documentEntry.replaceRange(replacementText, pos, pos + len); //Replace the found text with the replacement
            }
            catch (Exception ignored) {}
        });
    }
}