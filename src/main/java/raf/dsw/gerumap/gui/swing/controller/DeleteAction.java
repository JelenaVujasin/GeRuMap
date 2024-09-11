package raf.dsw.gerumap.gui.swing.controller;

import lombok.SneakyThrows;
import raf.dsw.gerumap.AppCore;
import raf.dsw.gerumap.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.implementation.ProjectExplorer;
import raf.dsw.gerumap.message.EventType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends AbstractGeRuMapAction{
    public DeleteAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/Button-Delete-icon 32.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete");
    }


    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getProjectExplorer().getLastSelectedPathComponent()==null){
            AppCore.getInstance().getMessageGenerator().generate(EventType.NIJE_SELEKTOVAN_CVOR);
        }else {
            MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getProjectExplorer().getLastSelectedPathComponent();
            if (!(selected.getMapNode() instanceof ProjectExplorer)) {
                MainFrame.getInstance().getMapTree().deleteChild(selected);
            } else {
                AppCore.getInstance().getMessageGenerator().generate(EventType.OBRISAN_EXPLORER);
            }
        }




    }
}
