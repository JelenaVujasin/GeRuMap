package raf.dsw.gerumap.gui.swing.controller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ActionManager{
    private InfoAction infoAction;
    private NewProjectAction newProjectAction;
    private DeleteAction deleteAction;
    private EditAuthor editAuthor;
    private ExitAction exitAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private OpenAction openAction;
    private SaveAction saveAction;
    private SaveMapPatternAction saveMapPatternAction;
    private OpenPatterns openPatterns;
    private ExportMapAction exportMapAction;

    public ActionManager() {
        initActions();
    }

    private void initActions() {
        infoAction = new InfoAction();
        exitAction = new ExitAction();
        newProjectAction = new NewProjectAction();
        deleteAction = new DeleteAction();
        editAuthor = new EditAuthor();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        undoAction.setEnabled(false);
        redoAction.setEnabled(false);
        openAction = new OpenAction();
        saveAction = new SaveAction();
        saveMapPatternAction = new SaveMapPatternAction();
        openPatterns = new OpenPatterns();
        exportMapAction = new ExportMapAction();
    }


}
