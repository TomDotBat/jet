package dev.tomdotbat.jet.windows.editor;

import dev.tomdotbat.jet.windows.EditorWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatusBar extends JPanel {
    public StatusBar(EditorWindow editor) {
        this.editor = editor;

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS)); //Set the status bar to appear at the bottom of the window

        add(Box.createHorizontalGlue()); //Stick everything to the right

        createSeparator();

        lineNumberLabel = createLabel(" Ln 1, \t ");
        lineNumberLabel.setBorder(new EmptyBorder(4, 0, 4, 0)); //Force the status bar panel to increase it's height

        lineColumnLabel = createLabel("Col 1 ");

        createSeparator();

        zoomLevelLabel = createLabel(" 100% ");
    }

    public void updateLineNumber() { //Updates the line number and column in the status bar
        JTextArea textEntry = editor.getTextEntry();
        int caretPos = editor.getTextEntry().getCaretPosition();

        int lineNo = 0;
        int columnNo = 0;

        try { //Attempt to work out the line and column number form the caret position
            lineNo = textEntry.getLineOfOffset(caretPos);
            columnNo = caretPos - textEntry.getLineStartOffset(lineNo);
        }
        catch (Exception ignored) {}

        //Set the text in the line number/column status bar labels
        lineNumberLabel.setText(" Ln " + (lineNo + 1) + " \t ");
        lineColumnLabel.setText("Col: " + (columnNo + 1) + " ");
    }

    public void updateZoomLevel() {
        zoomLevelLabel.setText(" " + Math.round(editor.getZoomLevel() * 100) + "% ");
    }

    private void createSeparator() { //Creates a separator with a shorter amount of code
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setMaximumSize(new Dimension(0, 20));
        add(separator);
    }

    private JLabel createLabel(String text) { //Creates a label with a shorter amount of code
        JLabel label = new JLabel(text);
        label.setFont(font);
        add(label);
        return label;
    }

    private static Font font = new Font("Arial", Font.PLAIN, 12); //Create a font for all labels used on the status bar

    private final EditorWindow editor;
    private final JLabel lineNumberLabel;
    private final JLabel lineColumnLabel;
    private final JLabel zoomLevelLabel;
}
