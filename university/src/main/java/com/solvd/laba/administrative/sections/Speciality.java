package com.solvd.laba.administrative.sections;

import com.solvd.laba.cost.ICalculateCost;
import com.solvd.laba.members.Student;

import java.util.ArrayList;
import java.util.Objects;

public class Speciality extends AdministrativeSection {
    private ArrayList<Subject> subjects;
    private int id;

    private boolean requiredEntranceQuiz;

    public Speciality(String name, int id, ICalculateCost cost) {
        super(name, cost);
        this.id = id;
        this.requiredEntranceQuiz = true;
        this.subjects = new ArrayList<Subject>();
        this.subDependencies = this.subjects;//doing this because I need two pointers, subDependencies is needed for
        // getStudentsArrayList() in AdministrativeSection, colleges is needed for the streams in some methods of Speciality
    }

    public int calculateCost() {
        int result = 0;
        for (Subject subject : subjects) {
            result += subject.getBaseCost() + subject.getExtraCharge();
        }
        return result + this.getBaseCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speciality that = (Speciality) o;
        if (this.hashCode() != that.hashCode()) return false;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getBaseCost(), that.getBaseCost());
    }

    public boolean haveSubjectByName(String subject) {
        for (Subject subj : this.subjects) {
            if (subj.getName().equals(subject)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBaseCost());
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "Name=" + getName() +
                "id=" + getId() +
                ", requiredEntranceQuiz=" + isRequiredEntranceQuiz() +
                '}';
    }

    public void addSubject(Subject subject) {
        if (!this.subjects.contains(subject)) {
            this.subjects.add(subject);
        }
    }

    public int getId() {
        return id;
    }

    public int getQuantitySubjects() {
        return this.subjects.size();
    }

    public void setRequiredEntranceQuiz(boolean requiredEntranceQuiz) {
        this.requiredEntranceQuiz = requiredEntranceQuiz;
    }

    @Override
    public ArrayList<? extends AdministrativeSection> getSubSections() {
        return new ArrayList<Subject>(this.subjects);
    }

    public ArrayList<String> getDetail() {
        ArrayList<String> response = new ArrayList<>();
        for (Subject subject : subjects) {
            response.add("~ " + subject.getName() + " Cost: " + subject.getBaseCost() + " Quality teacher charge:" +
                    subject.getExtraCharge() + " QuizQ: " + subject.getQuizes().size());
        }
        response.add("--Is enter exam required? " + this.isRequiredEntranceQuiz());
        return response;
    }

    public boolean isRequiredEntranceQuiz() {
        return requiredEntranceQuiz;
    }

    public void deleteSubjectByName(String subject) {
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getName().equals(subject)) {
                this.subjects.remove(i);
            }
        }
    }
}
