package com.restful.sistema_de_computacao.service;

import com.restful.sistema_de_computacao.entity.Student;

import java.util.List;

public interface StudentService {

    void save(Student student);

    void delete(Long id);

    Student findById(Long id);

    List<Student> getAll();
}
