package raf.dsw.gerumap.observer;

import java.io.IOException;

public interface Subscriber {
     void update(Object notification,String message) throws IOException;
}
