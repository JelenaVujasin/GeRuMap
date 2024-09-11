package raf.dsw.gerumap.state.controller;

import raf.dsw.gerumap.gui.swing.controller.AbstractGeRuMapAction;
import raf.dsw.gerumap.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class CreateConnection extends AbstractGeRuMapAction {
    int cnt;
    String thickness;
    public CreateConnection() {
        putValue(NAME, "New Connection");
        putValue(SMALL_ICON, loadIcon("/images/line.png"));
        cnt = 0;
        thickness = "2";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if(cnt == 0){
//            if((JOptionPane.showConfirmDialog(MainFrame.getInstance(),"Da li zelite da izaberete debljinu linije","Linija",JOptionPane.YES_NO_OPTION))==0){
//                thickness = JOptionPane.showInputDialog(MainFrame.getInstance(), "Izaberite debljinu linije");
//            }
//        }
//        cnt++;
//        MainFrame.getInstance().getMainWindow().getStateManager().getCreateConnectionS().setThickness(thickness);
        MainFrame.getInstance().getMainWindow().startCreateConnectionS();
    }
}
