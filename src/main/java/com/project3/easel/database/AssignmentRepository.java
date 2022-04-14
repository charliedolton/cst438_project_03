package com.project3.easel.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AssignmentRepository extends CrudRepository<Assignment, Integer> {

    Assignment findAssignmentByAssignmentId(Integer assignmentId);
    Assignment findAssignmentByAssignmentName(String assignmentName);
    List<Assignment> findAssignmentsByIsComplete(Boolean isComplete);
}
