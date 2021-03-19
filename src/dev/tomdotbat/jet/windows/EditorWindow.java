package dev.tomdotbat.jet.windows;

import dev.tomdotbat.jet.documents.Document;
import dev.tomdotbat.jet.documents.HistoryManager;
import dev.tomdotbat.jet.listeners.editorwindow.CloseListener;
import dev.tomdotbat.jet.listeners.editorwindow.KeyInputListener;
import dev.tomdotbat.jet.listeners.editorwindow.MouseInputListener;
import dev.tomdotbat.jet.windows.editor.MenuBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditorWindow extends JFrame {
    public EditorWindow() { //Editor window constructor
        super("Untitled - JET");

        addWindowListener(new CloseListener(this)); //Add our window close event listener

        //Set up an untitled document
        document = new Document();

        //Set up a border layout
        setLayout(new BorderLayout(50, 0));

        //Set up the menu bar
        //setupMenuBar();
        setJMenuBar(new MenuBar(this));

        //Create the child elements of the toolbar and add it to the window
        //setupToolBar();
        //add(toolBar, BorderLayout.SOUTH);

        //Create our child elements of the status bar and add it to the bottom of the window
        setupStatusBar();
        statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.LINE_AXIS));
        add(statusBar, BorderLayout.SOUTH);

        //Create out main document text entry
        setupTextEntry();

        //Create the scroller and add it to the window
        setupScroller();
        add(scroller, BorderLayout.CENTER);

        //Attach a history manager to the editor window
        new HistoryManager(this);

        //Set the close operation to do nothing instead of terminate the program to allow different windows
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        //Size the frame to the contents' preferred size
        pack();

        //Set the window location to the center of the screen
        setLocationRelativeTo(null);

        //Set up preferences or whatever
        //String fontName = windowPreferences.getFontName();
        //int fontSize = windowPreferences.getFontSize();

        setFont(new Font("Arial", Font.PLAIN, 14));
    }

    public void updateLineNumber() { //Updates the line number and column in the status bar
        int caretPos = textEntry.getCaretPosition();

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

    public String getText() { //Getters preferences and content
        return textEntry.getText();
    }

    public Font getFont() {
        return font;
    }

    public float getZoomLevel() {
        return zoomLevel;
    }

    public Document getDocument() {
        return document;
    }

    public JTextArea getTextEntry() {
        return textEntry;
    }

    public JPanel getStatusBar() {
        return statusBar;
    }

    public JMenuItem getUndoBtn() {
        return undoBtn;
    }

    public JMenuItem getRedoBtn() {
        return redoBtn;
    }

    public void setDocument(Document document) { //Sets the document attached to the editor and fills the text entry with its text
        this.document = document;
        textEntry.setText(document.getBody());
    }

    public void setTextWrap(boolean state) { //Sets the text wrapping state
        textEntry.setLineWrap(state);
        //set preference here
    }

    public void setFont(Font font) { //Sets the font of the text entry
        setFont(font, false);
    }

    public void setFont(Font font, boolean isZoomed) { //Sets the font of the text entry
        this.font = font;
        this.textEntry.setFont(font);

        if (isZoomed) return;
        this.fontSize = font.getSize();
        //set preference here
    }

    public void setZoomLevel(float zoomLevel) { //Resize the font and update the zoom label according to the zoom amount
        this.zoomLevel = zoomLevel;
        setFont(new Font(font.getFontName(), Font.PLAIN, (int) (fontSize * zoomLevel)), true);

        zoomLevelLabel.setText(" " + Math.round(zoomLevel * 100) + "% ");
        //set preference here
    }

    public void setUndoBtn(JMenuItem undoBtn) {
        this.undoBtn = undoBtn;
    }

    public void setRedoBtn(JMenuItem redoBtn) {
        this.redoBtn = redoBtn;
    }

    private void setupStatusBar() { //Creates the child elements of the status bar and adds the appropriate listeners
        Font statusBarFont = new Font("Arial", Font.PLAIN, 12);

        lineNumberLabel = new JLabel(" Ln 1, \t ");
        lineNumberLabel.setFont(statusBarFont);
        lineNumberLabel.setBorder(new EmptyBorder(4, 0, 4, 0));

        lineColumnLabel = new JLabel("Col 1 ");
        lineColumnLabel.setFont(statusBarFont);

        zoomLevelLabel = new JLabel(" 100% ");
        zoomLevelLabel.setFont(statusBarFont);

        //Create a separator for each status label
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setMaximumSize(new Dimension(0, 20));

        JSeparator separator2 = new JSeparator(SwingConstants.VERTICAL);
        separator2.setMaximumSize(new Dimension(0, 20));

        //Add the elements the the status bar
        statusBar.add(Box.createHorizontalGlue());
        statusBar.add(separator);
        statusBar.add(lineNumberLabel);
        statusBar.add(lineColumnLabel);

        statusBar.add(Box.createRigidArea(new Dimension(30, 0)));
        statusBar.add(separator2);
        statusBar.add(zoomLevelLabel);
    }

    private void setupTextEntry() { //Creates, sets up our text entry and adds the listeners
        textEntry = new JTextArea(35, 35);
        textEntry.setLineWrap(true);
        textEntry.addKeyListener(new KeyInputListener(this));
        textEntry.addMouseListener(new MouseInputListener(this));
    }

    private void setupScroller() { //Creates the scroller to allow a larger text entry than what can fit within the window
        scroller = new JScrollPane(textEntry);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setPreferredSize(new Dimension(800, 450));
    }

    //private JToolBar toolBar;

    private JMenuItem undoBtn; //Editor UI elements
    private JMenuItem redoBtn;

    private JScrollPane scroller;
    private JTextArea textEntry;

    private final JPanel statusBar = new JPanel();
    private JLabel lineNumberLabel;
    private JLabel lineColumnLabel;
    private JLabel zoomLevelLabel;

    private Document document;

    private Font font; //Editor preferences
    private int fontSize;
    private float zoomLevel = 1;
}