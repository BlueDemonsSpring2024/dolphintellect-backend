//package com.daveleoshilander.springpractice.Account;
//
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "accounts")
//public class Account {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    int id;
//
//    String email;
//    String password;
//
//    @Column(name = "student_id")
//    long studentID;
//
//
//    public Account(String email, String password, long studentID) {
//        this.email = email;
//        this.password = password;
//        this.studentID = studentID;
//    }
//
//    public Account() {
//
//    }
//
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public long getStudentID() {
//        return studentID;
//    }
//
//    public void setStudentID(long studentID) {
//        this.studentID = studentID;
//    }
//
//
//
//
//
//
//
//}
