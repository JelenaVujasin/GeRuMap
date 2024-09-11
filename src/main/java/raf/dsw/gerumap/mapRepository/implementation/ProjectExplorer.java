package raf.dsw.gerumap.mapRepository.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.gerumap.mapRepository.composite.MapNode;
import raf.dsw.gerumap.mapRepository.composite.MapNodeComposite;
import java.io.IOException;


@Getter
@Setter


public class ProjectExplorer extends MapNodeComposite {
    public ProjectExplorer(String name, MapNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(MapNode project) throws IOException {
        if(project instanceof Project){
            getChildren().add(project);
            if(project.getName().equals("My Project Explorer")){
                project.setName("Project " + getChildren().size());
            }else {
                project.setName(project.getName());
            }
        }
    }

    @Override
    public void removeChild(MapNode child) {
        if(child instanceof Project){
            MapNode parent = child.getParent();
            for(int i = 1;i<((Project) child).getChildren().size();i++){
                ((Project) child).getChildren().remove(i);
            }

            if(parent instanceof ProjectExplorer){
                ((ProjectExplorer) parent).getChildren().remove(child);
            }
        }


    }

}
