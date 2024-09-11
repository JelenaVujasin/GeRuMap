package raf.dsw.gerumap.state;


import lombok.Getter;
import raf.dsw.gerumap.state.concrete.*;
@Getter
public class StateManager {
    private State currentState;
    private AddWordS addWordS;
    private CreateConnectionS createConnectionS;
    private DeleteS deleteS;
    private MoveS moveS;
    private SelectS selectS;
    private ZoomS zoomS;

    private void initStates(){
        addWordS = new AddWordS();
        createConnectionS = new CreateConnectionS();
        deleteS = new DeleteS();
        moveS = new MoveS();
        selectS = new  SelectS();
        zoomS = new ZoomS();
        currentState = addWordS;
    }

    public StateManager() {
        initStates();
    }

    public void setAddWordS() {
        currentState = addWordS;
    }

    public void setCreateConnectionS() {
        currentState = createConnectionS;
    }

    public void setDeleteS() {
        currentState = deleteS;
    }

    public void setMoveS() {
        currentState = moveS;
    }

    public void setSelectS() {
        currentState = selectS;
    }

    public void setZoomS() {
        currentState = zoomS;
    }

    public State getCurrentState() {
        return currentState;
    }
}
