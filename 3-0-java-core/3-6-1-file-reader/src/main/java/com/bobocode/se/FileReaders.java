package com.bobocode.se;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@link FileReaders} provides an API that allow to read whole file into a {@link String} by file name.
 */
public class FileReaders {
    private static final String DELIMITER = "\n";

    /**
     * Returns a {@link String} that contains whole text from the file specified by name.
     *
     * @param fileName a name of a text file
     * @return string that holds whole file content
     */
    public static String readWholeFile(String fileName) {
        try {
            URI resource = getResource(fileName);
            try (Stream<String> lines = Files.lines(Paths.get(resource))) {
                return lines.collect(Collectors.joining(DELIMITER));
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file: " + fileName, e);
        }
    }

    private static URI getResource(String fileName) {
        try {
            URL resource = FileReaders.class.getClassLoader().getResource(fileName);
            return resource.toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
