package com.harman.ebook.vaccination.covid.repository;


import com.harman.ebook.vaccination.covid.entity.Person;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Bhagwat Rokade
 * @project Ingestion
 * @created 5/29/2021
 */
@RepositoryRestResource(collectionResourceRel = "person", path = "person")
public interface PersonRespository extends JpaRepository<Person, Integer> {
	List<Person> findPersonByEmpMasterId(Integer empId);
	List<Person> findPersonByEmpMasterIdOrderByPersonId(Integer empId);
	Person findPersonByPersonId(Integer personId);

}
