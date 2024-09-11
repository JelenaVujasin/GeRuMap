package raf.dsw.gerumap.observer;

import java.io.IOException;

public interface Publisher {
     void addSubscriber(Subscriber subscriber);
     void removeSubscriber(Subscriber subscriber);
     void notifySubscriber(Object notification,String message) throws IOException;
}
