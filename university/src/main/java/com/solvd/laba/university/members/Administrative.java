package com.solvd.laba.university.members;

public class Administrative extends Member {
    private String workplace;

    public Administrative(String name, int idNumber) {
        super(name, idNumber);
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
