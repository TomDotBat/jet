package dev.tomdotbat.jet.windows.editor.menubar;

import dev.tomdotbat.jet.listeners.editorwindow.menubar.viewmenu.zoommenu.ResetZoomListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.viewmenu.zoommenu.ZoomInListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.viewmenu.zoommenu.ZoomOutListener;
import dev.tomdotbat.jet.windows.EditorWindow;
import dev.tomdotbat.jet.windows.editor.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ZoomMenu extends JMenu {
    public ZoomMenu(EditorWindow editor) { //Zoom menu constructor
        super("Zoom"); //Create a JMenu with the label "Zoom"

        setMnemonic('z'); //Set the shortcut key for the menu

        add(MenuBar.createMenuItem("Zoom In", new ZoomInListener(editor), 'i', //Zoom In
                KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()))); //Ctrl+Plus

        add(MenuBar.createMenuItem("Zoom Out", new ZoomOutListener(editor), 'o', //Zoom Out
                KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()))); //Ctrl+Minus

        add(MenuBar.createMenuItem("Reset", new ResetZoomListener(editor), 'r')); //Zoom Out
    }
}
