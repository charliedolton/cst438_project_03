package com.project3.easel.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClassRepository extends CrudRepository<Class, Integer> {

    Class findClassByClassId(Integer classId);
    Class findClassByClassName(String className);
    List<Class> findClassesByProfessor(String professor);
}
