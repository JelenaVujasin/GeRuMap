package raf.dsw.gerumap.gui.swing;

import raf.dsw.gerumap.core.Gui;
import raf.dsw.gerumap.gui.swing.tree.MapTree;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.message.Message;
import javax.swing.*;

public class SwingGui implements Gui {

    @Override
    public MapTree getMapTree() {
        return MainFrame.getInstance().getMapTree();
    }

    @Override
    public void start() {
        MainFrame.getInstance().setVisible(true);
    }

    @Override
    public void update(Object notification, String message) {
        if(notification instanceof Message){
            JOptionPane.showMessageDialog(MainFrame.getInstance(),((Message) notification).getMessage(),message,JOptionPane.PLAIN_MESSAGE);

        }

    }
}
