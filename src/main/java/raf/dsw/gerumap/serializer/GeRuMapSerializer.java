package raf.dsw.gerumap.serializer;

import com.google.gson.*;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import lombok.Setter;
import netscape.javascript.JSObject;
import raf.dsw.gerumap.core.Serializer;
import raf.dsw.gerumap.mapRepository.composite.MapNode;
import raf.dsw.gerumap.mapRepository.implementation.*;
import raf.dsw.gerumap.observer.Subscriber;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GeRuMapSerializer implements Serializer {
    final RuntimeTypeAdapterFactory<MapNode> typeAdapterFactory = RuntimeTypeAdapterFactory.of(MapNode.class,"type")
            .registerSubtype(Project.class,"project")
            .registerSubtype(MindMap.class,"map")
            .registerSubtype(Element.class,"element");

    private final Gson gson = new GsonBuilder().registerTypeAdapterFactory(typeAdapterFactory).create();
    @Override
    public Project loadProject(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        JsonObject jsonObject;
        jsonObject =  gson.fromJson(fileReader,JsonObject.class);
        JsonArray mape = jsonObject.getAsJsonArray("children");
        List<MapNode> children = new ArrayList<>();

        for(JsonElement element:mape){

            children.add(makeMap(element));
        }

        String name = jsonObject.get("name").getAsString();
        String author = jsonObject.get("author").getAsString();
        String filePath = jsonObject.get("filePath").getAsString();
        Project project = new Project(name,filePath,author);
        project.setChildren(children);


        return project;
    }

    @Override
    public void saveProject(Project project) throws IOException {
        FileWriter fileWriter = new FileWriter(project.getFilePath());
        gson.toJson(project,fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }

    public void saveMapAsPattern(MindMap map) throws IOException {

        FileWriter fileWriter = new FileWriter("C:\\Users\\Jelena Vujasin\\Desktop\\gerumap-tim_jelenavujasin_lukadavidovic\\src\\main\\resources\\patterns\\" + map.getName());
        gson.toJson(map,fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }

    public MindMap loadMapAsPattern(File file) throws FileNotFoundException {
        FileReader fileReader = new FileReader(file);
        JsonElement jsonElement =  gson.fromJson(fileReader,JsonElement.class);
        return makeMap(jsonElement);
    }

    private Word makeWord(JsonElement element1){
        Point point ;
        Dimension dimensionWord;
        String wordName = element1.getAsJsonObject().get("name").getAsString();
        boolean selected = element1.getAsJsonObject().get("selected").getAsBoolean();
        int thickness = element1.getAsJsonObject().get("thickness").getAsInt();
        String color = element1.getAsJsonObject().get("color").getAsString();
        JsonElement position = element1.getAsJsonObject().get("position");
        int x = position.getAsJsonObject().get("x").getAsInt();
        int y = position.getAsJsonObject().get("y").getAsInt();
        point = new Point(x,y);
        JsonElement dimension = element1.getAsJsonObject().get("dimension");
        int width = dimension.getAsJsonObject().get("width").getAsInt();
        int height = dimension.getAsJsonObject().get("height").getAsInt();

        dimensionWord = new Dimension(width,height);
        return new Word(wordName,color,thickness,selected,point,dimensionWord);
    }
    private Connection makeConnection(JsonElement element){
        JsonElement startPosition = element.getAsJsonObject().get("pointStart");
        JsonElement endPosition = element.getAsJsonObject().get("pointEnd");
        int xStart = startPosition.getAsJsonObject().get("x").getAsInt();
        int yStart = startPosition.getAsJsonObject().get("y").getAsInt();
        int xEnd = endPosition.getAsJsonObject().get("x").getAsInt();
        int yEnd = endPosition.getAsJsonObject().get("y").getAsInt();
        Point start = new Point(xStart,yStart);
        Point end = new Point(xEnd,yEnd);
        int thickness = element.getAsJsonObject().get("thickness").getAsInt();
        boolean selected = element.getAsJsonObject().get("selected").getAsBoolean();
        String color = element.getAsJsonObject().get("color").getAsString();
        String name = element.getAsJsonObject().get("name").getAsString();
        JsonElement fromWord = element.getAsJsonObject().get("from");
        JsonElement toWord = element.getAsJsonObject().get("from");
        Word from = makeWord(fromWord);
        Word to = makeWord(toWord);
        return new Connection(name,color,thickness,selected,start,end,from,to);
    }

    private MindMap makeMap(JsonElement element){
        String name = element.getAsJsonObject().get("name").getAsString();
        MindMap map = new MindMap(name);
        JsonArray words = element.getAsJsonObject().getAsJsonArray("words");
        JsonArray connections = element.getAsJsonObject().getAsJsonArray("connections");

        for(JsonElement element1:words){
            map.getWords().add(makeWord(element1));
        }
        for(JsonElement element1:connections){
            map.getConnections().add(makeConnection(element1));
        }
        return map;
    }
}
