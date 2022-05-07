package com.example.easel_new.database;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @NotEmpty(message = "User's name cannot be empty.")
    @Size(min = 5, max= 250)
    private String firstName;

    @NotEmpty(message = "User's name cannot be empty.")
    @Size(min = 5, max= 250)
    private String lastName;

    @NotEmpty(message = "User's email cannot be empty.")
    private String username;

    @NotEmpty(message = "Password cannot be empty.")
    private String password;


    private Boolean isProf;
    @OneToMany
    private List<Course> courses;

    public User() {

    }

    public User(String firstName, String lastName, String username, String password, Boolean isProf) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isProf = isProf;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getProf() {
        return isProf;
    }

    public void setProf(Boolean prof) {
        isProf = prof;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isProf=" + isProf +
                ", courses=" + courses +
                '}';
    }
}
