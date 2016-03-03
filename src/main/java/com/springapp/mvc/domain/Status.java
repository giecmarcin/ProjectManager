package com.springapp.mvc.domain;

/**
 * Created by Marcin on 2015-09-29.
 */
public class Status {
    private String name;

    public Status() {
    }

    public Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
