package raf.dsw.gerumap.mapRepository.factory;

import raf.dsw.gerumap.mapRepository.composite.MapNode;
import raf.dsw.gerumap.mapRepository.implementation.Element;
import java.awt.*;

public class ElementFactory extends NodeFactory{
    @Override
    public MapNode createNode(MapNode selected) {
        return new Element(selected.getName(),selected, "CYAN",10);
    }
}
