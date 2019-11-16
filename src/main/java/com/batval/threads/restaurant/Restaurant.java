package com.batval.threads.restaurant;

import java.util.ArrayList;

public class Restaurant {

    private final ArrayList<CashBox> cashBoxes;
    private int numberOfCashBoxes;
    private  CashBox cashBox;

    public Restaurant(int numberOfCashBoxes){
this.numberOfCashBoxes = numberOfCashBoxes;
this.cashBoxes =new ArrayList<CashBox>(numberOfCashBoxes);
    }

public int getNumberOfCashBoxes (){
        return numberOfCashBoxes;
}

public ArrayList<CashBox> getCashBoxes() {
        return cashBoxes;
}

public void addCashBox (CashBox cashBox){
        cashBoxes.add(cashBox);
}

public CashBox getCashBox(int i)
{
    return cashBoxes.get(i);
}

}
