package com.harman.ebook.vaccination.covid.repository;

import com.harman.ebook.vaccination.covid.entity.EmployeeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Bhagwat Rokade
 * @project Ingestion
 * @created 5/29/2021
 */
@RepositoryRestResource(collectionResourceRel = "employeemaster", path = "employeemaster")
public interface MasterEmpRespository extends JpaRepository<EmployeeMaster, Integer> {
	EmployeeMaster findByEmployeeId(Integer employeeId);
}
