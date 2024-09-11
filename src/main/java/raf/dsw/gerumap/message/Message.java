package raf.dsw.gerumap.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    String message;
    MessageType messageType;

    public Message(String message, MessageType messageType) {
        this.message = message;
        this.messageType = messageType;
    }
}
