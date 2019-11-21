package com.batval.threads.services;

import com.batval.threads.models.client.Client;
import com.batval.threads.models.comparator.NumberClientsCashBoxComparator;
import com.batval.threads.models.restaurant.CashBox;
import com.batval.threads.models.restaurant.Restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ClientService extends Thread {

    public static final int NUMBER_OF_DESKS = 2;


    private RestaurantService restaurantService;
    private CashBoxService cashBoxService;
    private Client client;

    public ClientService(RestaurantService restaurantService, CashBoxService cashBoxService, Client client){
        this.restaurantService = restaurantService;
        this.client = client;
        this.cashBoxService = cashBoxService;
    }


    private void occupyCashBox(CashBoxService cashBoxService) {
        /* using the desk to order food */
        cashBoxService.serviceClient(client);
        /* leaving the desk with the ordered food */
        System.out.println("Client #" + client.getIdClient() + ": leaving Desk #"
                + cashBoxService.getCashBox().getIdCashBox());
    }

    private int properCashBoxId() {
        ArrayList<CashBox> cashBoxesList = new ArrayList<CashBox>();
        cashBoxesList.addAll(restaurantService.getRestaurant().getCashBoxes());
        return Collections.min(cashBoxesList,
                new NumberClientsCashBoxComparator()).getIdCashBox();
    }


    private CashBox moveToNextCaxhBox(CashBox cashBox, int nextCashBoxId) {
        if (cashBox.getIdCashBox() != nextCashBoxId) {
            /* moving to the desk with fewer people in the queue */
            CashBox nextCashBox = restaurantService.useCashBox(nextCashBoxId - 1);
            System.out.println("Client #" + client.getIdClient() + ": moving to Desk #"
                    + nextCashBox.getIdCashBox());
            /* decreasing the number of people in the queue that was left */
            cashBox.getNumberOfClient().getAndDecrement();
            cashBox = nextCashBox;
        }
        return cashBox;
    }


    private void isInQueue(CashBoxService cashBoxService) throws InterruptedException {
        CashBox cashBox = cashBoxService.getCashBox();
        if (cashBoxService.isCashBoxFree()){
    occupyCashBox(cashBoxService);
}
else {
TimeUnit.MILLISECONDS.sleep(new Random().nextInt(50));
}
int nextCashBoxId=properCashBoxId();

        cashBox =moveToNextCaxhBox(cashBoxService.getCashBox(),nextCashBoxId);
isInQueue(cashBoxService);

}

public void run (){
    CashBox cashBox = cashBoxService.getCashBox();
    try {
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(50));
        cashBox =restaurantService.useCashBox(new Random().nextInt(NUMBER_OF_DESKS));
        System.out.println("Client #" + client.getIdClient() + " has chosen Desk #"
                + cashBox.getIdCashBox());

    }
    catch (InterruptedException ex)
    {
        System.out.println("A thread was interrupted! "+ex.toString());
    }
}
}
