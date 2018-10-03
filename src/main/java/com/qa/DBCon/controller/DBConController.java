package com.qa.DBCon.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.DBCon.exception.ResourceNotFoundException;
import com.qa.DBCon.model.mySpringBootDataModel;

import SpringDataAccessPackage.personRepository;

@RestController
@RequestMapping("/api")
public class DBConController {
	
	@Autowired
	personRepository myRepo;

	// method to create a person 
	@PostMapping("/person")
	private mySpringBootDataModel createPerson(@Valid @RequestBody mySpringBootDataModel mSDM) {
		return myRepo.save(mSDM);
	}
	
	@GetMapping("/person/{id}")
	public mySpringBootDataModel getPersonbyID(@PathVariable(value="id")Long personID){
		
		return myRepo.findById(personID).orElseThrow(()->new ResourceNotFoundException("mySpringBootDataModel", "id", personID));
	}
	
	@GetMapping("/person")
	public List<mySpringBootDataModel> getAllPeople()
	{
		return myRepo.findAll();
	}
	
	@PutMapping("/person/{id}")
	public mySpringBootDataModel updatePerson(@PathVariable(value="id")Long personID,
			@Valid@RequestBody mySpringBootDataModel personDetails)
	{
		mySpringBootDataModel mSDM = myRepo.findById(personID).orElseThrow(()->new ResourceNotFoundException("mySpringBootDataModel", "id", personID));
		
		mSDM.setName(personDetails.getName());
		mSDM.setAddress(personDetails.getAddress());
		mSDM.setAge(personDetails.getAge());
		
		mySpringBootDataModel updateData = myRepo.save(mSDM);
		return updateData;
	}
	

	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value="id")Long personID)
	{
		mySpringBootDataModel mSDM = myRepo.findById(personID).orElseThrow(()->new ResourceNotFoundException("mySpringBootDataModel", "id", personID));
		
		myRepo.delete(mSDM);
		return ResponseEntity.ok().build();
	}
	
	
}
