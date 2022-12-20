package com.solvd.laba.quizes;

import com.solvd.laba.evaluate.IEvaluate;
import com.solvd.laba.questions.MultipleChoise;
import com.solvd.laba.questions.TrueFalse;

import java.util.HashMap;
import java.util.Random;

public class Quiz {
    public static final Random RANDOM = new Random();
    private double percentageToAprove;
    public final int MAX_OPTIONS_MULT_CHOISE = 6;
    HashMap<String, IEvaluate> questionsMap;

    public Quiz(int quantityTrueFalse, int quantityMultipleChoise, double percentageToAprove) {
        this.questionsMap = new HashMap<String, IEvaluate>();
        this.setPercentageToAprove(percentageToAprove);
        this.createTrueFalse(quantityTrueFalse);
        this.createMultipleChoise(quantityMultipleChoise);
    }

    public void receiveAnswers(HashMap<String, String> answers) {
        for (String key : answers.keySet()) {
            this.questionsMap.get(key).setActualAnswer(answers.get(key));
        }
    }

    private void createTrueFalse(int quantityTrueFalse) {
        for (int i = 0; i < quantityTrueFalse; i++) {
            this.questionsMap.put("Question" + (questionsMap.size() + 1), new TrueFalse(Quiz.RANDOM.nextBoolean()));
        }
    }

    private void createMultipleChoise(int quantityMultipleChoise) {
        for (int i = 0; i < quantityMultipleChoise; i++) {
            this.questionsMap.put("Question" + (questionsMap.size() + 1),
                    new MultipleChoise((char) (Quiz.RANDOM.nextInt(MAX_OPTIONS_MULT_CHOISE) + 'a')));
        }
    }


    public final void setPercentageToAprove(double percentageToAprove) {
        if (percentageToAprove < 0) {
            percentageToAprove *= (-1);
        }
        while (percentageToAprove > 1) {
            percentageToAprove *= 0.1;
        }

        this.percentageToAprove = percentageToAprove;
    }

    public int getResult() {
        int result = 0;
        for (IEvaluate question :
                this.questionsMap.values()) {
            if (question.isCorrect()) {
                result++;
            }
        }
        return result;
    }

    public final boolean isAproved() {
        return this.getResult() >= (questionsMap.size() * percentageToAprove);
    }

    public final double getPercentageToAprove() {
        return percentageToAprove * 100;
    }

    public String getAcceptableAnswer(int index) {
        if (questionsMap.size() > 0 && index > 0 && index <= questionsMap.size()) {
            return this.questionsMap.get("Question" + index).acceptableAnswers();
        }
        return null;
    }


    public void clear() {
        for (IEvaluate iEvaluate : questionsMap.values()) {
            if (iEvaluate.acceptableAnswers().equals("character")) {
                iEvaluate.setActualAnswer("z");
            } else {
                iEvaluate.setActualAnswer(null);
            }
        }
    }
}
