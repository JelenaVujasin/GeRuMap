package raf.dsw.gerumap.core;

import raf.dsw.gerumap.mapRepository.implementation.MindMap;
import raf.dsw.gerumap.mapRepository.implementation.Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Serializer {
    Project loadProject(File file) throws IOException;
    void saveProject(Project project) throws IOException;
    void saveMapAsPattern(MindMap map) throws IOException;
    MindMap loadMapAsPattern(File file) throws FileNotFoundException;
}
