package raf.dsw.gerumap.gui.swing.controller;

import lombok.SneakyThrows;
import raf.dsw.gerumap.AppCore;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.implementation.Project;
import raf.dsw.gerumap.mapRepository.implementation.ProjectExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class OpenAction extends AbstractGeRuMapAction{
    public OpenAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/folder-red-open-icon.png"));
        putValue(NAME, "Open");
        putValue(SHORT_DESCRIPTION, "Open");
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof ProjectExplorer)) {
            //AppCore.getInstance().getMessageGenerator().generate(EventType.IZABRATI_EXPLORER);
        } else {
            JFileChooser jFileChooser = new JFileChooser();

            if (jFileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                Project project = AppCore.getInstance().getSerializer().loadProject(file);
                MainFrame.getInstance().getMapTree().loadProject(project);
            }
        }
    }
}
