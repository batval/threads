package com.batval.threads.models.comparator;

import com.batval.threads.models.restaurant.CashBox;

import java.util.Comparator;

public class NumberClientsCashBoxComparator implements Comparator<CashBox> {
    public int compare(CashBox cashBox1, CashBox cashBox2) {
        return cashBox1.getNumberOfClient().intValue()
                - cashBox2.getNumberOfClient().intValue();
    }
}
