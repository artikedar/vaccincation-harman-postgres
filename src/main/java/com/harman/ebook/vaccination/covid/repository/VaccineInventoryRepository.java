package com.harman.ebook.vaccination.covid.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.harman.ebook.vaccination.covid.entity.VaccineInventory;

@RepositoryRestResource(collectionResourceRel = "vaccineInventory", path = "vaccine_inventory")
public interface VaccineInventoryRepository extends JpaRepository<VaccineInventory, Integer>{
	List<VaccineInventory> findByLocationAndAndDateOfAvailabilityBetween(Short location,Date date1,Date tillDate);
}
