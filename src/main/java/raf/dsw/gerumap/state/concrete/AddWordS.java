package raf.dsw.gerumap.state.concrete;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.AppCore;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.commands.AbstractCommand;
import raf.dsw.gerumap.mapRepository.commands.implementation.AddWordCommand;
import raf.dsw.gerumap.mapRepository.implementation.MindMap;
import raf.dsw.gerumap.mapRepository.implementation.Word;
import raf.dsw.gerumap.mapRepository.view.MapView;
import raf.dsw.gerumap.mapRepository.view.WordView;
import raf.dsw.gerumap.message.EventType;
import raf.dsw.gerumap.state.State;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

@Getter
@Setter
public class AddWordS implements State {
    private String thickness =  "5";
    @Override
    public void misKliknut(Point position, MapView mapView) throws IOException {
        String name = JOptionPane.showInputDialog(MainFrame.getInstance(),"Unesite zeljeni pojam.");

        if(name!=null && !name.equals("")) {
            Word word = new Word(name, mapView.getMindMap().getParent(), "-16777216", Integer.parseInt(thickness), position, new Dimension(150, 50));
            if (!mapView.overLap(word)) {
                WordView wordView = new WordView(word);
                //mapView.getMindMap().addWord(word);
                AbstractCommand command = new AddWordCommand(mapView,word,wordView);
                MapView currMV = (MapView) MainFrame.getInstance().getMainWindow().getTabbedPane().getSelectedComponent();
                currMV.getMindMap().getCommandManager().addCommand(command);
            } else {
                AppCore.getInstance().getMessageGenerator().generate(EventType.PREKLAPANJE_POJMA);
            }
        }else{
            AppCore.getInstance().getMessageGenerator().generate(EventType.PRAZNO_IME);
        }

    }

    @Override
    public void misPovucen(Point position, MapView mapView) {

    }

    @Override
    public void misOtpusten(Point position, MapView mapView) {

    }

    @Override
    public void misSkrolovan(int wheel, MapView mapView) {

    }

}
