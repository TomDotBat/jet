package dev.tomdotbat.jet.listeners.findwindow;

import dev.tomdotbat.jet.windows.EditorWindow;
import dev.tomdotbat.jet.windows.FindWindow;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BiConsumer;

public class FindListener implements ActionListener {
    public FindListener(EditorWindow editorWindow, FindWindow findWindow) { //Constructs the find listener
        this.editorWindow = editorWindow;
        this.findWindow = findWindow;

        this.highlighter = editorWindow.getTextEntry().getHighlighter();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextArea documentEntry = editorWindow.getTextEntry();

        highlighter.removeAllHighlights();
        findNextOccurrence(documentEntry.getDocument(), findWindow.getSearchTerm(), findWindow.getDirection(), findWindow.getCaseSensitivity(), (pos, len) -> { //Run this code on all occurrences
             try {
                documentEntry.scrollRectToVisible(documentEntry.modelToView(pos)); //Scrolls to a specific part of the document, see: https://stackoverflow.com/a/13438455
            }
            catch (Exception ignored) {}

            try {
                highlighter.addHighlight(pos, pos + len, painter); //Highlight the occurrence
            }
            catch (Exception ignored) {}
        });
    }

    protected void findNextOccurrence(Document document, String searchTerm, boolean searchDirection, boolean caseSensitive, BiConsumer<Integer, Integer> callback) { //Finds the next occurrence of a search term and runs the callback on it
        if (!caseSensitive) searchTerm = searchTerm.toLowerCase(); //Transform the search term to lower case if not case sensitive

        int searchTermLength = searchTerm.length();
        if (searchTermLength < 1) return;  //Check the provided search term is big enough first

        int pos = lastPos;
        boolean foundOccurrence = false;

        try {
            while (searchDirection ? (pos >= 0) : (pos + searchTermLength < (document.getLength() + 1))) { //Repeat until we reach the end of the document
                String documentString = document.getText(pos, searchTermLength); //Get text from the document at the current position
                if (!caseSensitive) documentString = documentString.toLowerCase();

                if (documentString.equals(searchTerm)) {
                    callback.accept(pos, searchTermLength); //Call the callback with the position and result length
                    foundOccurrence = true;
                    lastPos = searchDirection ? (pos - searchTermLength - 1) : (pos + searchTermLength);
                    break;
                }

                pos = searchDirection ? (pos - 1) : (pos + 1); //Move to the next character in the correct direction
            }
        }
        catch (Exception ignored) {}

        if (foundOccurrence) return;
        lastPos = searchDirection ? (document.getLength() - searchTermLength) : 0; //Set the next pos to the top/bottom depending on the current direction

        if (!isWrapping && findWindow.getWrapAround()) { //Wrap around the document if it is set to do so
            isWrapping = true; //This prevents an infinite loop when wrapping is enabled
            findNextOccurrence(document, searchTerm, searchDirection, caseSensitive, callback);
            isWrapping = false;
            return;
        }

        //Show a dialog to say there are no occurrences of the search term
        JOptionPane.showMessageDialog(null, "No occurrences of '" + searchTerm + "' were found.");
    }

    protected final EditorWindow editorWindow;
    protected final FindWindow findWindow;

    private int lastPos = 0;
    private boolean isWrapping = false;
    private final Highlighter highlighter;
    private static final Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(ColorUIResource.yellow); //Cache the highlight painter once
}
