package raf.dsw.gerumap.core;

import raf.dsw.gerumap.message.EventType;
import raf.dsw.gerumap.message.Message;
import raf.dsw.gerumap.observer.Publisher;

import java.io.IOException;

public interface MessageGenerator extends Publisher {
     Message generate(EventType eventType) throws IOException;
}
