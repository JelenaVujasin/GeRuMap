package raf.dsw.gerumap.gui.swing.controller;

import lombok.SneakyThrows;
import raf.dsw.gerumap.AppCore;
import raf.dsw.gerumap.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.factory.FactoryUtils;
import raf.dsw.gerumap.mapRepository.factory.NodeFactory;
import raf.dsw.gerumap.mapRepository.implementation.Element;
import raf.dsw.gerumap.message.EventType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractGeRuMapAction{
    public NewProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/add-1-icon.png"));
        putValue(NAME, "New");
        putValue(SHORT_DESCRIPTION, "New");
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getProjectExplorer().getLastSelectedPathComponent()==null){
            AppCore.getInstance().getMessageGenerator().generate(EventType.NIJE_SELEKTOVAN_CVOR);
        }else {
            MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getProjectExplorer().getLastSelectedPathComponent();
            if (!(selected.getMapNode() instanceof Element)) {
                NodeFactory factory = FactoryUtils.getFabrika(selected.getMapNode());

                AppCore.getInstance().getGui().getMapTree().addChild(selected, factory.getNode(selected.getMapNode()));
            } else {
                AppCore.getInstance().getMessageGenerator().generate(EventType.DODATO_DETE_ELEMENTU);
            }
        }




    }



}
