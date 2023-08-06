package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping
	public List<Student> getStudents(){
		return studentService.getStudents();
	}

	//    POST MAPPING  - when want to add a new resources to our system in our case - add a new student
	@PostMapping
	public void registerNewStudent(@RequestBody Student student){ //@RequestBody - map JSON from client to Student
		studentService.addNewStudent(student);
	}

	// 	  DELETE MAPPING  - when want to delete exist resource from our system
//	http://localhost:8080/api/v1/student/1    => /1 - is parameter which we take with @PathVariable - in our case studentId =1
	@DeleteMapping(path = "{studentId}")  // {this is pass param in the URL and we want to take it}
	public void deleteStudent(@PathVariable("studentId") Long studentId){
		studentService.deleteStudent(studentId);
	}

}
