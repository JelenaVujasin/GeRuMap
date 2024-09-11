package raf.dsw.gerumap.core;

import lombok.Getter;
import raf.dsw.gerumap.mapRepository.factory.FactoryUtils;

@Getter
public abstract class AppFramework {
    protected Gui gui;
    protected MapRepository mapRepository;
    protected ErrorLog errorLog;
    protected MessageGenerator messageGenerator;
    protected Serializer serializer;
    public abstract void run();

    public void initialise(Gui gui, MapRepository mapRepository, ErrorLog errorLog, MessageGenerator messageGenerator, Serializer serializer){
        this.gui = gui;
        this.mapRepository = mapRepository;
        this.errorLog = errorLog;
        this.messageGenerator = messageGenerator;
        this.serializer = serializer;
        FactoryUtils.initialise();
        messageGenerator.addSubscriber(errorLog);
        messageGenerator.addSubscriber(gui);
    }


}
