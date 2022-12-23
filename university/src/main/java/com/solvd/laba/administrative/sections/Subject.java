package com.solvd.laba.administrative.sections;

import com.solvd.laba.cost.ICalculateCost;
import com.solvd.laba.custom.linked.list.CustomLinkedList;
import com.solvd.laba.result.Result;
import com.solvd.laba.members.Student;
import com.solvd.laba.members.Teacher;
import com.solvd.laba.quizes.Quiz;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.function.DoublePredicate;

public class Subject extends AdministrativeSection {
    private static final Logger LOGGER = LogManager.getLogger();
    private int hours;
    private ArrayList<Teacher> teachers;
    private ArrayList<Quiz> quizes;
    private ArrayList<Student> students;

    private ArrayList<Result> results;

    private CustomLinkedList<Result> backupResults;

    public Subject(String name, int hours, Teacher teacher, Quiz quiz, ICalculateCost cost) {
        super(name, cost);
        this.hours = hours;
        this.teachers = new ArrayList<>();
        this.addTeacher(teacher);
        this.quizes = new ArrayList<>();
        this.addQuiz(quiz);
        this.students = new ArrayList<>();
        this.results = new ArrayList<>();
        this.backupResults = new CustomLinkedList<>();
        teacher.addSubject(this);
    }

    public void addQuiz(Quiz quiz) {
        this.quizes.add(quiz);
    }

    public void removeQuiz(int index) {
        if (this.quizes.size() > 1) {
            this.quizes.remove(index);
        }
    }

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    public void removeTeacher(int index) {
        if (this.teachers.size() > 1) {
            this.teachers.remove(index);
        }
    }

    public void addStudent(Student student) {
        if (!this.students.contains(student)) {
            this.students.add(student);
        }
    }

    public void removeStudent(int index) {
        this.students.remove(index);
    }

    @Override
    public int getQuantityOfStudents() {
        return this.students.size();
    }

    @Override
    public ArrayList<Student> getStudentsArrayList() {
        return new ArrayList<Student>(this.students);
    }

    public ArrayList<Quiz> getQuizes() {
        return new ArrayList<Quiz>(this.quizes);
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }


    public int getExtraCharge() {
        AtomicInteger result = new AtomicInteger();
        teachers.stream().forEach(t -> result.addAndGet(t.getRating()));
        return result.get() * this.getBaseCost();
    }

    public void exam() {
        for (Student s : this.students) {//ask each student
            for (Quiz q : this.quizes) {//gives the student each quiz
                HashMap<String, String> answer = new HashMap<>();
                answer = s.answerQuiz(q);//student answer the quiz
                q.receiveAnswers(answer);//the quiz receives the answers
                this.results.add(new Result(s.getName(), this.getName(), q.isAproved(), q.getResult()));
                if (q.isAproved()) {
                    s.addApprobedSubject(this);
                }
                this.backupResults.insertFirst(new Result(s.getName(), this.getName(), q.isAproved(), q.getResult()));
                q.clear();
            }
        }
    }

    public ArrayList<String> getResults() {
        ArrayList<String> results = new ArrayList<String>();
        this.results.forEach(res -> results.add(res.toString()));
        return results;
    }

    public void displayBackupResults() {
        this.backupResults.display();
    }

    public ArrayList<Student> searchStudents(Predicate<Student> predicate) {
        ArrayList<Student> result = new ArrayList<Student>();
        this.students.stream().filter(predicate).forEach(result::add);
        return result;
    }

    public ArrayList<Result> searchResults(DoublePredicate doublePredicate) {
        ArrayList<Result> response = new ArrayList<>();
        this.results.stream().filter(result -> doublePredicate.test(result.getResult())).forEach(response::add);
//        for (Result r : this.results) {
//            if (doublePredicate.test(r.getResult())) {
//                response.add(r);
//            }
//        }
        return response;
    }

    public void printStudents(Predicate<Student> predicate) {
        ArrayList<Student> toPrint = new ArrayList<Student>();
        toPrint = this.searchStudents(predicate);
        for (Student s : toPrint) {
            LOGGER.info(s.getName());
        }
    }
}
