package com.solvd.laba.members;

import com.solvd.laba.enums.Regions;

public class Administrative extends Member {
    private String workplace;

    public Administrative(String name, int idNumber, Regions region) {
        super(name, idNumber, region);
    }

    @Override
    public String getInfo() {
        return this.getName() + " id: " + this.getIdNumber() + " workplace: " + this.getWorkplace();
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }
}
