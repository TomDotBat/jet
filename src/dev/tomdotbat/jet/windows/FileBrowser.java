package dev.tomdotbat.jet.documents;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileBrowser extends JFrame {
    public FileBrowser(String title) { //Constructs a file browser frame with a specific title
        this.title = title;
    }

    public String getFileLocation() { //Gets a file location from a user when requested
        JFileChooser fileChooser = new JFileChooser(); //Create a file chooser and set its title
        fileChooser.setDialogTitle(title);

        String filePath = null;

        //Set the filter to only show .txt files and set the default selection to Untitled.txt
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Document (*.txt)", "txt"));
        fileChooser.setSelectedFile(new File("Untitled.txt"));

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) //Get the file path if the user didn't cancel
            filePath = fileChooser.getSelectedFile().getPath();

        //Close this frame
        dispose();

        return filePath;
    }

    private final String title;
}
