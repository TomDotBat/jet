package dev.tomdotbat.jet.listeners.editorwindow.menubar.filemenu;

import dev.tomdotbat.jet.windows.EditorWindow;
import dev.tomdotbat.jet.documents.FileBrowser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveFileAsListener extends SaveFileListener implements ActionListener {
    public SaveFileAsListener(EditorWindow window) { //Constructs the save file as listener and attaches the editor window
        super(window); //Use the constructor of SaveFileListener
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String location = new FileBrowser("Save document as").getFileLocation(); //Ask the user for a location to save this document at
        if (location == null) return; //Save cancelled, exit this method

        window.getDocument().setLocation(location); //Override the document location to the new one
        super.actionPerformed(e); //Once the new location is set we can call the same action that SaveFileListener uses

        window.setTitle(location + " - JET");
        window.updateLineNumber();
    }
}
