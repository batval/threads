package com.batval.threads.main;

import com.batval.threads.models.client.Client;
import com.batval.threads.models.restaurant.CashBox;
import com.batval.threads.models.restaurant.Restaurant;
import com.batval.threads.services.RestaurantService;
import com.batval.threads.settings.FileSettings;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class StartRestaurantTest {
    private static final Logger logger = LoggerFactory.getLogger(StartRestaurant.class);
    @Test
    public void startRestaurant() throws InterruptedException {
        int expected=900;
        FileSettings fileSettings = new FileSettings();
        Restaurant restaurant = new Restaurant(fileSettings.getCountCashBoxes());
        logger.info("Number of CashBoxes at the restaurant: " + fileSettings.getCountCashBoxes());
        logger.info("Number of Clients at the restaurant: " + fileSettings.getCountClients() + "\n");
        for (int i = 1; i <= fileSettings.getCountClients(); i++) {
            new RestaurantService(restaurant, new Client(i),fileSettings.getCountCashBoxes()).start();
        }
        TimeUnit.MILLISECONDS.sleep(10000);
        logger.info(new CashBox(1).getMoney().toString());
        int actual =Integer.parseInt( new CashBox(1).getMoney().toString());
        assertEquals(expected,actual);
    }
}