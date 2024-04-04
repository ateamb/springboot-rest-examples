package com.javabanters.demorest.rest;

import com.javabanters.demorest.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    public List<Student> students;
    @PostConstruct
    public void createStudents(){
        Student s1 = new Student("Hemanth","Tamang");
        Student s2 = new Student("Tom","Rai");
        Student s3 = new Student("Sita","Gurung");
        students = List.of(s1,s2,s3);
    }
    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudents(@PathVariable int studentId){

        if(studentId >= students.size() || studentId < 0) {
            throw  new StudentNotFoundException("Student not found with id "+studentId);
        }

        return students.get(studentId);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception ex) {
        StudentErrorResponse response = new StudentErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());
        response.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
