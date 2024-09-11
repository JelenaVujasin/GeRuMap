package raf.dsw.gerumap.mapRepository.view;

import lombok.Setter;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.composite.MapNode;
import raf.dsw.gerumap.state.controller.MapActionManager;
import raf.dsw.gerumap.mapRepository.implementation.MindMap;
import raf.dsw.gerumap.mapRepository.implementation.Project;
import raf.dsw.gerumap.observer.Subscriber;
import raf.dsw.gerumap.state.StateManager;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Setter

public class ProjectView extends JPanel implements Subscriber {
    private Project project;
    private JLabel author;
    private JTabbedPane tabbedPane;
    private List<MapView> maps;
    private JLabel name;
    private MapActionManager mapActionManager;
    private MapToolBar mapToolBar;
    private StateManager stateManager;
    private JScrollPane jScrollPane;

    public ProjectView() {
        this.name = new JLabel("Nijedan projekat nije otvoren");
        maps = new ArrayList<>();
        tabbedPane = new JTabbedPane();
        mapActionManager = new MapActionManager();
        mapToolBar = new MapToolBar(this);
        stateManager = new StateManager();
        this.author = new JLabel();
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JPanel middle = new JPanel();
        //JScrollPane scrollPane = new JScrollPane(mapToolBar);
        middle.setLayout(new BorderLayout());
        middle.add(tabbedPane,BorderLayout.CENTER);
        middle.add(mapToolBar,BorderLayout.EAST);
        middle.setBackground(Color.lightGray);
        this.add(name);
        this.add(author);
        this.add(middle);
    }

    public void setProject(Project project) {
        this.project = project;
        maps.clear();
        tabbedPane.removeAll();
        if(project!=null){
            project.addSubscriber(this);
            update(project,"Promenjeno ime");
            update(project,"Promenjen autor");
            for(MapNode mapNode : project.getChildren()) {
                MapView mapView = new MapView((MindMap) mapNode,this);
                maps.add(mapView);
                tabbedPane.addTab(mapView.getMindMap().getName(), mapView);
                mapNode.addSubscriber(this);
            }
        }else{
            name.setText("Prazan projekat");
        }
    }

    public MapView getActiveMindMap(){
        MapView mindMapView = null;
        for(MapView mindMapView1 : MainFrame.getInstance().getMainWindow().getMaps()){
            if(mindMapView1.getMindMap().getName().equals(((MapView) MainFrame.getInstance().getMainWindow().getTabbedPane().getSelectedComponent()).getMindMap().getName())){
                mindMapView = mindMapView1;
            }
        }
        return mindMapView;
    }

    @Override
    public void update(Object notification, String message) {

        if(notification instanceof Project && message.equals("Promenjeno ime")){
            this.name.setText(((Project) notification).getName());
            this.project = (Project) notification;
            //this.repaint();
        }
        if(notification instanceof Project && message.equals("Promenjen autor")){
            this.author.setText("Autor:" + ((Project) notification).getAuthor());
        }

        if(notification instanceof MindMap && message.equals("Promenjeno ime")){
            for(MapView m:maps){
                if(m.getMindMap().equals(notification)){
                    tabbedPane.setTitleAt(maps.indexOf(m),m.getMindMap().getName());
                    break;
                }
            }
        }

        if(notification instanceof MindMap && message.equals("Obrisano dete")){
            for(MapView m:maps){
                if(m.getMindMap().equals(notification)){
                    tabbedPane.removeTabAt(maps.indexOf(m));
                    maps.remove(m);
                    m.getMindMap().removeSubscriber(this);

                    break;
                }
            }
        }

        if(notification instanceof MindMap && message.equals("Dodato dete")){
            MapView mapView = new MapView((MindMap) notification,this);
            maps.add(mapView);
            tabbedPane.addTab(mapView.getMindMap().getName(),mapView);
            ((MindMap) notification).addSubscriber(this);
        }
        if(notification instanceof Project && message.equals("Obrisano dete")){
            this.setProject(null);
        }
    }


    public void mousePressed(Point pos,MapView mapView) throws IOException {
        this.stateManager.getCurrentState().misKliknut(pos,mapView);
    }
    public void mouseDragged(Point position,MapView mapView) throws IOException {
        this.stateManager.getCurrentState().misPovucen(position, mapView);
    }
    public void mouseReleased(Point position,MapView mapView) throws IOException {
        this.stateManager.getCurrentState().misOtpusten(position, mapView);
    }
    public void mouseWheelMoved(int wheel,MapView mapView) throws IOException {
        this.stateManager.getCurrentState().misSkrolovan(wheel,mapView);
    }


    public MapActionManager getMapActionManager() {
        return mapActionManager;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void startAddWordS() {
        this.stateManager.setAddWordS();
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    public void startCreateConnectionS() {
        this.stateManager.setCreateConnectionS();
    }

    public void startDeleteS() {
        this.stateManager.setDeleteS();    }

    public void startMoveS() {

        this.stateManager.setMoveS();    }

    public void startSelectS() {
        this.stateManager.setSelectS();    }

    public List<MapView> getMaps() {
        return maps;
    }

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }
}
