package util;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

public class FileUtil {

    public List<String> readFileFromResource(String fileName) throws URISyntaxException, IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            File file = new File(resource.toURI());
            return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        }
    }
}
