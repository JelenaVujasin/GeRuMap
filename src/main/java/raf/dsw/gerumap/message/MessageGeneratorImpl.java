package raf.dsw.gerumap.message;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.core.MessageGenerator;
import raf.dsw.gerumap.observer.Subscriber;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MessageGeneratorImpl implements MessageGenerator {
    private List<Subscriber> subscribers;
    private Message message;

    public MessageGeneratorImpl() {
        subscribers = new ArrayList<>();
    }

    @Override
    public Message generate(EventType eventType) throws IOException {
        if (eventType.equals(EventType.DODATO_DETE_ELEMENTU)) {
            message =  new Message("Element ne može imati dete",MessageType.ERROR);
        } else if (eventType.equals(EventType.PRAZNO_IME)) {
            message = new Message("Ime čvora ne može biti prazno",MessageType.ERROR);
        } else if (eventType.equals(EventType.OBRISAN_EXPLORER)) {
            message = new Message("Ne možete obrisati Project Explorer",MessageType.ERROR);
        } else if(eventType.equals(EventType.NIJE_SELEKTOVAN_CVOR)){
            message = new Message("Niste selektovali čvor",MessageType.ERROR);
        }else if(eventType.equals(EventType.NIJE_SELEKTOVAN_POJAM)){
            message = new Message("Morate selektovati pojam ili vezu da bi izvrsili radnju",MessageType.ERROR);
        }else if(eventType.equals(EventType.PREKLAPANJE_POJMA)){
            message = new Message("Pojmovi se preklapaju",MessageType.ERROR);
        }else if(eventType.equals(EventType.VEZA_NIJE_DOVRSENA)){
            message = new Message("Oba kraja veze moraju biti povezana sa pojmom",MessageType.ERROR);
        }else if(eventType.equals(EventType.IZABRANA_BOJA)){
            message = new Message("Morate izabrati boju",MessageType.ERROR);
        }else if(eventType.equals(EventType.IZABRATI_EXPLORER)){
            message = new Message("Da biste otvorili projekat morate kliknuti na project explorer",MessageType.ERROR);
        }else if(eventType.equals(EventType.NIJE_IZABRAN_PROJEKAT)){
            message = new Message("Morate izabrati projekat za cuvanje",MessageType.ERROR);
        }else if(eventType.equals(EventType.NIJE_IZABRANA_MAPA)){
            message = new Message("Morate izabrati mapu koja ce biti dodata u sablone",MessageType.ERROR);
        }else if(eventType.equals(EventType.MAPA_DODATA_KAO_PATTERN)){
            message = new Message("Mapa je uspesno dodata u sablone",MessageType.INFORMATION);
        }
        notifySubscriber(message,message.getMessageType().name());
        return null;
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscriber(Object notification, String message) throws IOException {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(notification, message);
        }
    }
}
