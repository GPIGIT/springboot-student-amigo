package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private  final  StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents(){
        return  studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        // write business logic
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
            if(studentOptional.isPresent()){
                throw  new IllegalStateException("email taken");
            }
            studentRepository.save(student);
//        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
            // business logic
        boolean exists = studentRepository.existsById(studentId);
        if(!exists) {
            throw new IllegalStateException("student with id " + studentId  + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }
    // Transactional mean our Repository object is goes into manage stage
    @Transactional  // say no implement any JPQA query, so we can use setters from our entity to update records in DB
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId  + " does not exists"));

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
           Optional<Student> studentOptional =  studentRepository.findStudentByEmail(email);
           if (studentOptional.isPresent()){
               throw  new IllegalStateException("student with id " + studentId  + " does not exists");
           }
            student.setEmail(email);
        }
    }
}
