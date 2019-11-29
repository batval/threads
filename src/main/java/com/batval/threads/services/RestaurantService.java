package com.batval.threads.services;

import com.batval.threads.models.client.Client;
import com.batval.threads.comparator.*;
import com.batval.threads.models.restaurant.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Emulates customer behavior in a fast-food restaurant
 *
 * @version 1.0
 * @autor Baturo Valery
 */
public class RestaurantService extends Thread {
    /**
     * Event Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(RestaurantService.class);
    /**
     * Restaurant where cash boxes are generated
     */
    private Restaurant restaurant;
    /**
     * The client who went to the restaurant
     */
    private Client client;

    /**
     * Number of cash boxes in a restaurant
     */
    private int countCashBoxes;

    /**
     * Constructor - creating a restaurant service
     *
     * @param restaurant     - restaurant
     * @param client         - client of the restaurant
     * @param countCashBoxes - cash boxes of the restaurant
     */
    public RestaurantService(Restaurant restaurant, Client client, int countCashBoxes) {
        this.client = client;
        this.restaurant = restaurant;
        this.countCashBoxes = countCashBoxes;
    }

    /**
     * The main method to start the thread
     * Client randomly selects a cash box and queues
     */
    public void run() {
        CashBox cashBox;
        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(20));
            cashBox = restaurant.useCashBox(new Random().nextInt(countCashBoxes));
            logger.info("Client #" + client.getIdClient() + " has chosen CashBox #"
                    + cashBox.getCashBoxId());
            stayingInQueue(cashBox);
        } catch (InterruptedException exception) {
            logger.error("Error. A thread was interrupted!");
        }
    }

    /**
     * Queuing a client at the cash box
     * If the cash desk is free, then the client takes it,
     * otherwise it becomes a queue.
     * If there is a cash box with fewer clients, he goes there.
     *
     * @param cashBox - cash box of the restaurant where the client is serve
     * @throws InterruptedException -  Thrown when a thread is waiting, sleeping, or otherwise occupied,
     *                              and the thread is interrupted, either before or during the activity.
     */
    private void stayingInQueue(CashBox cashBox) throws InterruptedException {
        if (cashBox.isCashBoxFree()) {
            occupyCashBox(cashBox);
        } else {
            logger.info("Client #" + client.getIdClient() + " in the queue at the CashBox #" + cashBox.getCashBoxId());
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(50));
            int nextCashBoxkId = properCashBoxId();
            cashBox = moveToNextCashBox(cashBox, nextCashBoxkId);
            stayingInQueue(cashBox);
        }
    }

    /**
     * Queuing a client at the cash box
     * If the cash desk is free, then the client takes it,
     * otherwise it becomes a queue.
     * If there is a cash box with fewer clients, he goes there.
     *
     * @return id of the cash box with the fewest clients in the queue
     */
    private int properCashBoxId() {
        ArrayList<CashBox> deskList = new ArrayList<CashBox>();
        deskList.addAll(restaurant.getCashBoxes());
        return Collections.min(deskList,
                new NumberClientsCashBoxComparator()).getCashBoxId();
    }

    /**
     * The client takes the cash desk, is serviced and leaves it.
     *
     * @param cashBox - cash box of the restaurant where the client is serve
     */
    private void occupyCashBox(CashBox cashBox) {
        cashBox.serviceClient(client);
        logger.info("Client #" + client.getIdClient() + ": leaving CashBox #"
                + cashBox.getCashBoxId());
    }

    /**
     * Transfer to another cash box.
     *
     * @param cashBox       - cash box of the restaurant where the client is serve
     * @param nextCashBoxId - id of the next cash box
     * @return cash box to which the client switched
     */
    private CashBox moveToNextCashBox(CashBox cashBox, int nextCashBoxId) {
        if (cashBox.getCashBoxId() != nextCashBoxId) {
            CashBox nextCashBox = restaurant.useCashBox(nextCashBoxId - 1);
            logger.info("Client #" + client.getIdClient() + ": moving to CashBox #"
                    + nextCashBox.getCashBoxId());
            cashBox.getNumberOfClients().getAndDecrement();
            cashBox = nextCashBox;
        }
        return cashBox;
    }
}
