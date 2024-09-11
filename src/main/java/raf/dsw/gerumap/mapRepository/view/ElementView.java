package raf.dsw.gerumap.mapRepository.view;

import raf.dsw.gerumap.mapRepository.implementation.Element;
import raf.dsw.gerumap.observer.Subscriber;
import javax.swing.*;
import java.awt.*;

public abstract class ElementView  extends JPanel implements Subscriber  {
    public ElementView(Element element){}
    public abstract void paint(Graphics2D g,Element element);
    public abstract boolean elementAt(Point pos);
}
