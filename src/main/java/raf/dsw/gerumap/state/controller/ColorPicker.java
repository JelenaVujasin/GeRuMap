package raf.dsw.gerumap.state.controller;

import lombok.SneakyThrows;
import raf.dsw.gerumap.AppCore;
import raf.dsw.gerumap.gui.swing.controller.AbstractGeRuMapAction;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.view.MapView;
import raf.dsw.gerumap.mapRepository.view.WordView;
import raf.dsw.gerumap.message.EventType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class ColorPicker extends AbstractGeRuMapAction {
    public ColorPicker() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/RGB_Circle_1-80_icon-icons.com_57282.png"));
        putValue(NAME, "PickColor ");
        putValue(SHORT_DESCRIPTION, "Pick color");
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;

        for (MapView mapView : MainFrame.getInstance().getMainWindow().getMaps()) {
            if (!mapView.getSelected().isEmpty()) {
                Color color = JColorChooser.showDialog(MainFrame.getInstance(), "Izaberite boju pojma", Color.BLACK);
                flag = false;
                if (color != null) {
                    List<WordView> toBeDeleted = new ArrayList<>();
                    for (WordView wordView : mapView.getSelected()) {
                        wordView.getWord().setColor(Integer.toString(color.getRGB()));
                        wordView.setBefore(Integer.toString(color.getRGB()));
                        wordView.getWord().setSelected(false);
                        toBeDeleted.add(wordView);
                        mapView.getMindMap().notifySubscriber(wordView.getWord(), "Promenjena boja");
                    }
                    mapView.getSelected().removeAll(toBeDeleted);
                }
            }
        }
        if (flag) {
            AppCore.getInstance().getMessageGenerator().generate(EventType.NIJE_SELEKTOVAN_POJAM);
        }
    }
}
