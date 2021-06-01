package com.harman.ebook.vaccination.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.harman.ebook.vaccination.covid.entity.VaccineInventory;

@RepositoryRestResource(collectionResourceRel = "vaccineInventory", path = "vaccine_inventory")
public interface VaccineInventoryRepository extends JpaRepository<VaccineInventory, Integer>{

}
