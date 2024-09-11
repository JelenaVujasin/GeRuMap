package raf.dsw.gerumap;

import raf.dsw.gerumap.core.*;
import raf.dsw.gerumap.gui.swing.SwingGui;
import raf.dsw.gerumap.logger.ConsoleLogger;
import raf.dsw.gerumap.logger.FileLogger;
import raf.dsw.gerumap.mapRepository.implementation.MapRepositoryImplementation;
import raf.dsw.gerumap.message.MessageGeneratorImpl;
import raf.dsw.gerumap.serializer.GeRuMapSerializer;

import java.io.IOException;

public class AppCore extends AppFramework {
    private static AppCore instance;

    private AppCore() {
        super();

    }

    public static AppCore getInstance() {
        if (instance == null)
            instance = new AppCore();
        return instance;
    }

    @Override
    public void run() {
        this.gui.start();
    }

    public static void main(String[] args) throws IOException {
        AppFramework appCore = AppCore.getInstance();
        MapRepository mapRepository = new MapRepositoryImplementation();
        Gui gui = new SwingGui();
        MessageGenerator messageGenerator = new MessageGeneratorImpl();
        Serializer serializer = new GeRuMapSerializer();
        ErrorLog errorLog = new ConsoleLogger();

        appCore.initialise(gui,mapRepository,errorLog,messageGenerator,serializer);
        appCore.run();
    }


}

