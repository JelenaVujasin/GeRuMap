package raf.dsw.gerumap.mapRepository.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.mapRepository.composite.MapNode;
import raf.dsw.gerumap.mapRepository.composite.MapNodeComposite;
import java.io.IOException;

@Getter
@Setter

public class Project extends MapNodeComposite {

    private String filePath;
    private String author;

    public Project(String name, MapNode parent) throws IOException {
        super(name, parent);
        setAuthor("Nepoznat");
    }

    /*public Project(String name, MapNode parent, String name1, String filePath, String author) throws IOException {
        super(name, parent);
        this.name = name1;
        this.filePath = filePath;
        this.author = author;
        setAuthor("Nepoznat");
    }*/

    public Project(String name, String filePath, String author) {
        super(name);
        this.filePath = filePath;
        this.author = author;
    }

    @Override
    public void addChild(MapNode map) throws IOException {
        if(map instanceof MindMap){
            getChildren().add(map);
            map.setName("Map" + getChildren().size());
        }
    }

    @Override
    public void removeChild(MapNode child) {
        if(child instanceof MindMap){
            this.getChildren().remove(child);

        }
    }

    public void setAuthor(String author) throws IOException {
        this.author = author;
        notifySubscriber(this,"Promenjen autor");
    }


}
