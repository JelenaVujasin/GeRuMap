package raf.dsw.gerumap.logger;

import raf.dsw.gerumap.core.ErrorLog;
import raf.dsw.gerumap.message.Message;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleLogger implements ErrorLog {
    @Override
    public void log(Message message) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("[" + message.getMessageType().name()+ "]" + "[" + dtf.format(now) + "]" + message.getMessage());
    }

    @Override
    public void update(Object notification, String message) {
        if(notification instanceof Message){
            this.log((Message) notification);
        }
    }
}
