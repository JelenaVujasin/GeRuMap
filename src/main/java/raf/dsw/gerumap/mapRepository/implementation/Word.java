package raf.dsw.gerumap.mapRepository.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.mapRepository.composite.MapNode;
import java.awt.*;
import java.io.IOException;

@Getter
@Setter
public class Word extends Element {
    private Point position;
    private Dimension dimension;
    public Word(String name, MapNode parent, String color, int thickness, Point position, Dimension dimension) {
        super(name, parent, color, thickness);
        this.position = position;
        this.dimension = dimension;
    }

    public Word(String name, String color, int thickness, boolean selected, Point position, Dimension dimension) {
        super(name, color, thickness, selected);
        this.position = position;
        this.dimension = dimension;
    }

    @Override
    public void setName(String name) throws IOException {
        super.setName(name);
        //notifySubscriber(this,"Promenjeno ime");
    }


    public void setPosition(Point position) throws IOException {
        this.position = position;
        notifySubscriber(this,"Promenjena pozicija");
    }
}
