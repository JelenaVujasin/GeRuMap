package raf.dsw.gerumap.gui.swing.controller;

import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.view.MapView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExportMapAction extends AbstractGeRuMapAction{
    public ExportMapAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/Arrow-download-3-icon.png"));
        putValue(NAME, "Export");
        putValue(SHORT_DESCRIPTION, "Export");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapView mindMapView =  MainFrame.getInstance().getMainWindow().getActiveMindMap();
        mindMapView.saveImage(mindMapView.getMindMap().getName(), "png");
    }
}
