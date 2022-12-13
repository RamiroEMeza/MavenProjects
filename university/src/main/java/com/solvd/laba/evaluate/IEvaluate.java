package com.solvd.laba.evaluate;

public interface IEvaluate {
    public abstract boolean isCorrect();

    public abstract String acceptableAnswers();

    public abstract void setActualAnswer(String answer);


}
