package raf.dsw.gerumap.gui.swing.tree.view;

import raf.dsw.gerumap.gui.swing.tree.controller.MapTreeCellEditor;
import raf.dsw.gerumap.gui.swing.tree.controller.MapTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class MapTreeView extends JTree {
    public MapTreeView(DefaultTreeModel defaultTreeModel){
        setModel(defaultTreeModel);
        MapTreeCellRenderer mapTreeCellRenderer = new MapTreeCellRenderer();
        addTreeSelectionListener(new MapTreeSelectionListener());
        setCellEditor(new MapTreeCellEditor(this,mapTreeCellRenderer));
       setCellRenderer(mapTreeCellRenderer);
        setEditable(true);
    }
}
