package dev.tomdotbat.jet.windows.editor;

import dev.tomdotbat.jet.windows.EditorWindow;
import dev.tomdotbat.jet.windows.editor.menubar.*;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    public MenuBar(EditorWindow editor) { //Status bar constructor
        JMenu[] menus = new JMenu[]{ //Add our menus to the menu bar
            new FileMenu(editor),
            new EditMenu(editor),
            new FormatMenu(editor),
            new ViewMenu(editor),
            new HelpMenu(editor)
        };

        for (int i = 0; i < menus.length; i++) add(menus[i]); //Add each menu to the menu bar
    }

    public static JMenuItem createMenuItem(String name, char mnemonic) { //Menu item creation helper
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.setMnemonic(mnemonic);
        return menuItem;
    }
    public static JMenuItem createMenuItem(String name, ActionListener listener, char mnemonic) { //Menu item creation helper
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.setMnemonic(mnemonic);
        menuItem.addActionListener(listener);
        return menuItem;
    }

    public static JMenuItem createMenuItem(String name, char mnemonic, KeyStroke shortcut) { //Menu item creation helper
        JMenuItem menuItem = createMenuItem(name, mnemonic);
        menuItem.setAccelerator(shortcut);
        return menuItem;
    }

    public static JMenuItem createMenuItem(String name, ActionListener listener, char mnemonic, KeyStroke shortcut) { //Menu item creation helper
        JMenuItem menuItem = createMenuItem(name, mnemonic, shortcut);
        menuItem.addActionListener(listener);
        return menuItem;
    }
}
