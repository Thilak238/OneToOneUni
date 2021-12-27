package com.springone.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

//Entity - Used to create a table in database . By Default, class name will be your table name.
@Entity
//Table - Used to define table name in database if needed
@Table(name = "organization")
//Data - used to reduce the boilerPlate code such as Getter and setter methods.
@Data
public class Organization {
    //Id - Defines the Primary key of the table
	@Id
	//GeneratedValue - Which is used to avoid duplicate entries. 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String orgId;
    
    //TargetEntity - Used to define the parent association class. 
    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    private Address address;
}