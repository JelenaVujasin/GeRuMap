package raf.dsw.gerumap.mapRepository.composite;


import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public abstract class MapNodeComposite extends MapNode {

    List<MapNode> children;

    public MapNodeComposite(String name, MapNode parent) {
        super(name, parent);
        children = new ArrayList<>();
    }

    public MapNodeComposite(String name) {
        super(name);
    }

    public void addChild(MapNode child) throws IOException {
        if (children.contains(child)) {
            return;
        }
        children.add(child);
        notifySubscriber(child,"Dodato dete");
    }
    public  void removeChild(MapNode child) throws IOException {
        children.remove(child);
        notifySubscriber(child,"Obrisano dete");

    }


}
