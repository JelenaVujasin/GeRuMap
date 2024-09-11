package raf.dsw.gerumap.gui.swing.controller;

import lombok.SneakyThrows;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.implementation.MindMap;
import raf.dsw.gerumap.mapRepository.view.MapView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractGeRuMapAction{
    public UndoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_U);
        putValue(SMALL_ICON, loadIcon("/images/undo.png"));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getMainWindow().getTabbedPane() != null) {
            MapView mapView = (MapView) MainFrame.getInstance().getMainWindow().getTabbedPane().getSelectedComponent();
            MindMap mindMap = mapView.getMindMap();
            mindMap.getCommandManager().undoCommand();
        }    }
}
