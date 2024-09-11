package raf.dsw.gerumap.mapRepository.commands;

import com.sun.tools.javac.Main;
import lombok.Getter;
import raf.dsw.gerumap.AppCore;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.view.MapView;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Getter
public class CommandManager {
    private List<AbstractCommand> commands = new ArrayList<AbstractCommand>();
    private int currentCommand = 0;

    public void addCommand(AbstractCommand command) throws IOException {
        while(currentCommand < commands.size())
            commands.remove(currentCommand);
        commands.add(command);
        redoCommand();
    }
    public void redoCommand() throws IOException {
        if(currentCommand < commands.size()){
            commands.get(currentCommand++).redoCommand();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getProjectExplorer());
            //AppCore.getInstance().getMapRepository().enableUndoAction();
            MapView currMV = (MapView) MainFrame.getInstance().getMainWindow().getTabbedPane().getSelectedComponent();
            currMV.getMindMap().enableUndoAction();
        }
        if(currentCommand==commands.size()){
            MapView currMV = (MapView) MainFrame.getInstance().getMainWindow().getTabbedPane().getSelectedComponent();
            currMV.getMindMap().disableRedoAction();
        }
    }



    public void undoCommand() throws IOException {
        if(currentCommand > 0){
            MapView currMV = (MapView) MainFrame.getInstance().getMainWindow().getTabbedPane().getSelectedComponent();
            currMV.getMindMap().enableRedoAction();
            commands.get(--currentCommand).undoCommand();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getProjectExplorer());
        }
        if(currentCommand==0){
            MapView currMV = (MapView) MainFrame.getInstance().getMainWindow().getTabbedPane().getSelectedComponent();
            currMV.getMindMap().disableUndoAction();
        }
    }

}
