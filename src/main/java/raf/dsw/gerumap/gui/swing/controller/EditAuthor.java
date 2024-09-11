package raf.dsw.gerumap.gui.swing.controller;

import lombok.SneakyThrows;
import raf.dsw.gerumap.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditAuthor extends AbstractGeRuMapAction{

    public EditAuthor() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/Pencil-icon.png"));
        putValue(NAME, "EditAuthor");
        putValue(SHORT_DESCRIPTION, "EditAuthor");
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getProjectExplorer().getLastSelectedPathComponent();
        if(selected.getMapNode() instanceof  Project){
            Project project = (Project) selected.getMapNode();
            String author = JOptionPane.showInputDialog(MainFrame.getInstance(),"Unesite ime autora");
            project.setAuthor(author);
        }else{
            JOptionPane.showMessageDialog(MainFrame.getInstance(),"Morate izabrati projekat");
        }


    }
}
