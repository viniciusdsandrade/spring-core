package com.restful.studentmanagementsystem.service.impl;

import com.restful.studentmanagementsystem.repository.StudentRepository;
import com.restful.studentmanagementsystem.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    
}
