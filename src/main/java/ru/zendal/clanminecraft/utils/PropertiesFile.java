package ru.zendal.clanminecraft.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertiesFile {


    private Map<String, String> storageProperties = new HashMap<>();

    public PropertiesFile(InputStream inputStream) throws IOException {
        this.prepareList(IOUtils.readLines(inputStream, "UTF-8"));
    }


    private void prepareList(List<String> lines) throws IOException {
        for (String line : lines) {
            String[] preparedLine = line.split("=", 2);
            if (preparedLine.length != 2) {
                throw new IOException("Invalid line: " + line);
            }
            storageProperties.put(preparedLine[0], preparedLine[1]);
        }
    }

    public String get(String property) {
        return storageProperties.get(property);
    }

}
