package com.batval.threads.services;

import com.batval.threads.models.client.Client;
import com.batval.threads.models.restaurant.CashBox;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CashBoxService {

    private CashBox cashBox;

    private Client client;

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public CashBoxService(Client client) {
        this.client = client;
    }


    public CashBox getCashBox() {
        return cashBox;
    }


    public boolean isCashBoxFree() {
        boolean isFree = lock2.tryLock();
        return isFree;
    }


    private void acceptOrder() {
        System.out.println("CashBox #" + cashBox.getIdCashBox() + ": accepting order from Client #"
                + client.getIdClient());
    }

    private void deliverOrder() {
        System.out.println("CashBox #" + cashBox.getIdCashBox() + ": delivering order to Client #"
                + client.getIdClient());
    }

    public void serviceClient(Client client) {
        try {
            lock1.lock();
            acceptOrder();
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(50));
            deliverOrder();
            cashBox.getNumberOfClient().getAndDecrement();

        } catch (InterruptedException ex) {
            System.out.println(ex.toString());
        } finally {
            lock1.unlock();
            lock2.unlock();
        }
    }

}
