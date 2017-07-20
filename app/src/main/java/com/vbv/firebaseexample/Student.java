package com.vbv.firebaseexample;


public class Student {
    String name;
    String roll;
    String id;



    public Student(String name, String roll, String id) {
        this.name = name;
        this.roll = roll;
        this.id = id;
    }

    public Student(String name, String roll) {
        this.name = name;
        this.roll = roll;
    }

    public Student(String name) {
        this.name = name;
    }







    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }
}
