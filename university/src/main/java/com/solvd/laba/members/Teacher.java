package com.solvd.laba.members;

import com.solvd.laba.enums.Regions;
import com.solvd.laba.members.Student;
import com.solvd.laba.exam.IExamStudents;
import com.solvd.laba.administrative.sections.Subject;
import com.solvd.laba.exam.IGiveResults;
import com.solvd.laba.result.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.function.DoublePredicate;
import java.util.function.Predicate;

public class Teacher extends Member implements IExamStudents, IGiveResults {
    private static final Logger LOGGER = LogManager.getLogger();
    private int rating;
    private ArrayList<Subject> currentlyAsignedSubjects;

    public Teacher(String name, int idNumber, int rating, Regions region) {
        super(name, idNumber, region);
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

    public void addSubject(Subject s) {
        if (s != null) {
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

    public void printSearchedStudents(Predicate<Student> predicate) {
        ArrayList<Student> toPrint = new ArrayList<Student>();
        for (Subject subject : this.currentlyAsignedSubjects) {
            ArrayList<Student> subjectList = subject.searchStudents(predicate);
            for (Student s : subjectList) {
                if (!toPrint.contains(s)) {
                    toPrint.add(s);
                }
            }
        }
        for (Student s : toPrint) {
            LOGGER.info(s.getName());
        }
    }

    public void printSearchedResults(DoublePredicate doublePredicate) {
        ArrayList<Result> toPrint = new ArrayList<>();
        for (Subject subject : this.currentlyAsignedSubjects) {
            toPrint.addAll(subject.searchResults(doublePredicate));
        }
        for (Result r : toPrint) {
            LOGGER.info(r.getStudent() + " get: " + r.getResult());
        }
    }

}
