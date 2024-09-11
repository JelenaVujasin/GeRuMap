package raf.dsw.gerumap.mapRepository.commands.implementation;

import raf.dsw.gerumap.mapRepository.commands.AbstractCommand;
import raf.dsw.gerumap.mapRepository.implementation.Word;
import raf.dsw.gerumap.mapRepository.view.MapView;
import raf.dsw.gerumap.mapRepository.view.WordView;
import java.io.IOException;

public class AddWordCommand extends AbstractCommand {
    MapView mapView;
    Word word;
    WordView wordView;

    public AddWordCommand(MapView mapView, Word newWord, WordView newWordView) {
        this.mapView = mapView;
        this.word = newWord;
        this.wordView = newWordView;
    }

    @Override
    public void undoCommand() throws IOException {
        Word currWord = null;
        for(Word word1 : mapView.getMindMap().getWords())
            if(word1.getName().equals(word.getName())){
                currWord = word1;
                break;
            }
        WordView currWordView = null;
        for(WordView wordView1 : mapView.getWordViews())
            if(wordView1.getWord().getName().equals(word.getName())){
                currWordView = wordView1;
                break;
            }
        if(currWord != null && currWord.isSelected()){
            currWord.setSelected(false);
            mapView.getSelected().remove(currWordView);
        }
        mapView.getMindMap().getWords().remove(currWord);
        mapView.getWordViews().remove(currWordView);
        mapView.repaint();
    }

    @Override
    public void redoCommand() throws IOException {
        mapView.getMindMap().addWord(word);
        mapView.getWordViews().add(wordView);
    }
}
