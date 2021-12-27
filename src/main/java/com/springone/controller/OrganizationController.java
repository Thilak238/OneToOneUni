package com.springone.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springone.entity.Organization;
import com.springone.repository.OrganizationRepository;
import com.springone.service.OrganizationService;

@RestController
public class OrganizationController {
	
	@Autowired
	public OrganizationService organizationService;
	
	@Autowired
	public OrganizationRepository organizationRepository;
	
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Organization organization){
		return organizationService.createOrganization(organization);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Organization organization){
		return organizationService.updateOrganization(id, organization);
	}

	@GetMapping
	public List<Organization> getAll() {
		return organizationRepository.findAll();
	}
	
	@GetMapping
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Optional<Organization> optionalorganization = organizationRepository.findById(id);
		if(optionalorganization.isPresent()) {
			return ResponseEntity.ok().body(optionalorganization.get());              
		}
		else {
			return ResponseEntity.unprocessableEntity().body("Id does not exist");
		}
	}
	
	

}
