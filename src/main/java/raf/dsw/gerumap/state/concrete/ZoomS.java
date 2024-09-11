package raf.dsw.gerumap.state.concrete;

import lombok.Getter;
import raf.dsw.gerumap.mapRepository.implementation.Word;
import raf.dsw.gerumap.mapRepository.view.ConnectionView;
import raf.dsw.gerumap.mapRepository.view.MapView;
import raf.dsw.gerumap.mapRepository.view.WordView;
import raf.dsw.gerumap.state.State;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
@Getter
public class ZoomS implements State {
    private double zoomFactor = 1;
    private double prevZoomFactor = 1;
    private double xOffset = 0;
    private double yOffset = 0;
    private  int xDiff = 0;
    private int yDiff = 0;
    private Point startPoint;
    int prevDiffX = 0;

    @Override
    public void misKliknut(Point position, MapView mapView){
//        for(WordView wordView: mapView.getWordViews()){
//            System.out.println("Pozicija reci: " + wordView.getWord().getPosition());
//        }
//        System.out.println("Pozicija misa X:" + position.x);
//        System.out.println("Pozicija misa Y:" + position.y);
        startPoint = position;
    }

    @Override
    public void misPovucen(Point position, MapView mapView) throws IOException {
        xDiff = position.x - startPoint.x;
        yDiff = position.y - startPoint.y;
        AffineTransform at = new AffineTransform();
        at.translate(xOffset + xDiff,yOffset +  yDiff);
//        for (WordView wordView:mapView.getWordViews()){
//            Point pos = new Point((int) (Math.abs(wordView.getX() + (2*xDiff + xOffset))), (int) Math.abs((wordView.getY()) + (2*yDiff + yOffset)));
//            wordView.getWord().setPosition(pos);
//        }
        at.scale(zoomFactor, zoomFactor);
        mapView.getMindMap().notifySubscriber(at,"Zoom");
    }

    @Override
    public void misOtpusten(Point position, MapView mapView) throws IOException {
        xOffset += xDiff;
        yOffset += yDiff;
//        System.out.println("XDIFF: " + xDiff);
//        System.out.println("YDIFF: " + yDiff);
//        System.out.println("XOFFSET: " + xOffset);
//        System.out.println("YOFFSET: " + yOffset);
        if (xDiff != prevDiffX) {
            changePosition(mapView);
        }
        prevDiffX = xDiff;
        mapView.repaint();
        //mapView.getMindMap().notifySubscriber(mapView,"Zoom");
    }


    @Override
    public void misSkrolovan(int wheel, MapView mapView) throws IOException {
        AffineTransform at = new AffineTransform();
        double xRel = MouseInfo.getPointerInfo().getLocation().getX()-mapView.getLocationOnScreen().getX();
        double yRel = MouseInfo.getPointerInfo().getLocation().getY()-mapView.getLocationOnScreen().getY();
        double zoomDif = zoomFactor/prevZoomFactor;

        xOffset = (zoomDif)*(xOffset) + (1-zoomDif)*xRel;
        yOffset = (zoomDif)*(yOffset) + (1-zoomDif)*yRel;

        at.translate(xOffset + xDiff,yOffset + yDiff);
        at.scale(zoomFactor,zoomFactor);
        changePosition(mapView);
        prevZoomFactor = zoomFactor;
        mapView.getMindMap().notifySubscriber(at,"Zoom");
        if(wheel < 0){
            zoomFactor*=1.1;
            mapView.getMindMap().notifySubscriber(mapView,"Zoom");
        }
        if(wheel > 0){
            zoomFactor/=1.1;
            mapView.getMindMap().notifySubscriber(mapView,"Zoom");
        }
    }

    private void changePosition(MapView mapView) throws IOException {

        for (WordView wordView:mapView.getWordViews()){
            Point pos = new Point((int) (Math.abs(wordView.getX() + (2*xDiff + xOffset))), (int) Math.abs((wordView.getY()) + (2*yDiff + yOffset)));
            wordView.getWord().setPosition(pos);
        }
        for(ConnectionView connectionView:mapView.getConnectionViews()){
            connectionView.getConnection().setPointStart(new Point((int) (Math.abs(connectionView.getConnection().getPointStart().x + (2*xDiff + xOffset))), (int) Math.abs((connectionView.getConnection().getPointStart().y) + (2*yDiff + yOffset))));
            connectionView.getConnection().setPointEnd(new Point((int) (Math.abs(connectionView.getConnection().getPointEnd().x + (2*xDiff + xOffset))), (int) Math.abs((connectionView.getConnection().getPointEnd().y) + (2*yDiff + yOffset))));

        }

    }


}
