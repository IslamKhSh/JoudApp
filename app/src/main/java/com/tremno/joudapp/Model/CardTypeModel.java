package com.tremno.joudapp.Model;

import java.io.Serializable;

public class CardTypeModel implements Serializable {
    private int id = -1;
    private String name = "";
    private String price = "";

    public CardTypeModel() {
    }

    public CardTypeModel(String name,String price) {
        this.setName(name);
        this.setPrice(price);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
