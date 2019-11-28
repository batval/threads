package com.batval.threads.models.restaurant;

import java.util.ArrayList;

/**
 * Class restaurant
 * Initializes the cash box list
 *
 * @version 1.0
 * @autor Baturo Valery
 */
public class Restaurant {
    /**
     * Number of cash boxes in a restaurant
     */
    private int numberOfCashBoxes;
    /**
     * List of cash boxes
     */
    private ArrayList<CashBox> cashBoxes;

    /**
     * Constructor - creating a restaurant service
     *
     * @param numberOfCashBoxes - Number of cash boxes in a restaurant
     */
    public Restaurant(int numberOfCashBoxes) {
        this.numberOfCashBoxes = numberOfCashBoxes;
        this.cashBoxes = new ArrayList<>(numberOfCashBoxes);
        initializeRestaurant();
    }

    /**
     * Get a list of cash boxes
     *
     * @return  list of cash boxes
     */
    public ArrayList<CashBox> getCashBoxes() {
        return cashBoxes;
    }

    /**
     * Add cash box to the list
     *
     * @param cashBox - restaurant cash box
     */
    public void addCashBox(CashBox cashBox) {
        cashBoxes.add(cashBox);
    }

    /**
     * Get a number of cash boxes
     *
     * @return number of cash boxes
     */
    public int getNumberOfCashBoxes() {
        return numberOfCashBoxes;
    }

    /**
     * Get cash box from the list
     * @param i  - id of cash box
     * @return the used cash box
     */
    public CashBox useCashBox(int i) {
        cashBoxes.get(i).getNumberOfClients().getAndIncrement();
        return cashBoxes.get(i);
    }

    /**
     * Initialization of the restaurant, creation of cash boxes
     */
    private void initializeRestaurant() {
        for (int i = 0; i < numberOfCashBoxes; i++) {
            cashBoxes.add(new CashBox(i + 1));
        }
    }
}
