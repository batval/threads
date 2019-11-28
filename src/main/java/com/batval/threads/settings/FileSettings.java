package com.batval.threads.settings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileSettings {

    /**
     * Event Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(FileSettings.class);
    /**
     * Application settings file name
     */
    private static final String FILE_NAME = "src/main/resources/config1.properties";
    /**
     * Number of clients
     */
    private static int countClients;
    /**
     * Number of cash boxes
     */
    private static int countCashBoxes;

    public FileSettings() {
        loadSettings(FILE_NAME);
    }

    /**
     * Get the number of clients
     *
     * @return number of clients
     */
    public int getCountClients() {
        return countClients;
    }

    /**
     * Get the number of cash boxes
     *
     * @return number of cash boxes
     */
    public int getCountCashBoxes() {
        return countCashBoxes;
    }

    /**
     * Loading settings
     *
     * @param fileName settings file name
     */
    public static void loadSettings(String fileName) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(fileName)) {
            properties.load(input);
            countClients = Integer.parseInt(properties.getProperty("Clients"));
            countCashBoxes = Integer.parseInt(properties.getProperty("CashBoxes"));
        } catch (FileNotFoundException e) {
            logger.error("File " + fileName + " not found!");
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

}
