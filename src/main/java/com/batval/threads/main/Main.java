package com.batval.threads.main;

import com.batval.threads.models.client.Client;
import com.batval.threads.models.restaurant.Restaurant;
import com.batval.threads.services.CashBoxService;
import com.batval.threads.services.ClientService;
import com.batval.threads.services.RestaurantService;

public class Main {
    public static void main(String[] args) {

        private RestaurantService restaurantService;
        private CashBoxService cashBoxService;
        private Client client;
        System.out.println("Initializing the restaurant...");
        Restaurant restaurant = new Restaurant(2);
        System.out.println("Number of desks at the restaurant: " + restaurant.getNumberOfCashBoxes());
        System.out.println("Number of customers at the restaurant: " + 10 + "\n");

        /* starting the Threads (allowing the Customers to enter the restaurant) */
        for (int i = 1; i <= 10; i++) {
            new ClientService(restaurantService,cashBoxService,client).start();
        }
    }
}
