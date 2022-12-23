package com.solvd.laba.result;

public class Result {
    public final static boolean t = true;//just to prove reflections
    private String student;
    private String subject;
    private boolean approved;
    private int result;

    public Result() {
        this.student = null;
        this.subject = null;
        this.approved = false;
        this.result = 0;
    }

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

    public void setStudent(String student) {
        this.student = student;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setResult(int result) {
        this.result = result;
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
