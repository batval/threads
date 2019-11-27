package com.batval.threads.models.restaurant;

import com.batval.threads.models.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CashBox {
    /** Event Logger */
    private static final Logger logger = LoggerFactory.getLogger(CashBox.class);
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
            logger.info("Total amount of money at CashBox #" + cashBoxId + " is "
                    + money + " USD");
            numberOfClients.getAndDecrement();
        } catch (InterruptedException exception) {
            logger.error("Error. A thread was interrupted!");
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
        logger.info("CashBox #" + cashBoxId + ": accepting order from Client #"
                + client.getIdClient());
    }

    private void takeMoney(int amount, Client client) {
        logger.info("CashBox #" + cashBoxId + ": taking " + amount
                + " USD from Client #" + client.getIdClient());
        money.addAndGet(amount);
    }

    private void deliverOrder(Client client) {
        logger.info("CashBox #" + cashBoxId + ": delivering order to Client #"
                + client.getIdClient());
    }

}
