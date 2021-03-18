package dev.tomdotbat.jet.documents;

import dev.tomdotbat.jet.windows.EditorWindow;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

/*
    This class uses parts of the code from the webpage below:
    http://www.java2s.com/Code/Java/Swing-JFC/Undoredotextarea.htm
 */

public class HistoryManager {
    public HistoryManager(EditorWindow window, JMenuItem undoBtn, JMenuItem redoBtn) { //Constructs a history manager attached to an editor window
        this.textEntry = window.getTextEntry();
        this.undoBtn = undoBtn;
        this.redoBtn = redoBtn;

        undoManager = new UndoManager(); //Create an undo manager

        //Add the undo listener to the text entry
        textEntry.getDocument().addUndoableEditListener(e -> {
            undoManager.addEdit(e.getEdit());
            updateButtons();
        });

        //Add the undo button listener
        undoBtn.addActionListener(e -> {
            try {
                undoManager.undo(); //Undo the last change if possible
            } catch (CannotRedoException ex) {
                ex.printStackTrace();
            }
            updateButtons();
        });

        //Add the redo button listener
        redoBtn.addActionListener(e -> {
            try {
                undoManager.redo(); //Redo the last undo if possible
            } catch (CannotRedoException ex) {
                ex.printStackTrace();
            }
            updateButtons();
        });
    }

    private final UndoManager undoManager;
    private JTextArea textEntry;
    private JMenuItem undoBtn;
    private JMenuItem redoBtn;

    private void updateButtons() { //Update the undo and redo buttons to feed back whether they can be used of not
        undoBtn.setEnabled(undoManager.canUndo());
        redoBtn.setEnabled(undoManager.canRedo());
    }
}