package com.tremno.joudapp.Model;

import java.io.Serializable;

public class OfferModel implements Serializable {
    private String id = "";
    private String companyName = "";
    private String offer = "" ;
    private int image ;

    public OfferModel() {
    }

    public OfferModel(String companyName, String offer) {
        this.companyName = companyName;
        this.offer = offer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
