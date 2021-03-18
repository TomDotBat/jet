package dev.tomdotbat.jet.windows;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FontWindow {
    public FontWindow(EditorWindow editorWindow) { //Font window constructor
        JDialog dialog = new JDialog(editorWindow, true); //Create a dialog and set the title
        dialog.setTitle("JET - Font");

        //Create a main container and set up it's layout
        JPanel container = new JPanel();
        GridLayout layout = new GridLayout(0, 2);
        layout.setHgap(10);
        layout.setVgap(10);

        container.setLayout(layout);
        container.setBorder(new EmptyBorder(5, 5, 5, 5));

        //Add font size spinner components
        JLabel fontSize = new JLabel("Font size");
        container.add(fontSize);

        int currentFontSize = 0; //editorWindow.getPrefs().getFontSize();
        SpinnerModel fontSizeModel = new SpinnerNumberModel(currentFontSize, 11, 72, 1);
        //fontSizeModel.addChangeListener(new prefsFontSizeListener(editorWindow));

        JSpinner fontSizeSelector = new JSpinner(fontSizeModel);
        container.add(fontSizeSelector);

        //Add font selector components
        JLabel fontLabel = new JLabel("Font Selector");
        container.add(fontLabel);

        //Get the font list from the window
        JComboBox<String> fontList = new JComboBox<>(); //editorWindow.getFontList());
        //fontList.addItemListener(new prefsFontListener(this));
        container.add(fontList);

        //Add font style selector components
        JLabel fontStyleLabel = new JLabel("Font Style Selector");
        container.add(fontStyleLabel);

        //Create a combo box model to allow changes to the contents while the program runs
        styleModel = new DefaultComboBoxModel<>();

        //Create the combo box with the new model
        JComboBox<String> fontStyleList = new JComboBox<>(styleModel);
        //fontStyleList.addItemListener(new prefsFontStyleListener(editorWindow));
        container.add(fontStyleList);


        //Set the default dialog size
        dialog.setSize(400, 200);

        //Make the dialog open in the center of the screen (not the top left)
        dialog.setLocationRelativeTo(null);

        //Align the font components with the actual preferences
        String initialFontFamily = ""; //editorWindow.getPrefs().getFontName();
        String initialFont = ""; //editorWindow.getPrefs().getFontName();

        initialFontFamily = initialFontFamily.replace(" Bold", "");
        initialFontFamily = initialFontFamily.replace(" Italic", "");

        fontList.setSelectedItem(initialFontFamily);
        fontStyleList.setSelectedItem(initialFont);

        //Set the close operation of the dialog
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Add the container to the dialog and make it visible
        dialog.add(container);
        dialog.setVisible(true);
    }

    //returns the current styleModel
    public DefaultComboBoxModel<String> getDefaultComboBoxModel() {
        return styleModel;
    }

    private final DefaultComboBoxModel<String> styleModel;
}