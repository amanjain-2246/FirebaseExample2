package com.vbv.firebaseexample;


public class Book {
    String name;
    String author;
    String price;
    String releasingDate;
    String id;

    public Book() {
    }

    public Book(String name, String author, String price, String releasingDate, String id) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.releasingDate = releasingDate;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReleasingDate() {
        return releasingDate;
    }

    public void setReleasingDate(String releasingDate) {
        this.releasingDate = releasingDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
