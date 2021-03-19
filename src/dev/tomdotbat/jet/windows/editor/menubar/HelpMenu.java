package dev.tomdotbat.jet.windows.editor.menubar;

import dev.tomdotbat.jet.listeners.editorwindow.menubar.HelpListener;
import dev.tomdotbat.jet.windows.EditorWindow;
import dev.tomdotbat.jet.windows.editor.MenuBar;

import javax.swing.*;

public class HelpMenu extends JMenu {
    public HelpMenu(EditorWindow editor) { //Help menu constructor
        super("Help"); //Create a JMenu with the label "Help"

        setMnemonic('h'); //Set the shortcut key for the menu

        add(MenuBar.createMenuItem("Help", new HelpListener(), 'h')); //Help menu
    }
}
