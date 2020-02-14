package ru.zendal.clanminecraft.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Interface for working with properties files
 */
public class PropertiesFile {

    /**
     * Storage of properties
     */
    private Map<String, String> storageProperties = new HashMap<>();

    /**
     * Constructor
     *
     * @param inputStream stream to file.
     * @throws IOException when can't get or parse file.
     */
    public PropertiesFile(InputStream inputStream) throws IOException {
        this.prepareList(IOUtils.readLines(inputStream, "UTF-8"));
    }

    /**
     * Prepare list with lines
     *
     * @param lines lines with properties
     * @throws IOException when property file has invalid format
     */
    private void prepareList(List<String> lines) throws IOException {
        for (String line : lines) {
            if (line.startsWith("#") || line.length() <= 1) {
                continue;
            }
            String[] preparedLine = line.split("=", 2);
            if (preparedLine.length != 2) {
                throw new IOException("Invalid line: " + line);
            }
            storageProperties.put(preparedLine[0], preparedLine[1]);
        }
    }

    /**
     * Get property
     *
     * @param property name of property
     * @return value
     */
    public String get(String property) {
        return storageProperties.get(property);
    }

}
