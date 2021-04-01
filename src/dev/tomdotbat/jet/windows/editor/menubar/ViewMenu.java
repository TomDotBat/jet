package dev.tomdotbat.jet.windows.editor.menubar;

import dev.tomdotbat.jet.listeners.editorwindow.menubar.viewmenu.StatusBarToggleListener;
import dev.tomdotbat.jet.preferences.PreferenceManager;
import dev.tomdotbat.jet.windows.EditorWindow;

import javax.swing.*;

public class ViewMenu extends JMenu {
    public ViewMenu(EditorWindow editor) { //View menu constructor
        super("View"); //Create a JMenu with the label "View"

        setMnemonic('v'); //Set the shortcut key for the menu

        add(new ZoomMenu(editor)); //Add the zoom menu

        JCheckBoxMenuItem statusBarCheckBox = new JCheckBoxMenuItem("Status Bar", true); //Create a checkbox to toggle the status bar
        statusBarCheckBox.addItemListener(new StatusBarToggleListener(editor));
        statusBarCheckBox.setMnemonic('s');
        statusBarCheckBox.setSelected(PreferenceManager.getInstance().getShowStatusBar());
        add(statusBarCheckBox);
    }
}
