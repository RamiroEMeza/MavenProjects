package com.solvd.laba.university.questions;

import com.solvd.laba.evaluate.IEvaluate;

public class MultipleChoise implements IEvaluate {
    private char expectedAnswer;
    private char actualAnswer;

    public MultipleChoise(char expectedAnswer) {
        this.expectedAnswer = expectedAnswer;
    }

    @Override
    public boolean isCorrect() {
        return this.expectedAnswer == this.actualAnswer;
    }

    @Override
    public String acceptableAnswers() {
        return "character";
    }

    public void setActualAnswer(String actualAnswer) {
        this.actualAnswer = actualAnswer.charAt(0);
    }
}
