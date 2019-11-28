package com.batval.threads.models.restaurant;

import com.batval.threads.models.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Class Cash box
 *
 * @version 1.0
 * @autor Baturo Valery
 */
public class CashBox {
    /** Event Logger */
    private static final Logger logger = LoggerFactory.getLogger(CashBox.class);
    /**
     * Id of cash box
     */
    private int cashBoxId;
    /**
     * Current number of clients
     */
    private AtomicInteger numberOfClients = new AtomicInteger();
    /**
     * How much money is received
     */
    private static AtomicInteger money = new AtomicInteger();
    /**
     * Locks are used to control access to a shared resource (cash boxes) as an alternative to the synchronized statement.
     * Initially, the thread tries to access the shared resource (cash box). If it is free, then it locks on it.
     * After completion of the operation, the lock is removed from the shared resource (cash box).
     * If the resource (cash box) is not free and a lock is already imposed on it, then the thread waits until this lock is released.
     */
    private Lock lock1 = new ReentrantLock();
    /**
     * Lock to check if the resource is free (cash box)
     */
    private Lock lock2 = new ReentrantLock();

    /**
     * Constructor - creating a cash box
     *
     * @param cashBoxId - id of the cash box
     */
    public CashBox(int cashBoxId) {
        this.cashBoxId = cashBoxId;
    }

    /**
     * Get id of the cash box
     *
     * @return  cashBoxId - id of the cash box
     */
    public int getCashBoxId() {
        return cashBoxId;
    }

    /**
     *Get the number of clients in queue at the cash box
     *
     * @return  the number of clients staying in a queue to the cash box
     */
    public AtomicInteger getNumberOfClients() {
        return numberOfClients;
    }

    /**
     * Find out how much money a restaurant earns
     *
     * @return   earned money
     */
    public AtomicInteger getMoney() {
        return money;
    }

    /**
     * Client service (one client at a time).
     * Take the order, receive money from the client, wait for the order to be completed, receive the order.
     * Calculate the amount of money earned.
     * Reduce the number of clients in line at the cash box.
     *
     * @param client - client serviced at the cash box
     */
    public void serviceClient(Client client) {
        try {
            lock1.lock();
            acceptOrder(client);
            takeMoney(30, client);
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

    /**
     * Checks whether the current cash box is free
     * Tries to get a lock.
     * @return - if a lock is received, it returns true. If the lock is not received, then returns false.
     */
    public boolean isCashBoxFree() {
        boolean isFree = lock2.tryLock();
        return isFree;
    }


    /**
     * Accepts the client's order
     * @param client -client who makes an order
     */
    private void acceptOrder(Client client) {
        logger.info("CashBox #" + cashBoxId + ": accepting order from Client #"
                + client.getIdClient());
    }

    /**
     * Accept money from a client
     * @param amount -money
     * @param client -client who makes an order
     */
    private void takeMoney(int amount, Client client) {
        logger.info("CashBox #" + cashBoxId + ": taking " + amount
                + " USD from Client #" + client.getIdClient());
        money.addAndGet(amount);
    }

    /**
     * Deliver the client's order
     * @param client -client who makes an order
     */
    private void deliverOrder(Client client) {
        logger.info("CashBox #" + cashBoxId + ": delivering order to Client #"
                + client.getIdClient());
    }

}
