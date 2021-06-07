package com.harman.ebook.vaccination.covid.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.harman.ebook.vaccination.covid.entity.VaccineInventory;

@RepositoryRestResource(collectionResourceRel = "vaccineInventory", path = "vaccine_inventory")
public interface VaccineInventoryRepository extends JpaRepository<VaccineInventory, Integer>{

	@Query("select vacInv from VaccineInventory vacInv where vacInv.location = :location and vacInv.dateOfAvailability >= :fromDate and vacInv.dateOfAvailability <= :tillDate and vacInv.isactive = :isActive")
	List<VaccineInventory> findByLocationAndDateOfAvailabilityBetweenAndIsActiveAndOrderByDateOfAvailability(Short location,Date fromDate,Date tillDate, Boolean isActive);

	VaccineInventory findVaccineInventoryByVacTypeAndLocationAndDateOfAvailability(Short vacType, Short location, Date dateOfAvailability);

	@Query(value = 	"SELECT vacInv FROM VaccineInventory vacInv WHERE vacInv.location = :location AND " +
					"vacInv.dateOfAvailability <= TO_DATE(:dateOfAvailability, 'yyyy-MM-dd') and vacInv.vacType =:vacType")
	VaccineInventory getVaccineInventoryByVacTypeLocationDOAvailability(@Param("vacType") Short vacType,
																		@Param("location") Short location,
																		@Param("dateOfAvailability") String dateOfAvailability);
}
