package raf.dsw.gerumap.gui.swing.tree;


import raf.dsw.gerumap.gui.swing.tree.controller.TreeMouseListener;
import raf.dsw.gerumap.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.gui.swing.tree.view.MapTreeView;
import raf.dsw.gerumap.mapRepository.composite.MapNode;
import raf.dsw.gerumap.mapRepository.composite.MapNodeComposite;
import raf.dsw.gerumap.mapRepository.implementation.MindMap;
import raf.dsw.gerumap.mapRepository.implementation.Project;
import raf.dsw.gerumap.mapRepository.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.io.IOException;


public class MapTreeImplementation implements MapTree {
    private MapTreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        MapTreeItem root = new MapTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new MapTreeView(treeModel);
        treeView.addMouseListener(new TreeMouseListener());
        return treeView;
    }



    @Override
    public void addChild(MapTreeItem parent,MapNode child) throws IOException {
        if (!(parent.getMapNode() instanceof MapNodeComposite))
            return;
        parent.add(new MapTreeItem(child));
        ((MapNodeComposite) parent.getMapNode()).addChild(child);
        parent.getMapNode().notifySubscriber(child,"Dodato dete");
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void deleteChild(MapTreeItem child) throws IOException {
        if (!(child.getMapNode() instanceof Project)) {
        MapTreeItem parent = (MapTreeItem) child.getParent();
        ((MapNodeComposite) parent.getMapNode()).removeChild(child.getMapNode());
        parent.getMapNode().notifySubscriber(child.getMapNode(),"Obrisano dete");
        parent.remove(child);
        SwingUtilities.updateComponentTreeUI(treeView);
        }
        else{
            child.removeAllChildren();
            MapTreeItem parent = (MapTreeItem) child.getParent();
            child.getMapNode().notifySubscriber(child.getMapNode(),"Obrisano dete");
            parent.remove(child);
            SwingUtilities.updateComponentTreeUI(treeView);
        }

    }
    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }



    @Override
    public void loadProject(Project project) throws IOException {
        System.out.println("Project:" + project);
        MapTreeItem loadedProject = new MapTreeItem(project);
        ((MapTreeItem)treeModel.getRoot()).add(loadedProject);
        MapNodeComposite mapNode = (MapNodeComposite) ((MapTreeItem) treeModel.getRoot()).getMapNode();
        mapNode.addChild(project);
        for(MapNode map:project.getChildren()){
            loadedProject.add(new MapTreeItem(map));
        }
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }


    public void loadMapPattern(MapTreeItem parent, MindMap map) throws IOException {
        MapTreeItem loadedMap = new MapTreeItem(map);
        parent.add(loadedMap);
        MapNodeComposite mapNodeComposite = (MapNodeComposite) parent.getMapNode();
        mapNodeComposite.addChild(map);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }
}
