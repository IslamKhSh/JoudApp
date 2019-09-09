package com.tremno.joudapp.Model;

import java.io.Serializable;

public class StoresModel implements Serializable {
    private String id = "";
    private String stores = "";

    public StoresModel() {
    }

    public StoresModel(String stores) {
        this.setStores(stores);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStores() {
        return stores;
    }

    public void setStores(String stores) {
        this.stores = stores;
    }
}
