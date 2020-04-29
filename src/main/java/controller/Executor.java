package controller;

import service.ReaderTxt;

import java.io.File;

public class Executor {
    public void runProgramHW(){
        ReaderTxt song = new ReaderTxt();

        song.showTotalWordsInSong();
        song.showWordsThatShouldBeRemoved();
        song.showFilteredSongText();
        song.showTheMostRepeatedWords(3);
            }
}
