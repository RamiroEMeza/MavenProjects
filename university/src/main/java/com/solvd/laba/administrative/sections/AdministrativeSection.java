package com.solvd.laba.administrative.sections;

import com.solvd.laba.cost.ICalculateCost;
import com.solvd.laba.members.Student;

import java.util.ArrayList;

public abstract class AdministrativeSection {
    private String name;
    private ICalculateCost cost;

    public AdministrativeSection(String name, ICalculateCost cost) {
        this.name = name;
        this.cost = cost;
    }

    public abstract int getQuantityOfStudents();

    public abstract ArrayList<Student> getStudentsArrayList();

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
