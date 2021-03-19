package dev.tomdotbat.jet.windows.editor.menubar;

import dev.tomdotbat.jet.listeners.editorwindow.menubar.editmenu.CopyListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.editmenu.FindListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.editmenu.PasteListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.editmenu.ReplaceListener;
import dev.tomdotbat.jet.windows.EditorWindow;
import dev.tomdotbat.jet.windows.editor.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class EditMenu extends JMenu {
    public EditMenu(EditorWindow editor) { //Edit menu constructor
        super("Edit"); //Create a JMenu with the label "Edit"

        setMnemonic('e'); //Set the shortcut key for the menu

        JMenuItem undoBtn = MenuBar.createMenuItem("Undo", 'u', //Undo
                KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx())); //Ctrl+Z
        undoBtn.setEnabled(false); //Default to be disabled
        editor.setUndoBtn(undoBtn); //Store the undo button on the editor for use with the history manager
        add(undoBtn);

        JMenuItem redoBtn = MenuBar.createMenuItem("Redo", 'r', //Redo
                KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx())); //Ctrl+Y
        redoBtn.setEnabled(false); //Default to be disabled
        editor.setRedoBtn(redoBtn); //Store the redo button on the editor for use with the history manager
        add(redoBtn);

        add(MenuBar.createMenuItem("Copy", new CopyListener(editor), 'c', //Copy
                KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()))); //Ctrl+C

        add(MenuBar.createMenuItem("Paste", new PasteListener(editor), 'c', //Paste
                KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()))); //Ctrl+V

        add(new JSeparator()); //Add a spacer between clipboard functions and searching

        add(MenuBar.createMenuItem("Find", new FindListener(editor), 'f', //Find
                KeyStroke.getKeyStroke(KeyEvent.VK_F, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()))); //Ctrl+V

        add(MenuBar.createMenuItem("Replace", new ReplaceListener(editor), 'h', //Replace
                KeyStroke.getKeyStroke(KeyEvent.VK_H, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()))); //Ctrl+H
    }
}
