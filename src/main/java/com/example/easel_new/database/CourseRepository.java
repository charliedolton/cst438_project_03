package com.example.easel_new.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    Course findCourseByCourseId(Integer classId);
    Course findCourseByCourseName(String className);
    List<Course> findCoursesByProfessor(String professor);
    List<Course> findAll();
    Course findCourseByAssignmentsContains(Assignment assignment);
}