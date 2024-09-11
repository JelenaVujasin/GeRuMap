package raf.dsw.gerumap.state.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapActionManager {
    private AddWord addWord;
    private CreateConnection createConnection;
    private Delete delete;
    private Move move;
    private Select select;
    private Zoom zoom;
    private ColorPicker colorPicker;
    private ChangeLineThickness changeLineThickness;
    private ChangeWordName changeWordName;

    public MapActionManager() {
        init();
    }

    private void init(){
        addWord = new AddWord();
        delete = new Delete();
        move = new Move();
        select = new Select();
        zoom = new Zoom();
        createConnection = new CreateConnection();
        colorPicker =  new ColorPicker();
        changeLineThickness = new ChangeLineThickness();
        changeWordName = new ChangeWordName();
    }
}
