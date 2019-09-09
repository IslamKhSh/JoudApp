package com.tremno.joudapp.Model;

import java.io.Serializable;

public class BranchInCityModel  implements Serializable {

    private String id = "";
    private String branch = "";

    public BranchInCityModel() {
    }

    public BranchInCityModel(String branch) {
        this.setBranch(branch);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
