package com.batval.threads.main;

import com.batval.threads.models.client.Client;
import com.batval.threads.models.restaurant.CashBox;
import com.batval.threads.models.restaurant.Restaurant;
import com.batval.threads.services.RestaurantService;
import com.batval.threads.settings.FileSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Main application launch class.
 *
 * @version 1.0
 * Loading settings, initializing the restaurant, creating clients
 * @autor Baturo Valery
 */

public class StartRestaurant {
    /**
     * Event Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(StartRestaurant.class);

    public static void main(String[] args) throws InterruptedException {

        FileSettings fileSettings = new FileSettings();

        Restaurant restaurant = new Restaurant(fileSettings.getCountCashBoxes());
        logger.info("Number of CashBoxes at the restaurant: " + fileSettings.getCountCashBoxes());
        logger.info("Number of Clients at the restaurant: " + fileSettings.getCountClients() + "\n");

        for (int i = 1; i <= fileSettings.getCountClients(); i++) {
            new RestaurantService(restaurant, new Client(i), fileSettings.getCountCashBoxes()).start();
        }
        TimeUnit.MILLISECONDS.sleep(10000);

        logger.info(new CashBox(1).getMoney().toString());

    }
}
