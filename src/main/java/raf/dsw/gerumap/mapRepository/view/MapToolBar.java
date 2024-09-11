package raf.dsw.gerumap.mapRepository.view;

import javax.swing.*;
import java.awt.*;

public class MapToolBar extends JToolBar {
    public MapToolBar(ProjectView projectView) {
        super(VERTICAL);
        setFloatable(false);
        add(projectView.getMapActionManager().getAddWord());
        add(projectView.getMapActionManager().getCreateConnection());
        add(projectView.getMapActionManager().getDelete());
        add(projectView.getMapActionManager().getMove());
        add(projectView.getMapActionManager().getSelect());
        add(projectView.getMapActionManager().getZoom());
        add(projectView.getMapActionManager().getColorPicker());
        add(projectView.getMapActionManager().getChangeWordName());
        add(projectView.getMapActionManager().getChangeLineThickness());
        setBackground(Color.lightGray);

    }
}
