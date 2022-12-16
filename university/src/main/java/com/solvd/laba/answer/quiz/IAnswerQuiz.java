package com.solvd.laba.answer.quiz;

import com.solvd.laba.quizes.Quiz;

import java.util.HashMap;

public interface IAnswerQuiz {
    public abstract HashMap<String, String> answerQuiz(Quiz quiz);
}
