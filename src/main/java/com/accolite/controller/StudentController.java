package com.accolite.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.entity.Student;
import com.accolite.repository.StudentRepo;

@RestController
public class StudentController {
	
	private static Logger logger=LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentRepo studentRepo;
	
	
	@PostMapping("/")
	public ResponseEntity<Student> addStudent(@RequestBody Student student){
		logger.info("Adding student details");
		studentRepo.save(student);
		return new ResponseEntity<Student>(student,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/update/{name}")
	public ResponseEntity<Student> updateStudent(@PathVariable String name){
		logger.warn("Updating student details");
		studentRepo.updateStudentByFirstName(name,"mvananthu@gmail.com");
		return new ResponseEntity<Student>(HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Student> deleteStudentById(@PathVariable Long id){
		if(id==null || !(studentRepo.findById(id).isPresent())) {
			logger.error("Student with "+id+" not found");
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}else {
			logger.warn("deleting student details");
			studentRepo.deleteById(id);
			return new ResponseEntity<Student>(HttpStatus.ACCEPTED);
		}
			
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Student>> getStudentById(@PathVariable Long id){
		if(id==null || !(studentRepo.findById(id).isPresent())) {
			logger.warn("No Student with this id");
			return new ResponseEntity<Optional<Student>>(HttpStatus.NO_CONTENT);
		}else {
			logger.info("fetching student details by id");
			Optional<Student> student=studentRepo.findById(id);
			return new ResponseEntity<Optional<Student>>(student,HttpStatus.FOUND);
			
		}
	}
	
	
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Student>> getStudentByFirstName(@PathVariable String name){
		
			logger.info("fetching student details by name");
			List<Student> student=studentRepo.findByFirstName(name);
			return new ResponseEntity<List<Student>>(student,HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Student>> getAllStudents(){
		logger.info("fetching all student details");
		List<Student> student=studentRepo.findAll();
		return new ResponseEntity<List<Student>>(student,HttpStatus.OK);
	}
	
	

}
