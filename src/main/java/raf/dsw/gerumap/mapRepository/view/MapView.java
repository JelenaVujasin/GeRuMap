package raf.dsw.gerumap.mapRepository.view;


import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import raf.dsw.gerumap.gui.swing.controller.MapViewMouseListener;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.implementation.Connection;
import raf.dsw.gerumap.mapRepository.implementation.MindMap;
import raf.dsw.gerumap.mapRepository.implementation.Word;
import raf.dsw.gerumap.observer.Subscriber;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@Setter
@Getter
public class MapView extends JPanel implements Subscriber {
    MindMap mindMap;
    JLabel name;
    List<WordView> wordViews;
    List<ConnectionView> connectionViews;
    List<WordView> selected;
    List<ConnectionView> selectedConnections;
    Rectangle laso = null;
    AffineTransform at;
    private double zoomFactor = 1;
    private double prevZoomFactor = 1;
    private boolean zoom;
    private boolean dragged;
    private boolean released;
    private double xOffset = 0;
    private double yOffset = 0;
    private  int xDiff;
    private int yDiff;
    private Point startPoint;

    public MapView(MindMap mindMap,ProjectView projectView) {
        at = new AffineTransform();
        mindMap.addSubscriber(this);
        wordViews = new ArrayList<>();
        connectionViews = new ArrayList<>();
        selected = new ArrayList<>();
        selectedConnections = new ArrayList<>();
        this.mindMap = mindMap;
        this.setBackground(Color.white);
        this.addMouseListener(new MapViewMouseListener(this,projectView));
        this.addMouseMotionListener(new MapViewMouseListener(this,projectView));
        this.addMouseWheelListener(new MapViewMouseListener(this,projectView));
        for (Word word : mindMap.getWords()) {
            WordView wordView = new WordView(word);
            this.wordViews.add(wordView);
            wordView.getWord().addSubscriber(this);
        }
        for(Connection connection:mindMap.getConnections()){
            ConnectionView connectionView = new ConnectionView(connection);
            this.connectionViews.add(connectionView);
            connectionView.getConnection().addSubscriber(this);
        }

    }

    @Override
    public void update(Object notification, String message) {
        if (notification instanceof Word && message.equals("Dodat pojam")) {
            //WordView wordView = new WordView((Word) notification);
            //this.wordViews.add(wordView);
            this.repaint();
        } else if (notification instanceof WordView && message.equals("Selektovan")) {
            this.selected.add((WordView) notification);
            this.repaint();
        } else if (notification instanceof WordView && message.equals("Odselektovan")) {
            this.selected.remove((WordView) notification);
            this.repaint();
        } else if (notification instanceof Connection && message.equals("Dodata veza")) {
            ConnectionView connectionView = new ConnectionView((Connection) notification);
            this.connectionViews.add(connectionView);
            this.repaint();
            return;
        } else if (notification instanceof Connection && message.equals("Zavrsena veza")) {
            this.connectionViews.remove(connectionViews.size() - 1);
            this.repaint();
        } else if (notification instanceof ConnectionView && message.equals("Selektovana veza")) {
            this.selectedConnections.add((ConnectionView) notification);
            this.repaint();
        } else if (notification instanceof ConnectionView && message.equals("Odselektovana veza")) {
            this.selectedConnections.remove((ConnectionView) notification);
            this.repaint();
        }else if (notification instanceof Rectangle && message.equals("Laso select")) {
            laso = (Rectangle) notification;
        }else if(notification instanceof Rectangle && message.equals("Laso gotov")){
            laso = null;
            this.repaint();
        }else if(notification instanceof AffineTransform && message.equals("Zoom")){
            at = (AffineTransform) notification;
            this.repaint();
        }
        this.repaint();
    }

    public boolean overLap(Word word){
        int tx = word.getPosition().x;
        int ty = word.getPosition().y;
        for (Word wr:this.getMindMap().getWords()){
            int x = wr.getPosition().x;
            int y = wr.getPosition().y;
            int width = wr.getDimension().width;
            int height = wr.getDimension().height;
            if(tx > x-width && ty > y-height  && tx < x+width && ty < y+height)
                return true;
        }
        return false;
    }

    public void saveImage(String name,String type) {
        type = "png";
        BufferedImage image = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        paint(g2);
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.showSaveDialog(null);
        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                ImageIO.write(image, type, new File(jfc.getSelectedFile() + "/" + name + "." + type));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @SneakyThrows
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(laso!=null) {
            Graphics2D graphics2D = (Graphics2D) g;
            int x = laso.getBounds().x;
            int y = laso.getBounds().y;
            int width = laso.getBounds().width;
            int height = laso.getBounds().height;
            graphics2D.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
            graphics2D.drawRect(x, y, width, height);
            this.repaint();

        }
        paintZoom((Graphics2D) g,at);
        for(ConnectionView connectionView : this.connectionViews){
            connectionView.paint((Graphics2D) g,connectionView.getConnection());
        }
        for (WordView wordView : this.wordViews) {
            wordView.paint((Graphics2D) g, wordView.getWord());
        }
    }
    public boolean connectionAt(Point position){
        for(WordView wordView : this.wordViews){
            if(wordView.elementAt(position))
                return false;
        }
        return true;
    }

    public MindMap getMindMap() {
        return mindMap;
    }

    @Override
    public String getName() {
        return name.getText();
    }

    public void paintZoom(Graphics2D g,AffineTransform at) throws IOException {
        g.transform(at);

    }
}
