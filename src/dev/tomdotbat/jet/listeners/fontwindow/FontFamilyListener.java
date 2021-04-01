package dev.tomdotbat.jet.listeners.fontwindow;

import dev.tomdotbat.jet.preferences.PreferenceManager;
import dev.tomdotbat.jet.windows.EditorWindow;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FontFamilyListener implements ItemListener {
    public FontFamilyListener(EditorWindow editorWindow) { //Constructs the font family liste   ner
        this.editorWindow = editorWindow;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) return; //Only respond to item selections

        String selectedFont = e.getItem().toString();

        //Set the new font family preference
        PreferenceManager.getInstance().setFontName(selectedFont);

        //Change the font family in the editor
        Font curFont = editorWindow.getFont();
        editorWindow.setFont(new Font(selectedFont, curFont.getStyle(), curFont.getSize()));
    }

    private final EditorWindow editorWindow;
}
