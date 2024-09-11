package raf.dsw.gerumap.mapRepository.implementation;

import raf.dsw.gerumap.core.MapRepository;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.commands.CommandManager;

import javax.swing.*;

public class MapRepositoryImplementation implements MapRepository  {
    private ProjectExplorer projectExplorer;

    public MapRepositoryImplementation() {
        projectExplorer = new ProjectExplorer("My Project Explorer",null);
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }


}
