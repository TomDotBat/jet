package dev.tomdotbat.jet.listeners.editorwindow.menubar.filemenu;

import dev.tomdotbat.jet.documents.Document;
import dev.tomdotbat.jet.documents.DocumentStorage;
import dev.tomdotbat.jet.windows.EditorWindow;
import dev.tomdotbat.jet.windows.FileBrowser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveFileListener implements ActionListener {
    public SaveFileListener(EditorWindow window) { //Constructs the save file listener and attaches the editor window
        this.window = window;
    }

    public SaveFileListener(EditorWindow window, JDialog popup) {
        this.popup = popup;
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Document document = window.getDocument();

        String location = document.getLocation();

        if (location.equals("Untitled")) { //Untitled documents don't have a location set yet so we ask for one
            location = new FileBrowser("Save document").getFileLocation(); //Ask the user for a location to save this document at
            if (location == null) return; //Save cancelled, exit this method

            document.setLocation(location);
        }

        if (location == null) return;
        document.setBody(window.getText()); //Update the document body to the latest text entry content
        DocumentStorage.writeDocument(document);

        if (popup == null) return; //If we had a popup passed close the main window
        window.dispose();
    }

    protected final EditorWindow window;
    private JDialog popup;
}