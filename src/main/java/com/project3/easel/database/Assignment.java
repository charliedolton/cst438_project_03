package com.project3.easel.database;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer assignmentId;

    private String assignmentName;
    private String assignmentDescription;
    private String dueDate;
    private Boolean isComplete;

    public Assignment() {

    }

    public Assignment(Integer assignmentId, String assignmentName, String assignmentDescription, String dueDate, Boolean isComplete) {
        this.assignmentId = assignmentId;
        this.assignmentName = assignmentName;
        this.assignmentDescription = assignmentDescription;
        this.dueDate = dueDate;
        this.isComplete = isComplete;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId=" + assignmentId +
                ", assignmentName='" + assignmentName + '\'' +
                ", assignmentDescription='" + assignmentDescription + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", isComplete=" + isComplete +
                '}';
    }
}
