package raf.dsw.gerumap.mapRepository.composite;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import raf.dsw.gerumap.observer.Publisher;
import raf.dsw.gerumap.observer.Subscriber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

@Getter
@Setter
@ToString
public  abstract class MapNode implements Publisher {
    @ToString.Exclude
    private transient List<Subscriber> subscribers;
    private String name;
    @ToString.Exclude
    private transient MapNode parent;
    protected transient String type;

    public MapNode(String name, MapNode parent) {
        this.name = name;
        this.parent = parent;
        subscribers = new ArrayList<>();
    }

    public MapNode(String name) {
        this.name = name;
        subscribers = new ArrayList<>();
    }

    @Override
    public void addSubscriber(Subscriber subscriber){
        if(!subscribers.contains(subscriber)){
            subscribers.add(subscriber);
        }
    }
    @Override
    public void removeSubscriber(Subscriber subscriber){
        subscribers.remove(subscriber);
    }
    public void notifySubscriber(Object notification,String message) throws IOException {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(notification, message);
        }

    }
    public void setName(String name) throws IOException {
        this.name = name;
        notifySubscriber(this,"Promenjeno ime");
    }
}
