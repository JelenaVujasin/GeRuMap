package raf.dsw.gerumap.mapRepository.factory;

import raf.dsw.gerumap.mapRepository.composite.MapNode;
import raf.dsw.gerumap.mapRepository.implementation.Project;

import java.io.IOException;

public class ProjectFactory extends NodeFactory {
    @Override
    public MapNode createNode(MapNode selected) throws IOException {
        return new Project("Projekat",selected);
    }
}
