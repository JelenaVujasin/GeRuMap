package raf.dsw.gerumap.mapRepository.factory;

import raf.dsw.gerumap.mapRepository.composite.MapNode;
import raf.dsw.gerumap.mapRepository.implementation.MindMap;
import raf.dsw.gerumap.mapRepository.implementation.Project;
import raf.dsw.gerumap.mapRepository.implementation.ProjectExplorer;

public class FactoryUtils {
    private static MapFactory mapFactory;
    private static ElementFactory elementFactory;
    private static ProjectFactory projectFactory;
    private static ProjectExplorerFactory projectExplorerFactory;



    public static void initialise() {
        mapFactory = new MapFactory();
        elementFactory = new ElementFactory();
        projectFactory = new ProjectFactory();
        projectExplorerFactory = new ProjectExplorerFactory();
    }


    public static NodeFactory getFabrika(MapNode selected) {
        if (selected instanceof MindMap)
            return elementFactory;
        else if (selected instanceof Project)
            return mapFactory;
        else if (selected instanceof ProjectExplorer)
            return projectFactory;
        else {
            return null;
        }
    }

}
