package com.restful.sistema_de_computacao.controller;

import com.restful.sistema_de_computacao.entity.Student;
import com.restful.sistema_de_computacao.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody  @Valid Student student) {
        studentService.save(student);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find_by_id/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {
        Student student = studentService.findById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid Student newStudent, @PathVariable Long id) {
        Student existingStudent = studentService.findById(id);

        existingStudent.setFirst_name(newStudent.getFirst_name());
        existingStudent.setLast_name(newStudent.getLast_name());
        existingStudent.setCpf(newStudent.getCpf());
        existingStudent.setEmail(newStudent.getEmail());
        existingStudent.setRa(newStudent.getRa());
        existingStudent.setBirth_date(newStudent.getBirth_date());

        studentService.save(existingStudent);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
