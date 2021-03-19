package dev.tomdotbat.jet.windows;

import dev.tomdotbat.jet.listeners.findwindow.CancelListener;
import dev.tomdotbat.jet.listeners.findwindow.CloseListener;
import dev.tomdotbat.jet.listeners.findwindow.FindListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FindWindow {
    public FindWindow(EditorWindow window) { //Find window constructor
        //Create a dialog with "Find Text" as the title
        dialog = new JDialog(window, false);
        dialog.setTitle("Find Text");

        //Set up a grid layout for the left and right of the window
        GridLayout leftGrid = new GridLayout(0, 1);

        JPanel leftGridContainer = new JPanel();
        leftGridContainer.setLayout(leftGrid);

        rightGridContainer = new JPanel();
        rightGridContainer.setLayout(new GridLayout(0, 1));

        JPanel searchContainer = new JPanel();
        searchContainer.setLayout(new BoxLayout(searchContainer, BoxLayout.LINE_AXIS));

        //Create the next and cancel button and add them into their own container
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new GridLayout(1, 0));

        JButton nextButton = new JButton("Find Next");
        nextButton.addActionListener(new FindListener(window, this));
        buttonContainer.add(nextButton);
        rightGridContainer.add(nextButton);

        rightGridContainer.add(Box.createVerticalGlue());

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CancelListener(dialog, window));
        buttonContainer.add(cancelButton);
        rightGridContainer.add(cancelButton);

        //Create a the up and down radio buttons and add them to a button group
        ButtonGroup directionGroup = new ButtonGroup();

        directionUpRadio = new JRadioButton("Up", false);
        directionUpRadio.setMnemonic(1);
        directionGroup.add(directionUpRadio);

        JRadioButton directionDownRadio = new JRadioButton("Down", true);
        directionDownRadio.setMnemonic(0);
        directionGroup.add(directionDownRadio);

        //Create the label and text entry for the search and add it to the container
        searchContainer.add(new JLabel("Find: "));

        searchGrid = new JPanel();
        searchGrid.setLayout(new GridLayout(0, 1));

        searchTermEntry = new JTextField();
        searchContainer.add(searchTermEntry);

        searchGrid.add(searchContainer);
        searchGrid.add(Box.createVerticalGlue());

        //Create the case sensitive and wrap around checkboxes and add them to a container panel
        JPanel checkBoxContainer = new JPanel();
        checkBoxContainer.setLayout(new GridLayout(3, 0));

        checkBoxContainer.add(Box.createVerticalGlue());
        caseSensitiveCheckBox = new JCheckBox("Case Sensitive");
        checkBoxContainer.add(caseSensitiveCheckBox);

        wrapAroundCheckBox = new JCheckBox("Wrap around");
        checkBoxContainer.add(wrapAroundCheckBox);

        buttonContainer.add(checkBoxContainer);
        buttonContainer.add(Box.createHorizontalStrut(5));
        buttonContainer.add(directionUpRadio);
        buttonContainer.add(directionDownRadio);

        leftGridContainer.add(searchGrid);
        leftGridContainer.add(buttonContainer);

        //Build the main container and add everything to it
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.LINE_AXIS));
        container.setBorder(new EmptyBorder(5, 5, 5, 5));

        container.add(leftGridContainer);
        container.add(rightGridContainer);

        //Add the container to the window
        dialog.add(container);

        //Size the window to the preferred content size
        dialog.pack();

        //Add the close listener to the window to remove highlights
        dialog.addWindowListener(new CloseListener(window));

        //Set the close operation and center the window
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);

        //Show the window
        dialog.setVisible(true);
    }

    public String getSearchTerm() { //Search term getter
        return searchTermEntry.getText();
    }

    public boolean getDirection() { //Return the search direction, up is true, down is false
        return directionUpRadio.isSelected();
    }

    public boolean getCaseSensitivity() { //Return the case sensitivity state, true if case sensitive
        return caseSensitiveCheckBox.isSelected();
    }

    public boolean getWrapAround() { //Return with the finder should wrap around the document
        return wrapAroundCheckBox.isSelected();
    }

    protected final JDialog dialog; //Used in replace window
    protected final JPanel searchGrid;
    protected final JPanel rightGridContainer;
    protected final JButton cancelButton;

    private final JTextField searchTermEntry;
    private final JCheckBox caseSensitiveCheckBox;
    private final JCheckBox wrapAroundCheckBox;
    private final JRadioButton directionUpRadio;
}