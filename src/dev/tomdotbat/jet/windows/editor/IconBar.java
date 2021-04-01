package dev.tomdotbat.jet.windows.editor;

import dev.tomdotbat.jet.listeners.editorwindow.menubar.editmenu.FindListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.filemenu.ExitListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.filemenu.OpenFileListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.filemenu.SaveFileListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.viewmenu.zoommenu.ZoomInListener;
import dev.tomdotbat.jet.listeners.editorwindow.menubar.viewmenu.zoommenu.ZoomOutListener;
import dev.tomdotbat.jet.windows.EditorWindow;

import javax.swing.*;
import java.awt.*;

public class IconBar extends JToolBar {
    public IconBar(EditorWindow editorWindow) { //Constructs an icon bar element
        super();

        setFloatable(false);
        setPreferredSize(new Dimension(1000, 38));

        //Create our buttons, assign them icons and add them to the bar
        JButton openBtn = new JButton(new ImageIcon("src/dev/tomdotbat/jet/resources/open.png"));
        openBtn.addActionListener(new OpenFileListener(editorWindow));
        openBtn.setPreferredSize(new Dimension(34, 34));
        add(openBtn);

        JButton saveBtn = new JButton(new ImageIcon("src/dev/tomdotbat/jet/resources/save.png"));
        saveBtn.addActionListener(new SaveFileListener(editorWindow));
        saveBtn.setPreferredSize(new Dimension(34, 34));
        add(saveBtn);

        JSeparator separator1 = new JSeparator(SwingConstants.VERTICAL);
        separator1.setMaximumSize(new Dimension(400, 0));
        add(separator1);

        JButton findBtn = new JButton(new ImageIcon("src/dev/tomdotbat/jet/resources/find.png"));
        findBtn.addActionListener(new FindListener(editorWindow));
        findBtn.setPreferredSize(new Dimension(34, 34));
        add(findBtn);

        JSeparator separator2 = new JSeparator(SwingConstants.VERTICAL);
        separator2.setMaximumSize(new Dimension(400, 0));
        add(separator2);

        JButton zoomInBtn = new JButton(new ImageIcon("src/dev/tomdotbat/jet/resources/zoom-in.png"));
        zoomInBtn.addActionListener(new ZoomInListener(editorWindow));
        zoomInBtn.setPreferredSize(new Dimension(34, 34));
        add(zoomInBtn);

        JButton zoomOutBtn = new JButton(new ImageIcon("src/dev/tomdotbat/jet/resources/zoom-out.png"));
        zoomOutBtn.addActionListener(new ZoomOutListener(editorWindow));
        zoomOutBtn.setPreferredSize(new Dimension(34, 34));
        add(zoomOutBtn);

        add(Box.createHorizontalGlue()); //Separate the exit button from the others

        JButton exitBtn = new JButton(new ImageIcon("src/dev/tomdotbat/jet/resources/exit.png"));
        exitBtn.addActionListener(new ExitListener(editorWindow, true));
        exitBtn.setPreferredSize(new Dimension(34, 34));
        add(exitBtn);
    }
}
