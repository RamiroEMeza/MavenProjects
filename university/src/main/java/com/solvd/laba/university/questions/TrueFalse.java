package com.solvd.laba.university.questions;

import com.solvd.laba.evaluate.IEvaluate;

public final class TrueFalse implements IEvaluate {
    private boolean expectedAnswer;
    private boolean actualAnswer;

    public TrueFalse(boolean expectedAnswer) {
        this.expectedAnswer = expectedAnswer;
    }

    @Override
    public boolean isCorrect() {
        return this.expectedAnswer == this.actualAnswer;
    }

    @Override
    public String acceptableAnswers() {
        return "boolean";
    }

    public void setActualAnswer(String actualAnswer) {
        this.actualAnswer = Boolean.parseBoolean(actualAnswer);
    }
}
