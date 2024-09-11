package raf.dsw.gerumap.core;

import raf.dsw.gerumap.mapRepository.commands.CommandManager;
import raf.dsw.gerumap.mapRepository.implementation.ProjectExplorer;

public interface MapRepository {

    ProjectExplorer getProjectExplorer();
}
