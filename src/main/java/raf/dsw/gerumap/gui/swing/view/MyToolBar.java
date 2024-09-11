package raf.dsw.gerumap.gui.swing.view;


import com.sun.tools.javac.Main;

import javax.swing.*;

import java.awt.*;

public class MyToolBar extends JToolBar {
    public MyToolBar(){
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        add(MainFrame.getInstance().getActionManager().getInfoAction());
        add(MainFrame.getInstance().getActionManager().getDeleteAction());
        add(MainFrame.getInstance().getActionManager().getEditAuthor());
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());
        add(MainFrame.getInstance().getActionManager().getOpenAction());
        add(MainFrame.getInstance().getActionManager().getSaveAction());
        add(MainFrame.getInstance().getActionManager().getSaveMapPatternAction());
        add(MainFrame.getInstance().getActionManager().getOpenPatterns());
        add(MainFrame.getInstance().getActionManager().getExportMapAction());
        setBackground(Color.lightGray);

    }
}
