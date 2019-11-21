package com.batval.threads.services;

import com.batval.threads.models.restaurant.*;

public class RestaurantService {

    private Restaurant restaurant;

    public RestaurantService( int numberCashBoxes){
        this.restaurant = new Restaurant(numberCashBoxes);
    }


//    private Restaurant restaurant;

    private void initializeRestaurant() {
        for (int i = 0; i < restaurant.getNumberOfCashBoxes(); i++) {

            restaurant.addCashBox(new CashBox(i + 1));
        }
   }

    public CashBox useCashBox( int i) {
        restaurant.getCashBox(i).getNumberOfClient().getAndIncrement();
        return restaurant.getCashBox(i);
    }


    public Restaurant getRestaurant() {
        return restaurant;
    }


}
