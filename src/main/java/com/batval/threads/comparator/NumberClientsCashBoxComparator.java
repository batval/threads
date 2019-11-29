package com.batval.threads.comparator;

import com.batval.threads.models.restaurant.CashBox;

import java.util.Comparator;

/**
 * Class for finding cash boxes with a minimum number of clients.
 *
 * @version 1.0
 * @autor Baturo Valery
 */
public class NumberClientsCashBoxComparator implements Comparator<CashBox> {


    /**
     * Sorts a collection of CashBox objects by the number of clients queuing for this cash box
     *
     * @param cashBox1 - first cash box
     * @param cashBox2 - second cash box
     * @return a numerical value - if it is negative, then cashBox1 precedes cashBox2, otherwise, vice versa. And if the method returns zero, then the objects are equal
     */
    public int compare(CashBox cashBox1, CashBox cashBox2) {
        return cashBox1.getNumberOfClients().intValue()
                - cashBox2.getNumberOfClients().intValue();
    }
}
