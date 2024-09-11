package raf.dsw.gerumap.gui.swing.controller;

import lombok.SneakyThrows;
import raf.dsw.gerumap.AppCore;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.implementation.MindMap;
import raf.dsw.gerumap.message.EventType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SaveMapPatternAction extends AbstractGeRuMapAction{
    public SaveMapPatternAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/Patterns-icon.png"));
        putValue(NAME, "Save Map As Pattern");
        putValue(SHORT_DESCRIPTION, "Save Map As Pattern");
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof MindMap)) {
            AppCore.getInstance().getMessageGenerator().generate(EventType.NIJE_IZABRANA_MAPA);
        } else {

            MindMap mindMap = (MindMap) MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();
            mindMap.setPattern(true);
            AppCore.getInstance().getSerializer().saveMapAsPattern(mindMap);
            AppCore.getInstance().getMessageGenerator().generate(EventType.MAPA_DODATA_KAO_PATTERN);
        }

    }
}
