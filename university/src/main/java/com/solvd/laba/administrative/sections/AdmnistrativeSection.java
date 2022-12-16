package com.solvd.laba.administrative.sections;

import com.solvd.laba.cost.ICalculateCost;

public abstract class AdmnistrativeSection {
    private String name;
    private ICalculateCost cost;

    public AdmnistrativeSection(String name, ICalculateCost cost) {
        this.name = name;
        this.cost = cost;
    }

    public abstract int getQuantityOfStudents();

    public final String getName() {
        return name;
    }

    public final int getBaseCost() {
        return cost.getCost();
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final void setCost(ICalculateCost cost) {
        this.cost = cost;
    }

}
