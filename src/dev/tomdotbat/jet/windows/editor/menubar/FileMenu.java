package dev.tomdotbat.jet.windows.editor.menubar;

import dev.tomdotbat.jet.listeners.editorwindow.menubar.filemenu.*;
import dev.tomdotbat.jet.windows.EditorWindow;
import dev.tomdotbat.jet.windows.editor.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class FileMenu extends JMenu {
    public FileMenu(EditorWindow editor) { //File menu constructor
        super("File"); //Create a JMenu with the label "File"

        setMnemonic('f'); //Set the shortcut key for the menu

        add(MenuBar.createMenuItem("New", new NewFileListener(), 'n', //New file
                KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()))); //Ctrl+N

        add(MenuBar.createMenuItem("Save", new SaveFileListener(editor), 's', //Save file
                KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()))); //Ctrl+S

        add(MenuBar.createMenuItem("Save As", new SaveFileAsListener(editor), 'a', //Save file as
                KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | InputEvent.SHIFT_DOWN_MASK))); //Ctrl+Shift+S

        add(MenuBar.createMenuItem("Open", new OpenFileListener(editor), 'o', //Open file
                KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()))); //Ctrl+O

        add(new JSeparator()); //Add a spacer between the exit and file buttons

        add(MenuBar.createMenuItem("Exit", new ExitListener(editor, true), 'e',
                KeyStroke.getKeyStroke(KeyEvent.VK_E, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()))); //Ctrl+E
    }
}
