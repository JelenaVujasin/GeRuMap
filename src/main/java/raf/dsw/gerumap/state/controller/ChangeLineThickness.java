package raf.dsw.gerumap.state.controller;

import lombok.SneakyThrows;
import raf.dsw.gerumap.AppCore;
import raf.dsw.gerumap.gui.swing.controller.AbstractGeRuMapAction;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.view.ConnectionView;
import raf.dsw.gerumap.mapRepository.view.MapView;
import raf.dsw.gerumap.mapRepository.view.WordView;
import raf.dsw.gerumap.message.EventType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ChangeLineThickness extends AbstractGeRuMapAction {
    public ChangeLineThickness() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/software-vector-line_98205.png"));
        putValue(NAME, "Change line thickness");
        putValue(SHORT_DESCRIPTION, "Change line thickness");
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;


        for(MapView mapView:MainFrame.getInstance().getMainWindow().getMaps()){
            for(WordView wordView: mapView.getSelected()){
                String thickness = JOptionPane.showInputDialog(MainFrame.getInstance(),"Unesite debljinu linije");
                flag = false;
                wordView.getWord().setThickness(Integer.parseInt(thickness));
                mapView.getMindMap().notifySubscriber(wordView.getWord(),"Promenjena linija");
            }
            for(ConnectionView connectionView: mapView.getSelectedConnections()){
                String thickness = JOptionPane.showInputDialog(MainFrame.getInstance(),"Unesite debljinu linije");
                flag = false;
                connectionView.getConnection().setThickness(Integer.parseInt(thickness));
                mapView.getMindMap().notifySubscriber(connectionView.getConnection(),"Promenjena linija");
            }
        }
        if (flag){
            AppCore.getInstance().getMessageGenerator().generate(EventType.NIJE_SELEKTOVAN_POJAM);
        }
    }

}
