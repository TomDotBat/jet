package dev.tomdotbat.jet.listeners.editorwindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseCancelListener implements ActionListener {
    public CloseCancelListener(JDialog popup) { //Constructs the window close cancelled listener
        this.popup = popup;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        popup.dispose(); //Close the popup
    }

    private final JDialog popup;
}