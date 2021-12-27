package com.springone.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springone.entity.Organization;
import com.springone.repository.AddressRepository;
import com.springone.repository.OrganizationRepository;

@Service
public class OrganizationService {

	@Autowired
	public AddressRepository addressRepository;
	
	@Autowired
	public OrganizationRepository organizationRepository;
	
	 public ResponseEntity<?> createOrganization(Organization organization) {
		 Optional<Organization> optionalOrganization = organizationRepository.findById(organization.getId());
		 if(optionalOrganization.isPresent()) {
			 return ResponseEntity.unprocessableEntity().body("Id already Exist");
		 }
		 else {
			 Organization creatingOrganization = new Organization();
			 creatingOrganization.setId(organization.getId());
			 creatingOrganization.setAddress(organization.getAddress());
			 creatingOrganization.setName(organization.getName());
			 organizationRepository.save(creatingOrganization);
			 return ResponseEntity.ok().body("Organization created successfully");
		 }
	 }
	 
	 public ResponseEntity<?> updateOrganization(Long id,Organization organization) {
		 Optional<Organization> optionalOrganization = organizationRepository.findById(id);
		 if(!optionalOrganization.isPresent()) {
			return ResponseEntity.unprocessableEntity().body("ID does not Exist"); 
		 }
		 else {
			 Organization updateOrganization = optionalOrganization.get();
			 updateOrganization.setId(organization.getId());
			 updateOrganization.setName(organization.getName());		 
			 //Since it is a one to one relation we need to delete the Existing address for that user
			 addressRepository.deleteById(optionalOrganization.get().getAddress().getId());
			 updateOrganization.setAddress(organization.getAddress());
			 return ResponseEntity.ok().body("Organization updated Successfully");
		 }
	 }
	 
}
