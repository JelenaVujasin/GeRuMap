package raf.dsw.gerumap.state.controller;

import raf.dsw.gerumap.gui.swing.controller.AbstractGeRuMapAction;
import raf.dsw.gerumap.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class Move extends AbstractGeRuMapAction{
    public Move() {
        putValue(NAME, "Move");
        putValue(SMALL_ICON, loadIcon("/images/move.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getMainWindow().startMoveS();   }
}
