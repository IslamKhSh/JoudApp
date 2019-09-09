package com.tremno.joudapp.Model;

import java.io.Serializable;

public class VoucherssCategoriesModel implements Serializable {
    private String id = "";
    private String Voucher = "";

    public VoucherssCategoriesModel() {
    }

    public VoucherssCategoriesModel(String Voucher) {
        this.setVoucher(Voucher);

    }


    public String getVoucher() {
        return Voucher;
    }

    public void setVoucher(String voucher) {
        Voucher = voucher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}