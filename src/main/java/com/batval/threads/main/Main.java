package com.batval.threads.main;

import com.batval.threads.models.client.Client;
import com.batval.threads.models.restaurant.CashBox;
import com.batval.threads.models.restaurant.Restaurant;
import com.batval.threads.services.RestaurantService;

public class Main {
    public static void main(String[] args) {

        Restaurant restaurant = new Restaurant(5);
        System.out.println("Number of desks at the restaurant: " + 5);
        System.out.println("Number of customers at the restaurant: " + 30 + "\n");

        for (int i = 1; i <= 30; i++) {
            new RestaurantService(restaurant, new Client(i)).start();
        }

        for (int j = 1; j <= restaurant.getNumberOfCashBoxes(); j++) {
            System.out.println(new CashBox(j).getMoney());
        }
    }
}
