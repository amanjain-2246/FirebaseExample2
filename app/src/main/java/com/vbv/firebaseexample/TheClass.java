package com.vbv.firebaseexample;


public class TheClass {
    String subject;
    Student stud;

    public TheClass() {
    }

    public TheClass(String subject, Student stud) {
        this.subject = subject;
        this.stud = stud;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Student getStud() {
        return stud;
    }

    public void setStud(Student stud) {
        this.stud = stud;
    }
}
