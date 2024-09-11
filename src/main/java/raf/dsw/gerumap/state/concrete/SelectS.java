package raf.dsw.gerumap.state.concrete;

import raf.dsw.gerumap.mapRepository.implementation.Word;
import raf.dsw.gerumap.mapRepository.view.ConnectionView;
import raf.dsw.gerumap.mapRepository.view.MapView;
import raf.dsw.gerumap.mapRepository.view.WordView;
import raf.dsw.gerumap.state.State;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

public class SelectS implements State {
    Point firstPoint;
    Shape rec;
    @Override
    public void misPovucen(Point position, MapView mapView) throws IOException {
        int x = firstPoint.x;
        int y = firstPoint.y;
        int width = position.x - firstPoint.x;
        int height = position.y - firstPoint.y;
        if(width < 0 && height < 0){
            rec = new Rectangle(firstPoint.x+width,firstPoint.y+height,Math.abs(width),Math.abs(height));
            mapView.getMindMap().notifySubscriber(this.rec,"Laso select");
        }else
        if(width<0){
            rec = new Rectangle(firstPoint.x+width,y,Math.abs(width),height);
            mapView.getMindMap().notifySubscriber(this.rec,"Laso select");

        }else
        if(height<0){
            rec = new Rectangle(x,firstPoint.y+height,width,Math.abs(height));
            mapView.getMindMap().notifySubscriber(this.rec,"Laso select");

        }else {
            rec = new Rectangle(x, y, width, height);
            mapView.getMindMap().notifySubscriber(this.rec,"Laso select");
        }
        for(WordView wordView : mapView.getWordViews()){
            if(!rec.intersects(new Rectangle(wordView.getX(),wordView.getY(),wordView.getWidth(),wordView.getHeight())) && wordView.getWord().isSelected()){
                System.out.println("Usao u odselect");

                mapView.getSelected().remove(wordView);
                wordView.getWord().setSelected(false);
                //mapView.update(this.rec,"Laso select");
                mapView.getMindMap().notifySubscriber(this.rec,"Laso select");
                for(ConnectionView connectionView : mapView.getConnectionViews()){
                    if((connectionView.getConnection().getFrom().equals(wordView.getWord()) || connectionView.getConnection().getTo().equals(wordView.getWord())) && connectionView.getConnection().isSelected()){
                        mapView.getSelectedConnections().remove(connectionView);
                        connectionView.getConnection().setSelected(false);
                    }
                }
                continue;
            }
            if(rec.intersects(new Rectangle(wordView.getX(),wordView.getY(),wordView.getWidth(),wordView.getHeight())) && !wordView.getWord().isSelected()){
                mapView.getSelected().add(wordView);
                wordView.getWord().setSelected(true);

                for(ConnectionView connectionView : mapView.getConnectionViews()){
                    if((connectionView.getConnection().getFrom().equals(wordView.getWord()) || connectionView.getConnection().getTo().equals(wordView.getWord())) && !connectionView.getConnection().isSelected()){
                        mapView.getSelectedConnections().add(connectionView);
                        connectionView.getConnection().setSelected(true);
                    }
                }
                //mapView.update(this.rec,"Laso select");
                mapView.getMindMap().notifySubscriber(this.rec,"Laso select");
            }
        }
        for(ConnectionView connectionView : mapView.getConnectionViews()){
            Word from = connectionView.getConnection().getFrom();
            Word to =connectionView.getConnection().getTo();
            Rectangle fromRect = new Rectangle(from.getPosition().x, from.getPosition().y,from.getDimension().width,from.getDimension().height);
            Rectangle toRect = new Rectangle(to.getPosition().x, to.getPosition().y,to.getDimension().width,to.getDimension().height);
            if(connectionView.getShape().intersects((Rectangle2D) rec) && !connectionView.getConnection().isSelected()
                    && !rec.intersects(fromRect) && !rec.intersects(toRect)){
                mapView.getSelectedConnections().add(connectionView);
                connectionView.getConnection().setSelected(true);
                mapView.update(null,"");
                continue;
            }
            if(!connectionView.getShape().intersects((Rectangle2D) rec) && connectionView.getConnection().isSelected()
                    && !rec.intersects(fromRect) && !rec.intersects(toRect)){
                mapView.getSelectedConnections().remove(connectionView);
                connectionView.getConnection().setSelected(false);
                mapView.update(null,"");
            }
        }
    }

    @Override
    public void misOtpusten(Point position, MapView mapView) throws IOException {
        //mapView.update(rec,"Laso gotov");
        mapView.getMindMap().notifySubscriber(rec,"Laso gotov");
    }

    @Override
    public void misSkrolovan(int wheel, MapView mapView) {

    }


    @Override
    public void misKliknut(Point position, MapView mapView) throws IOException {
        firstPoint = new Point(position.x, position.y);
        rec = new Rectangle(firstPoint.x,firstPoint.y,0,0);
        for (WordView wordView : mapView.getWordViews()) {
            if (wordView.elementAt(position) && !wordView.getWord().isSelected()) {
                wordView.getWord().setSelected(true);
                mapView.getMindMap().notifySubscriber(wordView,"Selektovan");
            }else if(wordView.elementAt(position) && wordView.getWord().isSelected()){
                wordView.getWord().setSelected(false);
                mapView.getMindMap().notifySubscriber(wordView,"Odselektovan");
            }
        }
        for(ConnectionView connectionView: mapView.getConnectionViews()){
            if(connectionView.elementAt(position) && !connectionView.getConnection().isSelected() && mapView.connectionAt(position)){
                connectionView.getConnection().setSelected(true);
                mapView.getMindMap().notifySubscriber(connectionView,"Selektovana veza");
            }else if(connectionView.elementAt(position) && connectionView.getConnection().isSelected() && mapView.connectionAt(position)){
                connectionView.getConnection().setSelected(false);
                mapView.getMindMap().notifySubscriber(connectionView,"Odselektovana veza");
            }
        }
    }


}
