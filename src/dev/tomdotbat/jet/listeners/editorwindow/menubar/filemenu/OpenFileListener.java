package dev.tomdotbat.jet.listeners.editorwindow.menubar.filemenu;

import dev.tomdotbat.jet.documents.Document;
import dev.tomdotbat.jet.documents.DocumentStorage;
import dev.tomdotbat.jet.windows.EditorWindow;
import dev.tomdotbat.jet.documents.FileBrowser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenFileListener implements ActionListener {
    public OpenFileListener(EditorWindow window) { //Constructs the open file listener and attaches the editor window
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String location = new FileBrowser("Open an existing file").getFileLocation(); //Request a file path from the user
        if (location == null) return;

        Document document = DocumentStorage.readDocument(location);
        if (document == null) return;

        window.setTitle(location + " - JET"); //Update the editor with the new document details
        window.setDocument(document);
        window.updateLineNumber();
    }

    private final EditorWindow window;
}
