package dev.tomdotbat.jet.listeners.editorwindow.menubar.formatmenu;

import dev.tomdotbat.jet.windows.EditorWindow;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class TextWrapListener implements ItemListener {
    public TextWrapListener(EditorWindow window) { //Constructs the text wrap toggle listener and attaches the editor window
        this.window = window;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        window.setTextWrap(e.getStateChange() == ItemEvent.SELECTED); //Set the wrapping state to on if the check is visible
    }

    private final EditorWindow window;
}
