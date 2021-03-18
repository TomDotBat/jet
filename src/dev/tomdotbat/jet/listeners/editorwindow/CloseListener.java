package dev.tomdotbat.jet.listeners.editorwindow;

import dev.tomdotbat.jet.windows.EditorWindow;
import dev.tomdotbat.jet.windows.SaveWindow;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CloseListener implements WindowListener {
    public CloseListener(EditorWindow window) { //Constructs the window closed listener
        this.window = window;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        String curText = window.getText();
        String latestSavedText = window.getDocument().getBody();

        if (curText.equals(latestSavedText)) window.dispose();
        else new SaveWindow(window);
    }

    @Override
    public void windowOpened(WindowEvent e) {} //Unused method from interface

    @Override
    public void windowClosed(WindowEvent e) {} //Unused method from interface

    @Override
    public void windowIconified(WindowEvent e) {} //Unused method from interface

    @Override
    public void windowDeiconified(WindowEvent e) {} //Unused method from interface

    @Override
    public void windowActivated(WindowEvent e) {} //Unused method from interface

    @Override
    public void windowDeactivated(WindowEvent e) {} //Unused method from interface

    private final EditorWindow window;
}
