package com.example.demo.controllers;

import com.example.demo.domain.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
public class StudentsController {

    List<Student> students = Arrays.asList(
            new Student(UUID.randomUUID().toString(), "annasmith"),
            new Student(UUID.randomUUID().toString(), "jamesbond")
    );

    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> createStudent(@RequestBody final Student body) {
        return ResponseEntity.ok("Created");
    }

    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasAuthority('students:write')")
    public ResponseEntity<String> deleteStudent(@PathVariable final String studentId) {
        return ResponseEntity.ok("Deleted " + studentId);
    }
}
