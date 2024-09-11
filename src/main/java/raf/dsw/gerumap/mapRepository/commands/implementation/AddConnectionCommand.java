package raf.dsw.gerumap.mapRepository.commands.implementation;

import raf.dsw.gerumap.mapRepository.commands.AbstractCommand;
import raf.dsw.gerumap.mapRepository.implementation.Connection;
import raf.dsw.gerumap.mapRepository.view.ConnectionView;
import raf.dsw.gerumap.mapRepository.view.MapView;

import java.io.IOException;

public class AddConnectionCommand extends AbstractCommand {
    MapView mapView;
    Connection connection;

    public AddConnectionCommand(MapView mapView, Connection connection) {
        this.mapView = mapView;
        this.connection = connection;
    }

    @Override
    public void undoCommand() throws IOException {
        Connection currConnection = mapView.getMindMap().getConnections().get(mapView.getMindMap().getConnections().size()-1);
        if(currConnection.isSelected()){
            currConnection.setSelected(false);
            mapView.getMindMap().notifySubscriber(currConnection,"Odselektovana veza");
        }
        mapView.getMindMap().getConnections().remove(mapView.getMindMap().getConnections().size()-1);
        mapView.getMindMap().notifySubscriber(connection,"Zavrsena veza");
        connection.getSubscribers().remove(connection.getSubscribers().size()-1);
    }

    @Override
    public void redoCommand() throws IOException {

        mapView.getMindMap().getConnections().add(connection);
        mapView.getMindMap().notifySubscriber(connection,"Dodata veza");
    }
}
