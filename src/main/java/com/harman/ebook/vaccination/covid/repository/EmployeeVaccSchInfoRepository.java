package com.harman.ebook.vaccination.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.harman.ebook.vaccination.covid.entity.EmployeeVaccSchInfo;


@RepositoryRestResource(collectionResourceRel = "employeeVaccSchInfo", path = "employee_vacc_sch_info")
public interface EmployeeVaccSchInfoRepository extends JpaRepository<EmployeeVaccSchInfo, Integer> {

}
