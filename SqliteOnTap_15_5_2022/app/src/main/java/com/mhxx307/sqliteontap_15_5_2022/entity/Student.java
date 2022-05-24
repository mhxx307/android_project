package com.mhxx307.sqliteontap_15_5_2022.entity;

public class Student {
    private int id;
    private String name;
    private String email;
    private int idClassroom;

    public Student() {
    }

    public Student(int id, String name, String email, int idClassroom) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.idClassroom = idClassroom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getIdClassroom() {
        return idClassroom;
    }

    public void setIdClassroom(int idClassroom) {
        this.idClassroom = idClassroom;
    }
}
