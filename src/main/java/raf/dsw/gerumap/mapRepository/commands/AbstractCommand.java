package raf.dsw.gerumap.mapRepository.commands;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
@Getter
@Setter
public abstract class AbstractCommand {
    public abstract void undoCommand() throws IOException;
    public abstract void redoCommand() throws IOException;
}
