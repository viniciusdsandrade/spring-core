package com.restful.studentmanagementsystem.mapper;

import com.restful.studentmanagementsystem.dto.StudentDto;
import com.restful.studentmanagementsystem.entity.Student;

public class StudentMapper {

    public static StudentDto mapToDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }

    public static Student mapToEntity(StudentDto studentDto) {
        return new Student(
                studentDto.getId(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getEmail()
        );
    }

}
