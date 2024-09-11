package raf.dsw.gerumap.mapRepository.factory;

import raf.dsw.gerumap.mapRepository.composite.MapNode;

import java.io.IOException;

public abstract class NodeFactory {
    public  MapNode getNode(MapNode selected) throws IOException {
        MapNode m = createNode(selected);
        m.setName(selected.getName());
        m.setParent(selected.getParent());
        return m;
    }
    public abstract MapNode createNode(MapNode selected) throws IOException;
}
