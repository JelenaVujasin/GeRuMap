package raf.dsw.gerumap.mapRepository.factory;

import raf.dsw.gerumap.mapRepository.composite.MapNode;
import raf.dsw.gerumap.mapRepository.implementation.ProjectExplorer;

public class ProjectExplorerFactory extends NodeFactory{
    @Override
    public MapNode createNode(MapNode selected) {
        return new ProjectExplorer(selected.getName(),selected);
    }
}
