package dev.tomdotbat.jet.windows;

import dev.tomdotbat.jet.listeners.replacewindow.ReplaceListener;

import javax.swing.*;

public class ReplaceWindow extends FindWindow {
    public ReplaceWindow(EditorWindow window) { //Replace window constructor
        super(window);

        //Change the title
        dialog.setTitle("Find and Replace Text");

        //Create a new container for the replacement entry
        replacementContainer = new JPanel();
        replacementContainer.setLayout(new BoxLayout(replacementContainer, BoxLayout.LINE_AXIS));

        //Add a replace label before the entry
        replacementContainer.add(new JLabel("Replace: "));

        //Create the replacement entry
        replacementEntry = new JTextField();
        replacementContainer.add(replacementEntry);

        //Remove the glue and insert the new container into its place
        searchGrid.remove(1);
        searchGrid.add(replacementContainer);

        //Remove the cancel button and glue
        rightGridContainer.remove(1);
        rightGridContainer.remove(1);

        //Add the replace button
        replaceButton = new JButton("Replace");
        replaceButton.addActionListener(new ReplaceListener(window, this));
        rightGridContainer.add(replaceButton);

        //Add back the cancel button
        rightGridContainer.add(cancelButton);
    }

    public String getReplacementText() {
        return replacementEntry.getText();
    }

    private final JPanel replacementContainer;

    private final JTextField replacementEntry;
    private final JButton replaceButton;
}