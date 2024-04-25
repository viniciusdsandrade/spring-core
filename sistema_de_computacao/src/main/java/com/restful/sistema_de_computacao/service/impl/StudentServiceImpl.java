package com.restful.sistema_de_computacao.service.impl;

import com.restful.sistema_de_computacao.entity.Student;
import com.restful.sistema_de_computacao.repository.StudentRepository;
import com.restful.sistema_de_computacao.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        if (!studentRepository.existsById(id))
            throw new EntityNotFoundException("Estudante não encontrado");

        studentRepository.deleteById(id);
    }

    @Override
    public Student findById(Long id) {

        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Estudante não encontrado"));
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }
}
