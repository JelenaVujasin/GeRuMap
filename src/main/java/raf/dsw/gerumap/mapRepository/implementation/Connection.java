package raf.dsw.gerumap.mapRepository.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.mapRepository.composite.MapNode;
import raf.dsw.gerumap.mapRepository.view.WordView;

import java.awt.*;
import java.io.IOException;

@Getter
@Setter
public class Connection extends Element{
    Point pointStart;
    Point pointEnd;
    private Word from;
    private Word to;


    public Connection(String name, MapNode parent, String color, int thickness, Point pointStart, Word from) {
        super(name, parent, color, thickness);
        this.pointStart = pointStart;
        this.pointEnd = pointStart;
        this.from = from;
    }

    public Connection(String name, String color, int thickness, boolean selected, Point pointStart, Point pointEnd, Word from, Word to) {
        super(name, color, thickness, selected);
        this.pointStart = pointStart;
        this.pointEnd = pointEnd;
        this.from = from;
        this.to = to;
    }

    public void setPointStart(Point pointStart) throws IOException {
        this.pointStart = pointStart;
        this.notifySubscriber(this,"Promenjena pozicija veze");
    }

    public void setPointEnd(Point pointEnd) throws IOException {
        this.pointEnd = pointEnd;
        this.notifySubscriber(this,"Promenjena pozicija veze");

    }


}
