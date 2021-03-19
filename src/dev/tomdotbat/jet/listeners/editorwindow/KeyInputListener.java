package dev.tomdotbat.jet.listeners.editorwindow;

import dev.tomdotbat.jet.windows.EditorWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputListener implements KeyListener {
    public KeyInputListener(EditorWindow window) { //Constructs the key input listener
        this.window = window;
    }

    @Override
    public void keyTyped(KeyEvent e) {} //Unused method from interface

    @Override
    public void keyPressed(KeyEvent e) {} //Unused method from interface

    @Override
    public void keyReleased(KeyEvent e) {
        window.getStatusBar().updateLineNumber(); //Update the line number on every keypress
    }

    private final EditorWindow window;
}