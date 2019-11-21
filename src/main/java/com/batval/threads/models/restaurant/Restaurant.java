package com.batval.threads.models.restaurant;

import java.util.ArrayList;

public class Restaurant {

    private ArrayList<CashBox> cashBoxes;
    private int numberOfCashBoxes;

    public Restaurant(int numberOfCashBoxes) {
        this.numberOfCashBoxes = numberOfCashBoxes;
        this.cashBoxes = new ArrayList<CashBox>(numberOfCashBoxes);
    }

    public ArrayList<CashBox> getCashBoxes() {
        return cashBoxes;
    }

    public void addCashBox(CashBox cashBox){
        cashBoxes.add(cashBox);
    }

    public CashBox getCashBox (int idCashBox){
        return cashBoxes.get(idCashBox);
    }

    /* returns the number of desks in the restaurant */
    public int getNumberOfCashBoxes() {
        return numberOfCashBoxes;
    }









}
