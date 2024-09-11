package raf.dsw.gerumap.state.controller;

import lombok.SneakyThrows;
import raf.dsw.gerumap.AppCore;
import raf.dsw.gerumap.gui.swing.controller.AbstractGeRuMapAction;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.view.MapView;
import raf.dsw.gerumap.mapRepository.view.WordView;
import raf.dsw.gerumap.message.EventType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ChangeWordName extends AbstractGeRuMapAction {
    public ChangeWordName() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/letter_w_icon_151238.png"));
        putValue(NAME, "Change word name");
        putValue(SHORT_DESCRIPTION, "Change word name");
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;
        for (MapView mapView : MainFrame.getInstance().getMainWindow().getMaps()) {
            if (!mapView.getSelected().isEmpty()) {
                flag = false;
                String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Unesite novo ime pojma");
                if (name == null || name.equals("") ){
                    AppCore.getInstance().getMessageGenerator().generate(EventType.PRAZNO_IME);
                }else{

                for (WordView wordView : mapView.getSelected()) {
                    wordView.getWord().setName(name);
                    mapView.getMindMap().notifySubscriber(wordView.getWord(), "Promenjeno ime");
                    }
                }
            }
        }
        if (flag){
            AppCore.getInstance().getMessageGenerator().generate(EventType.NIJE_SELEKTOVAN_POJAM);
        }
    }
}
