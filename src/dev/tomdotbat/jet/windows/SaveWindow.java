package dev.tomdotbat.jet.windows;

import dev.tomdotbat.jet.listeners.editorwindow.menubar.filemenu.ExitListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.filemenu.SaveFileListener;
import dev.tomdotbat.jet.listeners.findwindow.CancelListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SaveWindow {
    public SaveWindow(EditorWindow editorWindow) { //Save window constructor
        JDialog dialog = new JDialog(editorWindow, true); //Create a new dialog and set its title
        dialog.setTitle("JET");

        //Set the layout to grid layout for both containers
        GridLayout layout = new GridLayout(0, 1);
        layout.setHgap(10);
        layout.setVgap(10);

        //Create the main container
        JPanel container = new JPanel();
        container.setLayout(layout);
        container.setBorder(new EmptyBorder(5, 5, 5, 5));

        //Add all of the elements to the container
        container.add(new JLabel("Do you want to exit without saving?"));

        //Create a button container and set the layout
        JPanel buttonContainer = new JPanel();
        GridLayout buttonLayout = new GridLayout(1, 0);
        buttonLayout.setHgap(5);
        buttonContainer.setLayout(buttonLayout);

        //Create buttons and add their listeners
        //We reuse listeners here from the main editor window to cut down on repeated code and increase efficiency
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveFileListener(editorWindow, dialog));
        buttonContainer.add(saveButton);

        JButton dontSaveButton = new JButton("Don't Save");
        dontSaveButton.addActionListener(new ExitListener(editorWindow, false));
        buttonContainer.add(dontSaveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CancelListener(dialog, editorWindow));
        buttonContainer.add(cancelButton);
        container.add(buttonContainer);

        dialog.add(container);

        //Set a default size and center the window
        dialog.setSize(320, 120);
        dialog.setLocationRelativeTo(null);

        //Set the close operation
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Show the window
        dialog.setVisible(true);
    }
}
