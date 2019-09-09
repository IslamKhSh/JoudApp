package com.tremno.joudapp.Model;

import java.io.Serializable;

public class CityBranchModel implements Serializable
{
    private String id = "";
    private String city = "";

    public CityBranchModel() {
    }

    public CityBranchModel(String city) {
        this.setCity(city);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
