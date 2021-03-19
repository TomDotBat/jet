package dev.tomdotbat.jet.listeners.editorwindow;

import dev.tomdotbat.jet.windows.EditorWindow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputListener implements MouseListener {
    public MouseInputListener(EditorWindow window) { //Constructs the key input listener
        this.window = window;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        window.updateLineNumber(); //Update the line number on every mouse input
    }

    @Override
    public void mousePressed(MouseEvent e) {} //Unused method from interface

    @Override
    public void mouseReleased(MouseEvent e) {} //Unused method from interface

    @Override
    public void mouseEntered(MouseEvent e) {} //Unused method from interface

    @Override
    public void mouseExited(MouseEvent e) {} //Unused method from interface

    private final EditorWindow window;
}