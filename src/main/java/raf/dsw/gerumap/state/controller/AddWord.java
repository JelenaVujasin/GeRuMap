package raf.dsw.gerumap.state.controller;

import raf.dsw.gerumap.gui.swing.controller.AbstractGeRuMapAction;
import raf.dsw.gerumap.gui.swing.view.MainFrame;

import java.awt.*;
import java.awt.event.ActionEvent;

public class AddWord extends AbstractGeRuMapAction {
    int cnt;
    Color color;
    String thickness;
    public AddWord() {
        putValue(SHORT_DESCRIPTION, "Add Word");
        putValue(SMALL_ICON, loadIcon("/images/rectangle.png"));
        cnt = 0;
        color = Color.BLACK;
        thickness = "3";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if(cnt == 0){
//            if((JOptionPane.showConfirmDialog(MainFrame.getInstance(),"Da li zelite da izaberete boju pojma i debljinu linije","Boja",JOptionPane.YES_NO_OPTION))==0) {
//                color = JColorChooser.showDialog(MainFrame.getInstance(), "Izaberite boju pojma", Color.BLACK);
//                thickness = JOptionPane.showInputDialog(MainFrame.getInstance(), "Izaberite debljinu linije");
//
//            }
//        }
//        cnt++;
//        MainFrame.getInstance().getMainWindow().getStateManager().getAddWordS().setThickness(thickness);
//        MainFrame.getInstance().getMainWindow().getStateManager().getAddWordS().setColor(color);
        MainFrame.getInstance().getMainWindow().startAddWordS();
    }
}
