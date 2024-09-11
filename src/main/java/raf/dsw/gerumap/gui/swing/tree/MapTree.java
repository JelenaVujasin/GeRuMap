package raf.dsw.gerumap.gui.swing.tree;

import raf.dsw.gerumap.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.gui.swing.tree.view.MapTreeView;
import raf.dsw.gerumap.mapRepository.composite.MapNode;
import raf.dsw.gerumap.mapRepository.implementation.MindMap;
import raf.dsw.gerumap.mapRepository.implementation.Project;
import raf.dsw.gerumap.mapRepository.implementation.ProjectExplorer;

import java.io.IOException;

public interface MapTree {
    MapTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(MapTreeItem parent, MapNode child) throws IOException;
    void deleteChild(MapTreeItem parent) throws IOException;
    MapTreeItem getSelectedNode();


    void loadProject(Project project) throws IOException;
    void loadMapPattern(MapTreeItem parent, MindMap map) throws IOException;

}
