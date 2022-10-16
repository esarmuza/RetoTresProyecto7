package com.example.backend.modelopersonal;

import com.example.backend.modelo.Client;

public class CountClient {
    private long total;
    private Client client;

    public CountClient(long total, Client client) {
        this.total = total;
        this.client = client;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
