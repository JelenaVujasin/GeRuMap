package raf.dsw.gerumap.core;

import raf.dsw.gerumap.gui.swing.tree.MapTree;
import raf.dsw.gerumap.observer.Subscriber;

public interface Gui extends Subscriber {
    MapTree getMapTree();
    void start();
}
