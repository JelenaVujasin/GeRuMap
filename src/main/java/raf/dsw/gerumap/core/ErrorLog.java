package raf.dsw.gerumap.core;

import raf.dsw.gerumap.message.Message;
import raf.dsw.gerumap.observer.Subscriber;

import java.io.IOException;

public interface ErrorLog extends Subscriber {
    void log(Message message) throws IOException;
}
