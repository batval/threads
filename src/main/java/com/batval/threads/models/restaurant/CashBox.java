package com.batval.threads.models.restaurant;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CashBox {

    private int idCashBox;
    private AtomicInteger numberOfClient;
   // private Lock lock1 = new ReentrantLock();
   // private Lock lock2 = new ReentrantLock();

    public CashBox(int idCashBox) {
        this.idCashBox = idCashBox;
    }

    public int getIdCashBox() {
        return idCashBox;
    }

    public void setIdCashBox(int idCashBox) {
        this.idCashBox = idCashBox;
    }

    public AtomicInteger getNumberOfClient() {
        return numberOfClient;
    }

    public void setNumberOfClient(AtomicInteger numberOfClient){
        this.numberOfClient = numberOfClient;
    }
}
