package raf.dsw.gerumap.state.controller;

import raf.dsw.gerumap.gui.swing.controller.AbstractGeRuMapAction;
import raf.dsw.gerumap.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class Delete extends AbstractGeRuMapAction {
    public Delete() {
        putValue(NAME, "Delete");
        putValue(SMALL_ICON, loadIcon("/images/bin.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getMainWindow().startDeleteS();}
}
