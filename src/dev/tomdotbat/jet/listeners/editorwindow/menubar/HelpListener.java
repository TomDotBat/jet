package dev.tomdotbat.jet.listeners.editorwindow.menubar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Click the text entry to focus and start typing. " +
                "Use the menu bar along the top to save the file, change your document formatting and more.");
    }
}
