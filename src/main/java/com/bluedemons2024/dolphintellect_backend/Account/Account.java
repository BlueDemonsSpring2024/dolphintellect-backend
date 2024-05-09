package com.bluedemons2024.dolphintellect_backend.Account;
import jakarta.persistence.*;

@Entity(name="mysql")
@Table(name="accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String email;
    String password;

    @Column(name="student_id")
    String studentID;

    public Account(int id, String email, String password, String studentID) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.studentID = studentID;
    }

    public Account() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}
