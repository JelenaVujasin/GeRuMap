package raf.dsw.gerumap.gui.swing.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar() {
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        JMenu info = new JMenu("Info");
        //Mnemonici
        info.setMnemonic(KeyEvent.VK_I);
        file.setMnemonic(KeyEvent.VK_F);
        help.setMnemonic(KeyEvent.VK_H);
        //Dodavanje u menije
        file.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        file.add(MainFrame.getInstance().getActionManager().getDeleteAction());
        help.add(MainFrame.getInstance().getActionManager().getEditAuthor());
        info.add(MainFrame.getInstance().getActionManager().getInfoAction());
        //Dodavanje na meni bar
        this.add(file);
        this.add(help);
        this.add(info);
    }
}
