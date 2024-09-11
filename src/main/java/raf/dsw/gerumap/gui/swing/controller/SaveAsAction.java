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

public class SaveAsAction extends  AbstractGeRuMapAction{
    public SaveAsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/Save-as-icon.png"));
        putValue(NAME, "SaveAs");
        putValue(SHORT_DESCRIPTION, "SaveAs");
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();

        if (!(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Project)) {
            AppCore.getInstance().getMessageGenerator().generate(EventType.NIJE_IZABRAN_PROJEKAT);
        }

        Project project = (Project) MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();

        File projectFile = null;


        if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            projectFile = jfc.getSelectedFile();
            project.setFilePath(projectFile.getPath());
        } else {
            return;
        }


        AppCore.getInstance().getSerializer().saveProject(project);
    }
}
