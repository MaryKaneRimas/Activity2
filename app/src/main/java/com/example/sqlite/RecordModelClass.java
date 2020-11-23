package com.example.sqlite;

public class RecordModelClass {

    Integer id;
    String studentid;
    String fullname;
    String address;

    public RecordModelClass(String studentid, String fullname, String address) {
        this.studentid = studentid;
        this.fullname = fullname;
        this.address = address;
    }

    public RecordModelClass(Integer id, String studentid, String fullname, String address) {
        this.id = id;
        this.studentid = studentid;
        this.fullname = fullname;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
