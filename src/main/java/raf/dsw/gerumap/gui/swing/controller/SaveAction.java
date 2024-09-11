package raf.dsw.gerumap.gui.swing.controller;

import lombok.SneakyThrows;
import raf.dsw.gerumap.AppCore;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.implementation.Project;
import raf.dsw.gerumap.message.EventType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class SaveAction extends AbstractGeRuMapAction{
    public SaveAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/Actions-stock-save-as-icon.png"));
        putValue(NAME, "Save");
        putValue(SHORT_DESCRIPTION, "Save");
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();

        if (!(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Project)) {
            AppCore.getInstance().getMessageGenerator().generate(EventType.NIJE_IZABRAN_PROJEKAT);
        } else {

            Project project = (Project) MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();

            File projectFile = null;

            if ((project.getFilePath() == null || project.getFilePath().isEmpty())) {
                if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                    projectFile = jfc.getSelectedFile();
                    project.setFilePath(projectFile.getPath());
                } else {
                    return;
                }

            }
            AppCore.getInstance().getSerializer().saveProject(project);
        }
    }
}
