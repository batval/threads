package com.batval.threads.models.restaurant;

import java.util.ArrayList;

public class Restaurant {

    private int numberOfCashBoxes;
    private ArrayList<CashBox> cashBoxes;

    public Restaurant (int numberOfCashBoxes) {
        this.numberOfCashBoxes = numberOfCashBoxes;
        this.cashBoxes =new ArrayList<>(numberOfCashBoxes);
        initializeRestaurant();
    }

    public ArrayList<CashBox> getCashBoxes() {
        return cashBoxes;
    }

    public void addCashBox(CashBox cashBox) {
        cashBoxes.add(cashBox);
    }

    public int getNumberOfCashBoxes() {
        return numberOfCashBoxes;
    }

    public CashBox useCashBox(int i) {
        cashBoxes.get(i).getNumberOfClients().getAndIncrement();
        return cashBoxes.get(i);
    }

    private void initializeRestaurant() {
        for (int i = 0; i < numberOfCashBoxes; i++) {
            cashBoxes.add(new CashBox(i + 1));
        }
    }

}
