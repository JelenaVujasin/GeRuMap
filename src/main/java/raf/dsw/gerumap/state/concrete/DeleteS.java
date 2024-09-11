package raf.dsw.gerumap.state.concrete;

import raf.dsw.gerumap.AppCore;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.commands.AbstractCommand;
import raf.dsw.gerumap.mapRepository.commands.implementation.DeleteCommand;
import raf.dsw.gerumap.mapRepository.view.ConnectionView;
import raf.dsw.gerumap.mapRepository.view.MapView;
import raf.dsw.gerumap.mapRepository.view.WordView;
import raf.dsw.gerumap.message.EventType;
import raf.dsw.gerumap.state.State;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class DeleteS implements State {
    @Override
    public void misKliknut(Point position, MapView mapView) throws ConcurrentModificationException, IOException {
        if(mapView.getSelected().isEmpty() && mapView.getSelectedConnections().isEmpty()){
            AppCore.getInstance().getMessageGenerator().generate(EventType.NIJE_SELEKTOVAN_POJAM);
            return;
        }
        //deleteWord(position,mapView);
        //deleteConnection(position, mapView);
        AbstractCommand command = new DeleteCommand(mapView,new ArrayList<>(mapView.getSelected()),new ArrayList<>(mapView.getSelectedConnections()));
        MapView currMV = (MapView) MainFrame.getInstance().getMainWindow().getTabbedPane().getSelectedComponent();
        currMV.getMindMap().getCommandManager().addCommand(command);

    }

    @Override
    public void misPovucen(Point position, MapView mapView)  {

    }


    @Override
    public void misOtpusten(Point position, MapView mapView) {

    }

    @Override
    public void misSkrolovan(int wheel, MapView mapView) {

    }


    public void deleteWord(Point position,MapView mapView) throws IOException {
        for(WordView wordView:mapView.getSelected()){
            List<ConnectionView> toBeDeleted = new ArrayList<>();
            for(ConnectionView connectionView:mapView.getConnectionViews()){
                if(connectionView.getConnection().getFrom().getName().equals(wordView.getWord().getName()) || connectionView.getConnection().getTo().getName().equals(wordView.getWord().getName())){
                    toBeDeleted.add(connectionView);
                }
            }
            for(ConnectionView connectionView : toBeDeleted){
                mapView.getMindMap().getConnections().remove(connectionView.getConnection());
            }
            mapView.getConnectionViews().removeAll(toBeDeleted);
            mapView.getMindMap().getWords().remove(wordView.getWord());
            mapView.getWordViews().remove(wordView);
            //mapView.update(mapView,"Obrisani pojmovi");
            mapView.getMindMap().notifySubscriber(mapView,"Obrisani pojmovi");
        }
        mapView.getSelected().clear();
    }

    public void deleteConnection(Point position,MapView mapView) throws IOException {
        List<ConnectionView> toBeDeleted = new ArrayList<>();
        for(ConnectionView connectionView:mapView.getSelectedConnections()){
            //mapView.getConnectionViews().remove(connectionView);
            toBeDeleted.add(connectionView);
            mapView.getMindMap().getConnections().remove(connectionView.getConnection());
            mapView.getMindMap().notifySubscriber(connectionView.getConnection(),"Obrisana veza");

        }
        mapView.getConnectionViews().removeAll(toBeDeleted);
    }


}
