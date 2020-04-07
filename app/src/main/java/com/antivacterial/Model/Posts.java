package com.antivacterial.Model;

public class Posts {

    private int id;
    private String name;
    private String description;
    private int times;

    public Posts(String description) {
        //this.name = name;
        this.description = description;
        //this.times = times;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getTimes() {
        return times;
    }

    /*
    private int id;
    private String title;

    public Posts(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }*/
}
