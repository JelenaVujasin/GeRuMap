package raf.dsw.gerumap.mapRepository.commands.implementation;

import raf.dsw.gerumap.mapRepository.commands.AbstractCommand;
import raf.dsw.gerumap.mapRepository.view.ConnectionView;
import raf.dsw.gerumap.mapRepository.view.MapView;
import raf.dsw.gerumap.mapRepository.view.WordView;
import raf.dsw.gerumap.state.concrete.DeleteS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends AbstractCommand {
    MapView mapView;
    List<WordView> toBeDeletedWords;
    List<ConnectionView> toBeDeletedConnecions;

    public DeleteCommand(MapView mapView, List<WordView> toBeDeletedWords, List<ConnectionView> toBeDeletedConnecions) {
        this.mapView = mapView;
        this.toBeDeletedWords = toBeDeletedWords;
        this.toBeDeletedConnecions = toBeDeletedConnecions;
    }

    @Override
    public void undoCommand() throws IOException {
        for(WordView wordView : toBeDeletedWords){
            mapView.getMindMap().getWords().add(wordView.getWord());
            mapView.getWordViews().add(wordView);
        }
        for(ConnectionView connectionView : toBeDeletedConnecions){
            mapView.getMindMap().getConnections().add(connectionView.getConnection());
            mapView.getConnectionViews().add(connectionView);
            mapView.getMindMap().addSubscriber(connectionView);
        }
        mapView.repaint();
    }

    @Override
    public void redoCommand() throws IOException {
        for(WordView wordView : toBeDeletedWords){
            mapView.getMindMap().getWords().remove(wordView.getWord());
            mapView.getWordViews().remove(wordView);
        }
        for(ConnectionView connectionView : toBeDeletedConnecions){
            mapView.getMindMap().getConnections().remove(connectionView.getConnection());
            mapView.getConnectionViews().remove(connectionView);
            mapView.getMindMap().getSubscribers().remove(connectionView);
            mapView.getMindMap().notifySubscriber(connectionView.getConnection(),"Obrisana veza");
        }
        List<ConnectionView> toBeDeletedTemp = new ArrayList<>();

        for(WordView wordView:mapView.getWordViews()) {
            for (ConnectionView connectionView : mapView.getConnectionViews()) {
                if (connectionView.getConnection().getFrom().getName().equals(wordView.getWord().getName()) || connectionView.getConnection().getTo().getName().equals(wordView.getWord().getName())) {
                    toBeDeletedTemp.add(connectionView);
                    toBeDeletedConnecions.add(connectionView);
                }
            }
        }
        for(ConnectionView connectionView : toBeDeletedTemp){
            mapView.getMindMap().getConnections().remove(connectionView.getConnection());
            mapView.getConnectionViews().remove(connectionView);
            mapView.getMindMap().getSubscribers().remove(connectionView);
            mapView.getMindMap().notifySubscriber(connectionView.getConnection(),"Obrisana veza");
        }

        mapView.getMindMap().notifySubscriber(mapView,"Obrisani pojmovi");
        for(WordView wordView : toBeDeletedWords){
            if(wordView.getWord().isSelected()){
                wordView.getWord().setSelected(false);
                mapView.getMindMap().notifySubscriber(wordView,"Odselektovan");
            }
        }
        for(ConnectionView connectionView : toBeDeletedConnecions) {
            if (connectionView.getConnection().isSelected()) {
                connectionView.getConnection().setSelected(false);
                mapView.getMindMap().notifySubscriber(connectionView, "Odselektovana veza");
            }
        }
    }
}
