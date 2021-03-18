package dev.tomdotbat.jet.listeners.findwindow;

import dev.tomdotbat.jet.windows.EditorWindow;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CloseListener implements WindowListener {
    public CloseListener(EditorWindow window) { //Constructs the close listener
        this.window = window;
    }

    @Override
    public void windowClosing(WindowEvent e) { //Remove all highlights on the document when closing the find window
        window.getTextEntry().getHighlighter().removeAllHighlights();
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}

    private final EditorWindow window;
}