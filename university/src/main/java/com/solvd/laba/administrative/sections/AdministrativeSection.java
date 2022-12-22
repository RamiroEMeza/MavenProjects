package com.solvd.laba.administrative.sections;

import com.solvd.laba.cost.ICalculateCost;
import com.solvd.laba.members.Student;

import java.util.ArrayList;

public abstract class AdministrativeSection {
    private String name;
    private ICalculateCost cost;

    protected ArrayList<? extends AdministrativeSection> subDependencies;

    public AdministrativeSection(String name, ICalculateCost cost) {
        this.name = name;
        this.cost = cost;
    }

    public abstract int getQuantityOfStudents();

    public ArrayList<Student> getStudentsArrayList() {
        ArrayList<Student> noRepeatsList = new ArrayList<Student>();
        ArrayList<Student> aux = new ArrayList<Student>();
        for (AdministrativeSection admSect : this.subDependencies) {
            aux = admSect.getStudentsArrayList();
            aux.stream().filter(student -> !noRepeatsList.contains(student)).forEach(noRepeatsList::add);
        }
        return noRepeatsList;
    }

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
