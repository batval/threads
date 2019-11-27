package com.batval.threads.models.restaurant;

import com.batval.threads.models.client.Client;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CashBox {

    private int cashBoxId;
    private AtomicInteger numberOfClients = new AtomicInteger();
    private static AtomicInteger money = new AtomicInteger();
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public CashBox(int cashBoxId) {
        this.cashBoxId = cashBoxId;
    }

    public int getCashBoxId() {
        return cashBoxId;
    }

    public AtomicInteger getNumberOfClients() {
        return numberOfClients;
    }


    public AtomicInteger getMoney() {
        return money;
    }


    public void serviceClient(Client client) {
        try {
            lock1.lock();

            acceptOrder(client);
            takeMoney(50, client);
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(100));
            deliverOrder(client);
            System.out.println("Total amount of money at CashBox #" + cashBoxId + " is "
                    + money + " USD");
            numberOfClients.getAndDecrement();
        } catch (InterruptedException exception) {
            System.out.println("Error. A thread was interrupted!");
        } finally {
            lock1.unlock();
            lock2.unlock();
        }
    }


    public boolean isCashBoxFree() {
        boolean isFree = lock2.tryLock();
        return isFree;
    }

    private void acceptOrder(Client client) {
        System.out.println("CashBox #" + cashBoxId + ": accepting order from Client #"
                + client.getIdClient());
    }

    private void takeMoney(int amount, Client client) {
        System.out.println("CashBox #" + cashBoxId + ": taking " + amount
                + " USD from Client #" + client.getIdClient());
        money.addAndGet(amount);
    }

    private void deliverOrder(Client client) {
        System.out.println("CashBox #" + cashBoxId + ": delivering order to Client #"
                + client.getIdClient());
    }

}
