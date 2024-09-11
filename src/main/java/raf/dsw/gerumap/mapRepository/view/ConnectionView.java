package raf.dsw.gerumap.mapRepository.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.mapRepository.implementation.Connection;
import raf.dsw.gerumap.mapRepository.implementation.Element;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.IOException;
@Getter
@Setter
public class ConnectionView extends ElementView{
    Connection connection;
    Shape shape;
    private int targetBoxSize = 4;
    String before;
    public ConnectionView(Element element) {
        super(element);
        connection = (Connection) element;
        element.addSubscriber(this);
    }

    @Override
    public void paint(Graphics2D g,Element connection1){
        g.setColor(new Color(Integer.parseInt(connection.getColor())));
        g.setStroke(new BasicStroke(connection.getThickness()));
        shape = new Line2D.Double(connection.getPointStart().x,connection.getPointStart().y,connection.getPointEnd().x,connection.getPointEnd().y);
        g.draw(shape);
        //g.drawLine(connection.getPointStart().x,connection.getPointStart().y,connection.getPointEnd().x,connection.getPointEnd().y);
    }

    @Override
    public boolean elementAt(Point pos) {
        return getClickedLine(pos.x,pos.y);
    }


    @Override
    public void update(Object notification, String message) throws IOException {
        if (notification instanceof Connection && message.equals("Selektovan")) {
            before = ((Connection) notification).getColor();
            ((Connection) notification).setColor("-16711681");
        } else if (notification instanceof Connection && message.equals("Odselektovan")) {
            ((Connection) notification).setColor(before);
        }else if(notification instanceof  Connection && message.equals("Promenjena pozicija veze")){
            //System.out.println("Promenjena pozicija veze");
            this.connection = (Connection) notification;
            shape = new Line2D.Double(connection.getPointStart().x,connection.getPointStart().y,connection.getPointEnd().x,connection.getPointEnd().y);
        }
    }

    public boolean getClickedLine(int x,int y){
        int BoxX = x-targetBoxSize/2;
        int BoxY = y-targetBoxSize/2;

        int width = targetBoxSize;
        int height = targetBoxSize;

        return shape.intersects(BoxX,BoxY,width,height);
    }
}
