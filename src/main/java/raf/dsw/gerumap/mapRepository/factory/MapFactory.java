package raf.dsw.gerumap.mapRepository.factory;

import raf.dsw.gerumap.mapRepository.composite.MapNode;
import raf.dsw.gerumap.mapRepository.implementation.MindMap;

public class MapFactory extends NodeFactory{
    @Override
    public MapNode createNode(MapNode selected) {
        return new MindMap(selected.getName(),selected);
    }
}
