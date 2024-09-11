package raf.dsw.gerumap.gui.swing.tree.view;

import raf.dsw.gerumap.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.mapRepository.implementation.Element;
import raf.dsw.gerumap.mapRepository.implementation.MindMap;
import raf.dsw.gerumap.mapRepository.implementation.Project;
import raf.dsw.gerumap.mapRepository.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class MapTreeCellRenderer extends DefaultTreeCellRenderer {
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row, hasFocus);
        URL imageURL = null;

        if (((MapTreeItem)value).getMapNode() instanceof ProjectExplorer) {
            imageURL = getClass().getResource("/images/computer.png");
        }
        else if (((MapTreeItem)value).getMapNode() instanceof Project) {
            imageURL = getClass().getResource("/images/folder.png");
        }
        else if (((MapTreeItem)value).getMapNode() instanceof MindMap) {
            imageURL = getClass().getResource("/images/palette.png");
        }
        else if (((MapTreeItem)value).getMapNode() instanceof Element) {
            imageURL = getClass().getResource("/images/page.png");
        }


        Icon icon = null;
        if (imageURL != null)
            icon = new ImageIcon(imageURL);
        setIcon(icon);

        return this;
    }
}
