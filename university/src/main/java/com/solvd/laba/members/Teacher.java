package com.solvd.laba.members;

import com.solvd.laba.exam.IExamStudents;
import com.solvd.laba.administrative.sections.Subject;
import com.solvd.laba.exam.IGiveResults;

import java.util.ArrayList;

public class Teacher extends Member implements IExamStudents, IGiveResults {
    private int rating;
    private ArrayList<Subject> currentlyAsignedSubjects;

    public Teacher(String name, int idNumber, int rating) {
        super(name, idNumber);
        this.setRating(rating);
        this.currentlyAsignedSubjects = new ArrayList<Subject>();
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating < 0) {
            rating *= (-1);
        }
        this.rating = rating;
    }

    public void addSubject(Subject s){
        if (s != null){
            this.currentlyAsignedSubjects.add(s);
        }
    }

    @Override
    public String getInfo() {
        return this.getName() + " is a " + this.getClass() + " Teaches in " +
                this.currentlyAsignedSubjects.size() + " classes";
    }

    @Override
    public void ExamStudents() {
        for (Subject subject : this.currentlyAsignedSubjects) {
            subject.Exam();

        }
    }

    @Override
    public ArrayList<String> giveResults() {
        ArrayList<String> results = new ArrayList<String>();
        for (Subject subject : this.currentlyAsignedSubjects) {
            results.addAll(subject.getResults());
        }
        return results;
    }
    @Override
    public void giveBackupResults() {
        for (Subject subject : this.currentlyAsignedSubjects) {
            subject.displayBackupResults();
        }
    }
}
