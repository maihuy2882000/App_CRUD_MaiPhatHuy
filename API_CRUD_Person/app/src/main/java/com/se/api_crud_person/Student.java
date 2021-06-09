package com.se.api_crud_person;

public class Student {
    private int id;
    private String name;
    private String classroom;
    private String status;
    private String working;

    public Student(int id, String name, String classroom, String status, String working) {
        this.id = id;
        this.name = name;
        this.classroom = classroom;
        this.status = status;
        this.working = working;
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

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWorking() {
        return working;
    }

    public void setWorking(String working) {
        this.working = working;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classroom='" + classroom + '\'' +
                ", status='" + status + '\'' +
                ", working='" + working + '\'' +
                '}';
    }
}
