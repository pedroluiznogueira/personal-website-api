package com.personal.website.domain;

public class Details {

    private String name;
    private String email;
    private String info;

    public Details(String name, String email, String info) {
        this.name = name;
        this.email = email;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
