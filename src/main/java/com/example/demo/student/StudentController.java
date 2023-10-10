package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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

	//TODO: @RequestParams example
	//   PUT MAPPING - when want to update record in our table
	//URL - localhost:8080/api/v1/student/2?name=Anton&email=Anton@gmail.com - here the last part after "?' are key-value pairs it mena name of parameter and his values we try to take
	// because we say in @RequestParam(required = false) are optional it mean we can have name or email and can not
	// exmaples: localhost:8080/api/v1/student/2 or
	// localhost:8080/api/v1/student/2?name=Anton or
	// localhost:8080/api/v1/student/2?email=Anton@gmail.com or both - localhost:8080/api/v1/student/2?name=Anton&email=Anton@gmail.com
	@PutMapping(path = "{studentId}")
	public void updateStudent(@PathVariable("studentId") Long studentId,
							  @RequestParam(required = false) String name, // @RequestParams(required = false) -> this mean that is optional param - parameter can or not  exist(passed from client)
							  @RequestParam(required = false) String email) {
		studentService.updateStudent(studentId, name, email);
	}
}
