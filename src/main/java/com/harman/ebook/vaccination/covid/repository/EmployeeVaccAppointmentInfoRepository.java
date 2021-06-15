package com.harman.ebook.vaccination.covid.repository;

import com.harman.ebook.vaccination.covid.entity.EmployeeVaccAppointmentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeVaccAppointmentInfoRepository extends JpaRepository<EmployeeVaccAppointmentInfo, Integer> {

    /*@Query(value = "SELECT new com.harman.ebook.vaccination.covid.domain.AppointmentVO(empAp.person.personId," +
            "empAp.person.)  FROM EmployeeVaccAppointmentInfo empAp " +
            "JOIN EmployeeMaster empM ON empM.empMasterId = empAp.person.empMasterId " +
            "WHERE empM.employeeId = :empId")*/

    @Query(value = "SELECT empAp FROM EmployeeVaccAppointmentInfo empAp " +
            "JOIN EmployeeMaster empM ON empM.empMasterId = empAp.person.empMasterId " +
            "WHERE empM.employeeId = :empId "
        + " order by empAp.personId,empAp.dateOfVaccination")
    List<EmployeeVaccAppointmentInfo> getAppointmentByEmployeeId(@Param("empId") String empId);

    EmployeeVaccAppointmentInfo findEmployeeVaccAppointmentInfoByPersonIdAndStatus(Integer personId, Short status);

    List<EmployeeVaccAppointmentInfo> findEmployeeVaccAppointmentInfosByLocationAndDateOfVaccinationAndStatus(Short location, Date bookingDate, Short status);
    List<EmployeeVaccAppointmentInfo> findEmployeeVaccAppointmentInfosByLocationAndDateOfVaccinationAndStatusOrderBySlotNoAscEmpVaccAppIdAsc(Short location, Date bookingDate, Short status);

}
