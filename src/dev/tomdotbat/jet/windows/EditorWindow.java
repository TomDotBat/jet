package dev.tomdotbat.jet.windows;

import dev.tomdotbat.jet.documents.Document;
import dev.tomdotbat.jet.documents.HistoryManager;
import dev.tomdotbat.jet.listeners.editorwindow.KeyInputListener;
import dev.tomdotbat.jet.listeners.editorwindow.MouseInputListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.HelpListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.editmenu.CopyListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.editmenu.FindListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.editmenu.PasteListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.editmenu.ReplaceListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.filemenu.*;
import dev.tomdotbat.jet.listeners.editorwindow.CloseListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.formatmenu.FontListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.formatmenu.TextWrapListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.viewmenu.StatusBarToggleListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.viewmenu.zoommenu.ResetZoomListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.viewmenu.zoommenu.ZoomInListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.viewmenu.zoommenu.ZoomOutListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class EditorWindow extends JFrame {
    public EditorWindow() { //Editor window constructor
        super("Untitled - JET");

        addWindowListener(new CloseListener(this)); //Add our window close event listener

        try {
            font = UIManager.getFont("TextField.font"); //Set the font size to the system's default
            UIManager.getDefaults().put("TextArea.font", font);
        }
        catch (Exception ignored) {}

        //Set up an untitled document
        document = new Document();

        //Set up a border layout
        setLayout(new BorderLayout(50, 0));

        //Set up the menu bar
        setupMenuBar();

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
        new HistoryManager(this, undoBtn, redoBtn);

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

    public void setDocument(Document document) { //Sets the document attached to the editor and fill the text entry with its text
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

    private void setupMenuBar() { //Sets up all the elements and menus within the menu bar
        JMenuBar menuBar = new JMenuBar();

        //Setup file menu items, listeners and their mnemonics
        JMenu menuFile = new JMenu("File");
        menuFile.setMnemonic('f');

        JMenuItem fileNew = new JMenuItem("New");
        fileNew.addActionListener(new NewFileListener());
        fileNew.setMnemonic('n');
        menuFile.add(fileNew);

        JMenuItem fileSave = new JMenuItem("Save");
        fileSave.addActionListener(new SaveFileListener(this));
        fileSave.setMnemonic('s');
        menuFile.add(fileSave);

        JMenuItem fileSaveAs = new JMenuItem("Save As");
        fileSaveAs.addActionListener(new SaveFileAsListener(this));
        fileSaveAs.setMnemonic('a');
        menuFile.add(fileSaveAs);

        JMenuItem fileOpen = new JMenuItem("Open");
        fileOpen.addActionListener(new OpenFileListener(this));
        fileOpen.setMnemonic('o');
        menuFile.add(fileOpen);

        menuFile.add(new JSeparator()); //Add a spacer between the exit button and the rest

        JMenuItem fileExit = new JMenuItem("Exit");
        fileExit.addActionListener(new ExitListener(this, true));
        fileExit.setMnemonic('e');
        menuFile.add(fileExit);

        //Setup file menu item shortcuts
        fileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); //N - New File
        fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); //S - Save File
        fileSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() | InputEvent.SHIFT_DOWN_MASK)); //Shift + S - Save File As
        fileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); //O - Open File
        fileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); //E - Exit

        menuBar.add(menuFile);


        //Setup edit menu items, listeners and their mnemonics
        JMenu menuEdit = new JMenu("Edit");
        menuEdit.setMnemonic('e');

        undoBtn = new JMenuItem("Undo");
        undoBtn.setMnemonic('u');
        undoBtn.setEnabled(false);
        menuEdit.add(undoBtn);

        redoBtn = new JMenuItem("Redo");
        redoBtn.setMnemonic('e');
        redoBtn.setEnabled(false);
        menuEdit.add(redoBtn);

        JMenuItem editCopy = new JMenuItem("Copy");
        editCopy.addActionListener(new CopyListener(this));
        editCopy.setMnemonic('c');
        menuEdit.add(editCopy);

        JMenuItem editPaste = new JMenuItem("Paste");
        editPaste.addActionListener(new PasteListener(this));
        editPaste.setMnemonic('o');
        menuEdit.add(editPaste);

        menuFile.add(new JSeparator()); //Add a spacer between clipboard functions and searching

        JMenuItem editFind = new JMenuItem("Find");
        editFind.addActionListener(new FindListener(this));
        editFind.setMnemonic('f');
        menuEdit.add(editFind);

        JMenuItem editReplace = new JMenuItem("Replace");
        editReplace.addActionListener(new ReplaceListener(this));
        editReplace.setMnemonic('r');
        menuEdit.add(editReplace);

        //Setup edit menu item shortcuts
        undoBtn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); //Z - Undo
        redoBtn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); //Y - Redo
        editCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); //C - Copy
        editPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); //V - Paste
        editFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); //F - Find
        editReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask())); //R - Replace

        menuBar.add(menuEdit);

        //Setup format menu items, listeners and their mnemonics
        JMenu menuFormat = new JMenu("Format");
        menuFormat.setMnemonic('t');

        JCheckBoxMenuItem textWrapCheckBox = new JCheckBoxMenuItem("Word Wrap", true);
        textWrapCheckBox.addItemListener(new TextWrapListener(this));
        textWrapCheckBox.setMnemonic('w');
        menuFormat.add(textWrapCheckBox);

        JMenuItem formatFont = new JMenuItem("Font...");
        formatFont.addActionListener(new FontListener(this));
        formatFont.setMnemonic('f');
        menuFormat.add(formatFont);

        menuBar.add(menuFormat);


        //Setup zoom menu items, listeners and their mnemonics
        JMenu menuView = new JMenu("View");
        menuView.setMnemonic('v');

        JMenu viewZoom = new JMenu("Zoom");
        viewZoom.setMnemonic('z');

        JMenuItem zoomIn = new JMenuItem("Zoom In");
        zoomIn.addActionListener(new ZoomInListener(this));
        zoomIn.setMnemonic('i');
        viewZoom.add(zoomIn);

        JMenuItem zoomOut = new JMenuItem("Zoom Out");
        zoomOut.addActionListener(new ZoomOutListener(this));
        zoomOut.setMnemonic('o');
        viewZoom.add(zoomOut);

        JMenuItem zoomReset = new JMenuItem("Reset");
        zoomReset.addActionListener(new ResetZoomListener(this));
        zoomReset.setMnemonic('r');
        viewZoom.add(zoomReset);

        menuView.add(viewZoom);

        JCheckBoxMenuItem viewStatusBarCheckBox = new JCheckBoxMenuItem("Status Bar", true);
        viewStatusBarCheckBox.addItemListener(new StatusBarToggleListener(this));
        viewStatusBarCheckBox.setMnemonic('t');
        menuView.add(viewStatusBarCheckBox);

        menuBar.add(menuView);


        //Add a help menu item
        JMenu menuHelp = new JMenu("Help");
        menuHelp.setMnemonic('h');

        JMenuItem help = new JMenuItem("Help");
        help.addActionListener(new HelpListener());
        help.setMnemonic('h');

        menuHelp.add(help);
        menuBar.add(menuHelp);

        setJMenuBar(menuBar);
    }

    //private void setupToolBar() { //Creates the child elements of the toolbar and adds the appropriate listeners
    //    toolBar = new JToolBar();
