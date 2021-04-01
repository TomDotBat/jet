package dev.tomdotbat.jet.windows.editor.menubar;

import dev.tomdotbat.jet.listeners.editorwindow.menubar.formatmenu.FontListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.formatmenu.TextWrapListener;
import dev.tomdotbat.jet.preferences.PreferenceManager;
import dev.tomdotbat.jet.windows.EditorWindow;
import dev.tomdotbat.jet.windows.editor.MenuBar;

import javax.swing.*;

public class FormatMenu extends JMenu  {
    public FormatMenu(EditorWindow editor) { //Format menu constructor
        super("Format"); //Create a JMenu with the label "Format"

        setMnemonic('t'); //Set the shortcut key for the menu

        JCheckBoxMenuItem wordWrapCheckBox = new JCheckBoxMenuItem("Word Wrap", true); //Create a checkbox to toggle word wrapping
        wordWrapCheckBox.addItemListener(new TextWrapListener(editor));
        wordWrapCheckBox.setMnemonic('w');
        wordWrapCheckBox.setSelected(PreferenceManager.getInstance().getWordWrap());
        add(wordWrapCheckBox);

        add(MenuBar.createMenuItem("Font...", new FontListener(editor), 'f')); //Font menu
    }
}
