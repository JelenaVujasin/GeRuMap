package raf.dsw.gerumap.mapRepository.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.mapRepository.composite.MapNode;
import java.awt.*;
import java.io.IOException;

@Getter
@Setter
public class Element extends MapNode {

    String color;
    int thickness;
    boolean selected;
    public Element(String name, MapNode parent) {
        super(name, parent);
    }

    public Element(String name, MapNode parent, String color, int thickness) {
        super(name, parent);
        this.color = color;
        this.thickness = thickness;
        selected = false;
    }

    public Element(String name, String color, int thickness, boolean selected) {
        super(name);
        this.color = color;
        this.thickness = thickness;
        this.selected = selected;
    }

    public void setSelected(boolean selected) throws IOException {
        this.selected = selected;
        if(selected){
            notifySubscriber(this,"Selektovan");
        }else{
            System.out.println(this.color);
            notifySubscriber(this,"Odselektovan");
        }
    }
}
