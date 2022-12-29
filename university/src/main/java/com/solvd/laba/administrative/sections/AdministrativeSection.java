package com.solvd.laba.administrative.sections;

import com.solvd.laba.cost.ICalculateCost;
import com.solvd.laba.members.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class AdministrativeSection {
    private String name;
    private ICalculateCost cost;

    protected ArrayList<? extends AdministrativeSection> subDependencies;

    public AdministrativeSection(String name, ICalculateCost cost) {
        this.name = name;
        this.cost = cost;
    }

    public ArrayList<Student> getStudentsArrayList() {
        Set<Student> set = new HashSet<Student>();
        for (AdministrativeSection admSect : getSubSections()) {
            set.addAll(admSect.getStudentsArrayList());
        }
        return new ArrayList<Student>(set);
    }

    public abstract ArrayList<? extends AdministrativeSection> getSubSections();

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
