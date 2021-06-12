package com.harman.ebook.vaccination.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.harman.ebook.vaccination.covid.entity.EmployeeVaccAppointmentInfo;

import java.util.Date;
import java.util.List;


@RepositoryRestResource(collectionResourceRel = "employeeVaccSchInfo", path = "employee_vacc_sch_info")
public interface EmployeeVaccAppointmentInfoRepository extends JpaRepository<EmployeeVaccAppointmentInfo, Integer> {

    EmployeeVaccAppointmentInfo findEmployeeVaccAppointmentInfoByPersonIdAndStatus(Integer personId, Short status);

    List<EmployeeVaccAppointmentInfo> findEmployeeVaccAppointmentInfosByLocationAndDateOfVaccinationAndStatus(Short location, Date bookingDate, Short status);
}
