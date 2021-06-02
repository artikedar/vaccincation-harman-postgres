package com.harman.ebook.vaccination.covid.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.harman.ebook.vaccination.covid.entity.VaccineInventory;

@RepositoryRestResource(collectionResourceRel = "vaccineInventory", path = "vaccine_inventory")
public interface VaccineInventoryRepository extends JpaRepository<VaccineInventory, Integer>{

	@Query("select vacInv from VaccineInventory vacInv where vacInv.location = :location and vacInv.dateOfAvailability >= :fromDate and vacInv.dateOfAvailability <= :tillDate and vacInv.isactive = :isActive")
	List<VaccineInventory> findByLocationAndDateOfAvailabilityBetweenAndIsActive(Short location,Date fromDate,Date tillDate, Boolean isActive);
}
