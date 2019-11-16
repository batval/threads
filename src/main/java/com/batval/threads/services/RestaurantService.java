package com.batval.threads.services;

import com.batval.threads.restaurant.CashBox;
import com.batval.threads.restaurant.Restaurant;

public class RestaurantService {

    //private Restaurant restaurant;

    private void initializeRestaurant(Restaurant restaurant) {
        for (int i = 0; i < restaurant.getNumberOfCashBoxes(); i++) {
           restaurant.addCashBox(new CashBox(i + 1));
        }
    }

    public CashBox useCashBox(Restaurant restaurant, int i) {
        restaurant.getCashBox(i).getNumberOfClient().getAndIncrement();
        return   restaurant.getCashBox(i);
    }


}
