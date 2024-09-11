package raf.dsw.gerumap.gui.swing.controller;

import lombok.SneakyThrows;
import raf.dsw.gerumap.mapRepository.view.MapView;
import raf.dsw.gerumap.mapRepository.view.ProjectView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MapViewMouseListener extends MouseAdapter implements MouseWheelListener {
    MapView mapView;
    ProjectView projectView;

    public MapViewMouseListener(MapView mapView, ProjectView projectView) {
        this.mapView = mapView;
        this.projectView = projectView;
    }

    @SneakyThrows
    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == 1){
            this.projectView.mousePressed(e.getPoint(),this.mapView);
        }
    }

    @SneakyThrows
    @Override
    public void mouseDragged(MouseEvent e) {
        this.projectView.mouseDragged(e.getPoint(),this.mapView);
    }

    @Override
    @SneakyThrows
    public void mouseReleased(MouseEvent e) {
        this.projectView.mouseReleased(e.getPoint(),mapView);
    }

    @SneakyThrows
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        this.projectView.mouseWheelMoved(e.getWheelRotation(),mapView);
    }
}
