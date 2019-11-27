package com.batval.threads.main;

import com.batval.threads.models.client.Client;
import com.batval.threads.models.restaurant.CashBox;
import com.batval.threads.models.restaurant.Restaurant;
import com.batval.threads.services.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    /** Event Logger */
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Restaurant restaurant = new Restaurant(5);
        logger.info("Number of CashBoxes at the restaurant: " + 5);
        logger.info("Number of Clients at the restaurant: " + 30 + "\n");

        for (int i = 1; i <= 30; i++) {
            new RestaurantService(restaurant, new Client(i)).start();
        }

        for (int j = 1; j <= restaurant.getNumberOfCashBoxes(); j++) {
            logger.info(new CashBox(j).getMoney().toString());
        }
    }
}
