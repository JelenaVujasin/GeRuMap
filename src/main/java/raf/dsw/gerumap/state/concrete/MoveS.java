package raf.dsw.gerumap.state.concrete;

import raf.dsw.gerumap.AppCore;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.commands.AbstractCommand;
import raf.dsw.gerumap.mapRepository.commands.implementation.MoveCommand;
import raf.dsw.gerumap.mapRepository.view.ConnectionView;
import raf.dsw.gerumap.mapRepository.view.MapView;
import raf.dsw.gerumap.mapRepository.view.WordView;
import raf.dsw.gerumap.message.EventType;
import raf.dsw.gerumap.state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoveS implements State {
    int lastX;
    int lastY;

    int totalX;
    int totalY;

    List<Point> startingPointsWords = new ArrayList<>();
    List<Point> startingConnections = new ArrayList<>();
    List<Point> endingConnections = new ArrayList<>();

    @Override
    public void misKliknut(Point position, MapView mapView) throws IOException {
        getPoints(mapView,startingPointsWords,startingConnections,endingConnections);
        lastX = position.x;
        lastY = position.y;
        totalX = 0;
        totalY = 0;
    }

    @Override
    public void misPovucen(Point position, MapView mapView) throws IOException {
        int xDiff = position.x - lastX;
        int yDiff = position.y - lastY;
        totalX+=xDiff;
        totalY+=yDiff;
        for(WordView wordView : mapView.getSelected()){
            wordView.setX(wordView.getX() + xDiff);
            wordView.setY(wordView.getY() + yDiff);
            wordView.getWord().getPosition().x = wordView.getX();
            wordView.getWord().getPosition().y = wordView.getY();
            float dx = wordView.getX();
            float dy = wordView.getY();
            wordView.setShape(new RoundRectangle2D.Float(dx, dy, wordView.getWidth(), wordView.getHeight(), 150, 100));
            for(ConnectionView connectionView:mapView.getConnectionViews()){
                if(connectionView.getConnection().getFrom().equals(wordView.getWord())){
                    connectionView.getConnection().getPointStart().x = (connectionView.getConnection().getPointStart().x+xDiff);
                    connectionView.getConnection().getPointStart().y = (connectionView.getConnection().getPointStart().y+yDiff);
                }if(connectionView.getConnection().getTo().equals(wordView.getWord())){
                    connectionView.getConnection().getPointEnd().x = (connectionView.getConnection().getPointEnd().x+xDiff);
                    connectionView.getConnection().getPointEnd().y = (connectionView.getConnection().getPointEnd().y+yDiff);
                }
            }
            //mapView.update(wordView,"Pomereno");
            mapView.getMindMap().notifySubscriber(wordView,"Pomereno");
        }
        lastX = position.x;
        lastY = position.y;
    }
    @Override
    public void misOtpusten(Point position, MapView mapView) throws IOException {
        int flag = 0;
        for(WordView wordView : mapView.getWordViews()){
            Rectangle2D rectangle2D = new Rectangle2D.Double(wordView.getX(),wordView.getY(),wordView.getWidth(),wordView.getHeight());;
            for(WordView wordView1 : mapView.getWordViews()){
                if(wordView1.getShape().intersects(rectangle2D) && wordView1!=wordView){
                    flag = 1;
                    break;
                }
            }
        }
        if(flag == 1){
            AppCore.getInstance().getMessageGenerator().generate(EventType.PREKLAPANJE_POJMA);
            int br = 0;
            for (WordView wordView : mapView.getWordViews()){
                wordView.setX(startingPointsWords.get(br).x);
                wordView.setY(startingPointsWords.get(br).y);
                wordView.getWord().getPosition().x = startingPointsWords.get(br).x;
                wordView.getWord().getPosition().y = startingPointsWords.get(br).y;
                float dx = startingPointsWords.get(br).x;
                float dy = startingPointsWords.get(br).y;
                ++br;
                wordView.setShape(new RoundRectangle2D.Float(dx, dy, wordView.getWidth(), wordView.getHeight(), 150, 100));
            }
            int br2 = 0;
            for (ConnectionView connectionView : mapView.getConnectionViews()){
                connectionView.getConnection().setPointStart(startingConnections.get(br2));
                connectionView.getConnection().setPointEnd(endingConnections.get(br2));
                ++br2;
            }
            //mapView.update(null,"Preklapanje moveS");
            mapView.getMindMap().notifySubscriber(null,"Preklapanje moveS");
        }else{
            for(WordView wordView : mapView.getSelected()){
                wordView.setX(wordView.getX() - totalX);
                wordView.setY(wordView.getY() - totalY);
                wordView.getWord().getPosition().x = wordView.getX();
                wordView.getWord().getPosition().y = wordView.getY();
                float dx = wordView.getX();
                float dy = wordView.getY();
                wordView.setShape(new RoundRectangle2D.Float(dx, dy, wordView.getWidth(), wordView.getHeight(), 150, 100));

                for(ConnectionView connectionView:mapView.getConnectionViews()){
                    if(connectionView.getConnection().getFrom().equals(wordView.getWord())){
                        connectionView.getConnection().getPointStart().x = (connectionView.getConnection().getPointStart().x-totalX);
                        connectionView.getConnection().getPointStart().y = (connectionView.getConnection().getPointStart().y-totalY);
                    }if(connectionView.getConnection().getTo().equals(wordView.getWord())){
                        connectionView.getConnection().getPointEnd().x = (connectionView.getConnection().getPointEnd().x-totalX);
                        connectionView.getConnection().getPointEnd().y = (connectionView.getConnection().getPointEnd().y-totalY);
                    }
                }
            }
            AbstractCommand command = new MoveCommand(mapView,mapView.getSelected(),totalX,totalY);
            MapView currMV = (MapView) MainFrame.getInstance().getMainWindow().getTabbedPane().getSelectedComponent();
            currMV.getMindMap().getCommandManager().addCommand(command);
        }
    }
    private void getPoints(MapView mapView,List<Point> startingPointsWords,List<Point> startingConnections,List<Point> endingConnections){
        startingPointsWords.clear();
        startingConnections.clear();
        endingConnections.clear();
        for(WordView wordView : mapView.getWordViews()){
            startingPointsWords.add(new Point(wordView.getWord().getPosition()));
        }
        for(ConnectionView connectionView : mapView.getConnectionViews()){
            startingConnections.add(new Point(connectionView.getConnection().getPointStart()));
            endingConnections.add(new Point(connectionView.getConnection().getPointEnd()));
        }
    }
    @Override
    public void misSkrolovan(int wheel, MapView mapView) {

    }
}
