package com.bobocode.se;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * {@link FileStats} provides an API that allow to get character statistic based on text file. All whitespace characters
 * are ignored.
 */
public class FileStats {
    private final Map<Character, Long> characterCountMap;
    private final char mostFrequentCharacter;

    /**
     * Creates a new immutable {@link FileStats} objects using data from text file received as a parameter.
     *
     * @param fileName input text file name
     * @return new FileStats object created from text file
     */
    public static FileStats from(String fileName) {
        return new FileStats(fileName);
    }

    private FileStats(String filename) {
        Path path = findFile(filename);
        Stream<String> fileStream = openFileStream(path);
        Stream<Character> characterStream = transformStringStreamToCharacterStream(fileStream);
        characterCountMap = computeCharacterCountMap(characterStream);
        mostFrequentCharacter = getMostPopularCharacter();
    }

    /**
     * Returns a number of occurrences of the particular character.
     *
     * @param character a specific character
     * @return a number that shows how many times this character appeared in a text file
     */
    public int getCharCount(char character) {
        return characterCountMap.get(character)
                .intValue();
    }

    /**
     * Returns a character that appeared most often in the text.
     *
     * @return the most frequently appeared character
     */
    public char getMostPopularCharacter() {
         return characterCountMap.entrySet()
                 .stream()
                 .max(Map.Entry.comparingByValue())
                 .get()
                 .getKey();
    }

    /**
     * Returns {@code true} if this character has appeared in the text, and {@code false} otherwise
     *
     * @param character a specific character to check
     * @return {@code true} if this character has appeared in the text, and {@code false} otherwise
     */
    public boolean containsCharacter(char character) {
        return characterCountMap.containsKey(character);
    }

    private Path findFile(String fileName) {
        try {
            URL resource = FileStats.class.getClassLoader().getResource(fileName);
            return Path.of(resource.toURI());
        } catch (Exception e) {
            throw new FileStatsException("Cannot find resource: " + fileName);
        }
    }

    @SneakyThrows
    private Stream<String> openFileStream(Path path) {
        try {
            return Files.lines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Stream<Character> transformStringStreamToCharacterStream(Stream<String> fileStream) {
        return fileStream
                .flatMap(line -> line.chars().mapToObj(c -> (char) c));
    }

    private Map<Character, Long> computeCharacterCountMap(Stream<Character> characterStream) {
        return characterStream
                .filter(character -> character != 32)
                .collect(groupingBy(Function.identity(), counting()));
    }
}
