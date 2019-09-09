package com.tremno.joudapp.Model;

import java.io.Serializable;

public class VouchersModel implements Serializable {
    private String id = "";
    private String vouchersDetail = "" ;
    private int image ;

    public VouchersModel() {
    }

    public VouchersModel(String vouchersDetail) {
        this.setVouchersDetail(vouchersDetail);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVouchersDetail() {
        return vouchersDetail;
    }

    public void setVouchersDetail(String vouchersDetail) {
        this.vouchersDetail = vouchersDetail;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
