package com.solvd.laba.result;

public class Result {
    private String student;
    private String subject;
    private boolean approved;
    private int result;

    public Result(String student, String subject, boolean approved, int result) {
        this.student = student;
        this.subject = subject;
        this.approved = approved;
        this.result = result;
    }

    public String getStudent() {
        return student;
    }

    public String getSubject() {
        return subject;
    }

    public boolean isApproved() {
        return approved;
    }

    public int getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "student=" + student +
                ", subject=" + subject +
                ", approved=" + approved +
                ", result=" + result +
                '}';
    }
}
