package raf.dsw.gerumap.state.concrete;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import raf.dsw.gerumap.AppCore;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.mapRepository.commands.AbstractCommand;
import raf.dsw.gerumap.mapRepository.commands.implementation.AddConnectionCommand;
import raf.dsw.gerumap.mapRepository.commands.implementation.AddWordCommand;
import raf.dsw.gerumap.mapRepository.implementation.Connection;
import raf.dsw.gerumap.mapRepository.view.MapView;
import raf.dsw.gerumap.mapRepository.view.WordView;
import raf.dsw.gerumap.message.EventType;
import raf.dsw.gerumap.state.State;
import java.awt.*;
import java.io.IOException;

@Getter
@Setter
public class CreateConnectionS implements State {
    private String thickness = "1";
    @Override
    @SneakyThrows
    public void misKliknut(Point position, MapView mapView)  {
        for (WordView wordView : mapView.getWordViews()) {
            if(wordView.elementAt(position)){
                int x = wordView.getX();
                int y = wordView.getY();
                x += wordView.getWidth()/2;
                y += wordView.getHeight()/2;
                Connection connection = new Connection("",mapView.getMindMap().getParent(),"-16777216",Integer.parseInt(thickness),new Point(x,y),wordView.getWord());
                mapView.getMindMap().getConnections().add(connection);
                //mapView.update(connection,"Dodata veza");
                mapView.getMindMap().notifySubscriber(connection,"Dodata veza");
                return;
            }else{
                //AppCore.getInstance().getMessageGenerator().generate(EventType.VEZA_NIJE_DOVRSENA);
            }
        }

    }

    @Override
    public void misPovucen(Point position, MapView mapView) throws IndexOutOfBoundsException, IOException {
        Connection curr;
        if(mapView.getMindMap().getConnections().size()>0) {
            curr = mapView.getMindMap().getConnections().get(mapView.getMindMap().getConnections().size() - 1);
        }else return;
        if(curr.getTo() != null)
            return;
        curr.setPointEnd(position);
        mapView.repaint();
    }

    @Override
    public void misOtpusten(Point position, MapView mapView) throws IOException {
        int flag = 0;
        Connection curr;
        if(mapView.getMindMap().getConnections().size()>0) {
            curr = mapView.getMindMap().getConnections().get(mapView.getMindMap().getConnections().size() - 1);
        }else{
            return;
        }
        for (WordView wordView : mapView.getWordViews()) {
            if(wordView.elementAt(curr.getPointEnd()) && !wordView.equals(curr.getFrom())){
                flag++;
                curr.setTo(wordView.getWord());
                mapView.getMindMap().getConnections().remove(curr);
                mapView.getMindMap().notifySubscriber(curr,"Zavrsena veza");

                Connection connection = new Connection("",mapView.getMindMap().getParent(),"-16777216",Integer.parseInt(thickness),new Point(curr.getPointStart()),curr.getFrom());
                connection.setTo(curr.getTo());
                connection.setPointEnd(new Point(curr.getPointEnd()));

                AbstractCommand command = new AddConnectionCommand(mapView,connection);
                MapView currMV = (MapView) MainFrame.getInstance().getMainWindow().getTabbedPane().getSelectedComponent();
                currMV.getMindMap().getCommandManager().addCommand(command);
                return;
            }
        }
        if(flag == 0){
            AppCore.getInstance().getMessageGenerator().generate(EventType.VEZA_NIJE_DOVRSENA);
        }
        mapView.getMindMap().getConnections().remove(curr);
        //mapView.update(curr,"Zavrsena veza");
        mapView.getMindMap().notifySubscriber(curr,"Zavrsena veza");
    }

    @Override
    public void misSkrolovan(int wheel, MapView mapView) {

    }


}
