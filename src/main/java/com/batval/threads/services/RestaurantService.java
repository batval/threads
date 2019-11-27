package com.batval.threads.services;

import com.batval.threads.models.client.Client;
import com.batval.threads.models.comparator.NumberClientsCashBoxComparator;
import com.batval.threads.models.restaurant.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RestaurantService extends Thread {


    private Restaurant restaurant;
    private Client client;

    public RestaurantService(Restaurant restaurant, Client client) {
        this.client = client;
        this.restaurant = restaurant;
    }


    public void run() {

        CashBox cashBox;

        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(20));
            cashBox = restaurant.useCashBox(new Random().nextInt(5));
            System.out.println("Client #" + client.getIdClient() + " has chosen CashBox #"
                    + cashBox.getCashBoxId());
            stayingInQueue(cashBox);
        } catch (InterruptedException exception) {
            System.out.println("Error. A thread was interrupted!");
        }
    }

    private void stayingInQueue(CashBox cashBox) throws InterruptedException {
        if (cashBox.isCashBoxFree()) {
            occupyCashBox(cashBox);
        } else {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(50));
            int nextCashBoxkId = properCashBoxId();
            cashBox = moveToNextCashBox(cashBox, nextCashBoxkId);
            stayingInQueue(cashBox);
        }
    }

    private int properCashBoxId() {
        ArrayList<CashBox> deskList = new ArrayList<CashBox>();
        deskList.addAll(restaurant.getCashBoxes());
        return Collections.min(deskList,
                new NumberClientsCashBoxComparator()).getCashBoxId();
    }

    private void occupyCashBox(CashBox cashBox) {
        cashBox.serviceClient(client);
        System.out.println("Client #" + client.getIdClient() + ": leaving CashBox #"
                + cashBox.getCashBoxId());
    }

    private CashBox moveToNextCashBox(CashBox cashBox, int nextCashBoxId) {
        if (cashBox.getCashBoxId() != nextCashBoxId) {
            CashBox nextCashBox = restaurant.useCashBox(nextCashBoxId - 1);
            System.out.println("Client #" + client.getIdClient() + ": moving to CashBox #"
                    + nextCashBox.getCashBoxId());
            cashBox.getNumberOfClients().getAndDecrement();
            cashBox = nextCashBox;
        }
        return cashBox;
    }
}
