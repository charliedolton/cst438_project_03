package com.project3.easel.database;


import javax.persistence.*;
import java.util.List;

@Entity
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer classId;

    private String className;
    private String classTime;
    private String professor;
    @OneToMany
    private List<Assignment> assignments;

    public Class() {

    }

    public Class(Integer classId, String className, String classTime, String professor) {
        this.classId = classId;
        this.className = className;
        this.classTime = classTime;
        this.professor = professor;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    @Override
    public String toString() {
        return "Class{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", classTime='" + classTime + '\'' +
                ", professor='" + professor + '\'' +
                ", assignments=" + assignments +
                '}';
    }
}
