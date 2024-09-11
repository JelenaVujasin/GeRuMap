package raf.dsw.gerumap.mapRepository.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.mapRepository.implementation.Connection;
import raf.dsw.gerumap.mapRepository.implementation.Element;
import raf.dsw.gerumap.mapRepository.implementation.Word;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

@Getter
@Setter
public class WordView extends ElementView {
    private Shape shape;
    private Word word;
    int x;
    int y;
    int width;
    int height;
    private String before;

    public WordView(Element element) {
        super(element);
        word = (Word) element;
        if(element != null){
            element.addSubscriber(this);
            this.x = ((Word) element).getPosition().x;
            this.y = ((Word) element).getPosition().y;
            this.width = ((Word) element).getDimension().width;
            this.height = ((Word) element).getDimension().height;
            float x = this.x;
            float y = this.y;
            float width = this.width;
            float height = this.height;
            this.shape = new RoundRectangle2D.Float(x,y,width,height,150,100);
        }
    }


    @Override
    public void paint(Graphics2D g,Element word) {
        Font font = new Font("TimesRoman",Font.BOLD,15);
        FontMetrics metrics = g.getFontMetrics(font);
        g.setColor(new Color(Integer.parseInt(word.getColor())));
        g.setFont(font);
        g.setStroke(new BasicStroke(word.getThickness()));
        //g.drawRoundRect(this.x, this.y,this.width,this.height,150,100);
        g.draw(shape);
        //System.out.println(shape.getBounds2D());
        int x2 = x + (width-metrics.stringWidth(word.getName()))/2;
        int y2 = y + ((height-metrics.getHeight())/2)+metrics.getAscent();
        g.setColor(Color.WHITE);
        g.fillRoundRect(this.x, this.y,this.width,this.height,150,100);
        g.setColor(new Color(Integer.parseInt(word.getColor())));
        g.drawString(word.getName(),x2,y2);
    }

    @Override
    public boolean elementAt(Point pos) {

        //System.out.println("Element at pos " + pos);
        return this.shape.contains(pos);
    }

    @Override
    public void update(Object notification, String message) throws IOException {
        if (notification instanceof Word && message.equals("Selektovan")) {
            before = ((Word) notification).getColor();
            ((Word) notification).setColor("-16711681");
        } else if (notification instanceof Word && message.equals("Odselektovan")) {
            ((Word) notification).setColor(before);
        }else if(notification instanceof Word && message.equals("Promenjena pozicija")){
            this.x = ((Word) notification).getPosition().x;
            this.y = ((Word) notification).getPosition().y;
//            System.out.println("X u promenjenoj poziciji:" + x);
//            System.out.println("Y u promenjenoj poziciji:" +y);
            shape =  new RoundRectangle2D.Float(this.x,this.y,this.width,this.height,150,100);
            //System.out.println(shape.getBounds2D());
        }

    }
}
