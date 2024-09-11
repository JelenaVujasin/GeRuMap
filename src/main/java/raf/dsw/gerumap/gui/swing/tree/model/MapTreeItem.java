package raf.dsw.gerumap.gui.swing.tree.model;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.mapRepository.composite.MapNode;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.IOException;

@Setter
@Getter

public class MapTreeItem extends DefaultMutableTreeNode {

    private MapNode mapNode;

    public MapTreeItem(MapNode nodeModel){
        this.mapNode = nodeModel;
    }


    @Override
    public String toString() {

        return this.mapNode.getName();
    }

    public void setName(String name) throws IOException {
        this.mapNode.setName(name);
        this.mapNode.notifySubscriber(this,"Promenjeno ime");
    }

}
