package com.springbootRestApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springbootRestApi.entity.Student;
import com.springbootRestApi.repository.StudentRepository;

@RestController
public class StudentController {
       
	@Autowired
	StudentRepository repo;
	
	
	//get all the students 
	//localhost:8081/students
	@GetMapping("/students")
	public List<Student>getAllStudents(){
		List<Student> students = repo.findAll();
		
		return students;
	}
	
	//localhost:8081/students/1
	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable int id) {
	
		Student student = repo.findById(id).get();
		return student;
	}
	
	@PostMapping("/students/add")
	//1.@ResponseStatus(code = HttpStatus.CREATED)
	//2.REsponseENTITY statement
	//1.public void createStudent(@RequestBody Student student) {
		
		public ResponseEntity<String> createStudent(@RequestBody Student student){
		Student newStudent=repo.save(student);
		return ResponseEntity.status(HttpStatus.CREATED).body("student is inserted Successfully");
		}
	
	@PutMapping("/students/update/{id}")
	public Student updateStudents(@PathVariable int id) {
		
	Student student = repo .findById(id).get();
	    student.setName("ashi");
	   student.setPercentage(94);
	   repo.save(student);
	    return student;
	}
	
	@DeleteMapping("/students/delete/{id}")
		//1.@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void removeStudent(@PathVariable int id) {
		
	   Student student = repo.findById(id).get();
		repo.delete(student);
	}
	
	
}
