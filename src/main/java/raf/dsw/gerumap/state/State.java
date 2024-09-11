package raf.dsw.gerumap.state;

import raf.dsw.gerumap.mapRepository.view.MapView;
import java.awt.*;
import java.io.IOException;

public interface State {
     void misKliknut(Point position, MapView mapView) throws IOException;
     void misPovucen(Point position, MapView mapView) throws IOException;
     void misOtpusten(Point position, MapView mapView) throws IOException;
     void misSkrolovan(int wheel,MapView mapView) throws IOException;
}
