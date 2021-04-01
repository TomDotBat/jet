package dev.tomdotbat.jet.windows;

import dev.tomdotbat.jet.listeners.fontwindow.FontFamilyListener;
import dev.tomdotbat.jet.listeners.fontwindow.FontSizeListener;
import dev.tomdotbat.jet.preferences.PreferenceManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FontWindow {
    public FontWindow(EditorWindow editorWindow) { //Font window constructor
        JDialog dialog = new JDialog(editorWindow, true); //Creates a dialog on top of the editor
        dialog.setTitle("JET - Font"); //Set the dialog title

        //Set the dialog's size
        dialog.setSize(330, 140);

        //Center the dialog
        dialog.setLocationRelativeTo(null);

        PreferenceManager preferenceManager = PreferenceManager.getInstance();

        //Create a main container and set up its layout
        JPanel container = new JPanel();
        GridLayout layout = new GridLayout(0, 2);
        layout.setHgap(30);
        layout.setVgap(10);

        container.setLayout(layout);
        container.setBorder(new EmptyBorder(5, 5, 5, 5));

        //Add a font size label
        JLabel fontSizeLbl = new JLabel("Font size:");
        container.add(fontSizeLbl);

        //Create a font size spinner
        SpinnerModel fontSizeSpinnerModel = new SpinnerNumberModel(preferenceManager.getFontSize(), 8, 128, 2);
        fontSizeSpinnerModel.addChangeListener(new FontSizeListener(editorWindow));

        JSpinner fontSizeSpinner = new JSpinner(fontSizeSpinnerModel);
        container.add(fontSizeSpinner);

        //Add a font selector label
        JLabel fontFamilyLbl = new JLabel("Font Family:");
        container.add(fontFamilyLbl);

        //Create a font list combobox
        JComboBox<String> fontFamilyComboBox = new JComboBox<>(fontList);
        fontFamilyComboBox.addItemListener(new FontFamilyListener(editorWindow));
        fontFamilyComboBox.setSelectedItem(preferenceManager.getFontName());
        container.add(fontFamilyComboBox);

        //Put the container in the dialog and make it pop up
        dialog.add(container);
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private static final String[] fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(); //Cache the system's available fonts on startup
}