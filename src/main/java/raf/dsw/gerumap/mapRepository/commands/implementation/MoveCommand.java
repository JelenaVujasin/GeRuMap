package raf.dsw.gerumap.mapRepository.commands.implementation;

import raf.dsw.gerumap.mapRepository.commands.AbstractCommand;
import raf.dsw.gerumap.mapRepository.view.ConnectionView;
import raf.dsw.gerumap.mapRepository.view.MapView;
import raf.dsw.gerumap.mapRepository.view.WordView;

import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.List;

public class MoveCommand extends AbstractCommand {
    MapView mapView;
    List<WordView> selectedWordViews;
    int totalX;
    int totalY;

    public MoveCommand(MapView mapView, List<WordView> selectedWordViews, int totalX, int totalY) {
        this.mapView = mapView;
        this.selectedWordViews = selectedWordViews;
        this.totalX = totalX;
        this.totalY = totalY;
    }

    @Override
    public void undoCommand() throws IOException {
        for(WordView wordView : mapView.getWordViews()){
            if(selected(wordView,selectedWordViews)){
                wordView.setX(wordView.getX() - totalX);
                wordView.setY(wordView.getY() - totalY);
                wordView.getWord().getPosition().x = wordView.getX();
                wordView.getWord().getPosition().y = wordView.getY();
                float dx = wordView.getX();
                float dy = wordView.getY();
                wordView.setShape(new RoundRectangle2D.Float(dx, dy, wordView.getWidth(), wordView.getHeight(), 150, 100));
                for(ConnectionView connectionView:mapView.getConnectionViews()){
                    if(connectionView.getConnection().getFrom().getName().equals(wordView.getWord().getName())){
                        connectionView.getConnection().getPointStart().x = (connectionView.getConnection().getPointStart().x-totalX);
                        connectionView.getConnection().getPointStart().y = (connectionView.getConnection().getPointStart().y-totalY);
                    }if(connectionView.getConnection().getTo().getName().equals(wordView.getWord().getName())){
                        connectionView.getConnection().getPointEnd().x = (connectionView.getConnection().getPointEnd().x-totalX);
                        connectionView.getConnection().getPointEnd().y = (connectionView.getConnection().getPointEnd().y-totalY);
                    }
                }
            }
        }
        mapView.repaint();
    }

    @Override
    public void redoCommand() throws IOException {
        for(WordView wordView : mapView.getWordViews()){
            if(selected(wordView,selectedWordViews)){
                wordView.setX(wordView.getX() + totalX);
                wordView.setY(wordView.getY() + totalY);
                wordView.getWord().getPosition().x = wordView.getX();
                wordView.getWord().getPosition().y = wordView.getY();
                float dx = wordView.getX();
                float dy = wordView.getY();
                wordView.setShape(new RoundRectangle2D.Float(dx, dy, wordView.getWidth(), wordView.getHeight(), 150, 100));
                for(ConnectionView connectionView:mapView.getConnectionViews()){
                    if(connectionView.getConnection().getFrom().getName().equals(wordView.getWord().getName())){
                        connectionView.getConnection().getPointStart().x = (connectionView.getConnection().getPointStart().x+totalX);
                        connectionView.getConnection().getPointStart().y = (connectionView.getConnection().getPointStart().y+totalY);
                    }if(connectionView.getConnection().getTo().getName().equals(wordView.getWord().getName())){
                        connectionView.getConnection().getPointEnd().x = (connectionView.getConnection().getPointEnd().x+totalX);
                        connectionView.getConnection().getPointEnd().y = (connectionView.getConnection().getPointEnd().y+totalY);
                    }
                }
            }
        }
        mapView.repaint();
    }
    public boolean selected(WordView wordView, List<WordView> selectedWordViews){
        for(WordView wordView1 : selectedWordViews)
            if(wordView1.getWord().getName().equals(wordView.getWord().getName()))
                return true;

        return false;
    }
}
