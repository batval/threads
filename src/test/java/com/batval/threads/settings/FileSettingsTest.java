package com.batval.threads.settings;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.*;

public class FileSettingsTest {

    private static final String FILE_NAME = "src/main/resources/config1.properties";
    private static final Logger logger = LoggerFactory.getLogger(FileSettingsTest.class);
    @Test
    public void loadSettings() {

        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(FILE_NAME)) {
            properties.load(input);
            Assert.assertNotNull(input); }

          catch (FileNotFoundException e) {
            logger.error("File " + FILE_NAME + " not found!");
        } catch (IOException e) {
            logger.error(e.toString());
        }

    }
}