package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class ReaderTxt {
    Path path = new File("src//main//resources//data.txt").toPath();
    String[] textOfSong;

    {
        try {
            textOfSong = Files.readString(path)
                    .replace("\r\n", " ")
                    .split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTotalWordsInSong() {
        System.out.println("Number of words: " + textOfSong.length);
    }

    public void showFilteredSongText() {
        List<String> filteredSongText = Arrays.stream(textOfSong)
                .filter(word -> (word.length() > 3))
                .collect(Collectors.toList());
        System.out.println("Total number of words  without short words: " + filteredSongText.size());
    }

    public void showWordsThatShouldBeRemoved() {
        List<String> wordsToBeRemoved = (List<String>) Arrays.stream(textOfSong)
                .filter(word -> (word.length() <= 3) && word.length() >= 1)
                .collect(Collectors.toList());
        System.out.println("Total number of words that should be removed is : " + wordsToBeRemoved.size());
        System.out.println("Words to be removed: " + wordsToBeRemoved);
    }

    public void showTheMostRepeatedWords(int count) {

        Map<String, Integer> songWordsUsedTimes = Arrays.stream(textOfSong)
                .filter(word -> !word.equals(""))
                .collect(toMap(w -> w.toLowerCase(), w -> 1, Integer::sum));
        LinkedHashMap<String, Integer> sortedSongWordsWithUsedTimes = songWordsUsedTimes.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        List<String> firstNWordsFromSongThatOftenUsed = sortedSongWordsWithUsedTimes.keySet().stream().limit(count).collect(Collectors.toList());
        System.out.println("First " + count + " words, that repeats very often: " + firstNWordsFromSongThatOftenUsed);
    }
}
