package com.solvd.laba.university.members;

import com.solvd.laba.university.administrative.sections.Subject;
import com.solvd.laba.university.quizes.Quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Student extends Member {
    public static final Random RANDOM = new Random();
    private ArrayList<Subject> aprobbedSubjects;
    private ArrayList<Subject> historicEnrolledSubjects;

    public Student(String name, int idNumber) {
        super(name, idNumber);
        this.aprobbedSubjects = new ArrayList<Subject>();
        this.historicEnrolledSubjects = new ArrayList<Subject>();
    }

    @Override
    public String getInfo() {
        return this.getName() + " is a " + this.getClass() + " Subjects approbed: " + this.aprobbedSubjects.size();
    }

    public HashMap<String, String> answerQuiz(Quiz quiz) {
        HashMap<String, String> answers = new HashMap<>();
        int index = 1;
        String possibleAnswuer = "possibleAnswuer";
        while (possibleAnswuer != null) {
            possibleAnswuer = quiz.getAcceptableAnswer(index);
            if (possibleAnswuer == null) {
                break;
            } else if (possibleAnswuer.equals("boolean")) {
                answers.put(("Question" + index),
                        String.valueOf(Quiz.RANDOM.nextBoolean()));
            } else if (possibleAnswuer.equals("character")) {
                answers.put(("Question" + index),
                        String.valueOf((char) (Student.RANDOM.nextInt(quiz.MAX_OPTIONS_MULT_CHOISE) + 'a')));
            }
            index++;
        }
        return answers;
    }


}
