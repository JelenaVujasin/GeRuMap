package raf.dsw.gerumap.gui.swing.controller;

import lombok.SneakyThrows;
import raf.dsw.gerumap.AppCore;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.implementation.MindMap;
import raf.dsw.gerumap.mapRepository.implementation.Project;
import raf.dsw.gerumap.message.EventType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class OpenPatterns extends AbstractGeRuMapAction{
    public OpenPatterns() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/folder-green-open-icon.png"));
        putValue(NAME, "Open Patterns");
        putValue(SHORT_DESCRIPTION, "Open Patterns");
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Project)) {
            AppCore.getInstance().getMessageGenerator().generate(EventType.NIJE_IZABRAN_PROJEKAT);
        } else {

            JFileChooser jFileChooser = new JFileChooser(new File("C:\\Users\\Jelena Vujasin\\Desktop\\gerumap-tim_jelenavujasin_lukadavidovic\\src\\main\\resources\\patterns"));

            if (jFileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                MindMap map = AppCore.getInstance().getSerializer().loadMapAsPattern(file);
                MainFrame.getInstance().getMapTree().loadMapPattern(MainFrame.getInstance().getMapTree().getSelectedNode(),map);
            }
        }
    }
}