//
    //    //Add the buttons to the toolbar
    //    JButton toolbarOpenButton = new JButton("Open"); //Add the open file button
    //    toolbarOpenButton.addActionListener(new OpenFileListener(this));
    //    toolBar.add(toolbarOpenButton);
//
    //    JButton toolbarSaveButton = new JButton("Save"); //Add the save file button
    //    //toolbarSaveButton.addActionListener(new SaveFileListener(this));
    //    toolBar.add(toolbarSaveButton);
//
    //    JButton toolbarZoomInButton = new JButton("Zoom In"); //Add the zoom in button
    //    //toolbarZoomInButton.addActionListener(new ZoomInListener(this));
    //    toolBar.add(toolbarZoomInButton);
//
    //    JButton toolbarZoomOutButton = new JButton("Zoom Out"); //Add the zoom out button
    //    //toolbarZoomOutButton.addActionListener(new ZoomOutListener(this));
    //    toolBar.add(toolbarZoomOutButton);
//
    //    toolBar.add(Box.createHorizontalGlue());
//
    //    JButton toolbarExitButton = new JButton("Exit"); //Add the exit button
    //    toolbarExitButton.addActionListener(new ExitListener(this, true));
    //    toolBar.add(toolbarExitButton);
//
    //    toolBar.setMaximumSize(new Dimension(1000, 20));
    //}

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