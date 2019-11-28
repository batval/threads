package com.batval.threads.models.client;

/**
 * Client class in a restaurant
 *
 * @version 1.0
 * @autor Baturo Valery
 */
public class Client {

    /**
     * Сlient id
     */
    private int idClient;
    /**
     * Constructor - creating a client
     *
     * @param idClient - client id
     */
    public Client(int idClient) {
        this.idClient = idClient;
    }
    /**
     * Get client id
     *
     * @return  idClient - client id
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * Set client id
     *
     * @param idClient - client id
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }


}
