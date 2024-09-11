package raf.dsw.gerumap.gui.swing.tree.controller;

import raf.dsw.gerumap.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.implementation.Project;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TreeMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        MapTreeItem mapTreeItem = (MapTreeItem) MainFrame.getInstance().getProjectExplorer().getLastSelectedPathComponent();
        if((e.getClickCount()==2) && mapTreeItem.getMapNode() instanceof Project){
            MainFrame.getInstance().getMainWindow().setProject((Project) mapTreeItem.getMapNode());
            //MainFrame.getInstance().getProjectExplorer().expandPath(MainFrame.getInstance().getProjectExplorer().getSelectionPath());
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
