package raf.dsw.gerumap.logger;

import raf.dsw.gerumap.core.ErrorLog;
import raf.dsw.gerumap.message.Message;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements ErrorLog {
    File file;
    FileWriter fw;
    DateTimeFormatter dtf;
    LocalDateTime now;

    public FileLogger() throws IOException {
        file = new File("src/main/resources/filelog.txt");
        fw = new FileWriter("src/main/resources/filelog.txt");
        dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        now = LocalDateTime.now();
    }

    @Override
    public void log(Message message) {
        System.out.println("LOG");
        try {
            System.out.println("TRY");
            fw.write("[" + message.getMessageType().name()+ "]" + "[" + dtf.format(now) + "]" + message.getMessage());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object notification, String message) {
        if(notification instanceof Message){
            System.out.println("Update");
            this.log((Message) notification);
        }
    }


}
