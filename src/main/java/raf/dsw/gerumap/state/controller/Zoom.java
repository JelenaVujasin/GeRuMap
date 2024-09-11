package raf.dsw.gerumap.state.controller;

import raf.dsw.gerumap.gui.swing.controller.AbstractGeRuMapAction;
import raf.dsw.gerumap.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class Zoom extends AbstractGeRuMapAction {
    public Zoom() {
        putValue(NAME, "Zoom");
        putValue(SMALL_ICON, loadIcon("/images/zoom.png"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getMainWindow().getStateManager().setZoomS();    }
}
