package raf.dsw.gerumap.mapRepository.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.commands.CommandManager;
import raf.dsw.gerumap.mapRepository.composite.MapNode;
import raf.dsw.gerumap.mapRepository.composite.MapNodeComposite;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class MindMap extends MapNodeComposite {

    List<MindMap> patterns =  new ArrayList<>();
    List<Word> words = new ArrayList<>();
    List<Connection> connections = new ArrayList<>();
    transient CommandManager commandManager;
    private boolean pattern;
    public MindMap(String name, MapNode parent) {
        super(name, parent);
        commandManager = new CommandManager();
        this.pattern = false;
    }

    public MindMap(String name) {
        super(name);
        this.type = "map";
        commandManager = new CommandManager();
    }

    @Override
    public void addChild(MapNode element) throws IOException {

        if(element instanceof Element){
            getChildren().add(element);
            element.setName("Element " + getChildren().size());
        }
    }
    public void addWord(Element element) throws IOException {
        if(element instanceof Word) {
            words.add((Word) element);
            notifySubscriber(element,"Dodat pojam");
        }
    }

    public void addPattern(MindMap mindMap) throws IOException {
        if(pattern){
            patterns.add(mindMap);
            mindMap.setName("Pattern " + patterns.size());
        }
    }

    @Override
    public void removeChild(MapNode child) {
        if(child instanceof Element){
            getChildren().remove(child);
        }
    }


    public void disableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
    }


    public void disableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
    }


    public void enableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
    }

    public void enableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);
    }
}
