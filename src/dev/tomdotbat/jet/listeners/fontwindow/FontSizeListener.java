package dev.tomdotbat.jet.listeners.fontwindow;

import dev.tomdotbat.jet.preferences.PreferenceManager;
import dev.tomdotbat.jet.windows.EditorWindow;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class FontSizeListener implements ChangeListener {
    public FontSizeListener(EditorWindow editorWindow) {  //Constructs the font size listener
        this.editorWindow = editorWindow;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int newFontSize = (int) ((SpinnerModel) e.getSource()).getValue(); //Get the new font size from the spinner

        //Set the new font size preference
        PreferenceManager.getInstance().setFontSize(newFontSize);

        //Change the font size in the editor
        Font curFont = editorWindow.getFont();
        editorWindow.setFont(new Font(curFont.getFontName(), curFont.getStyle(), newFontSize));

        editorWindow.setZoomLevel(editorWindow.getZoomLevel()); //Recalculates the font size to add the zoom
    }

    private final EditorWindow editorWindow;
}
