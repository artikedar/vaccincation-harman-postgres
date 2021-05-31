package com.harman.ebook.vaccination.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.harman.ebook.vaccination.covid.entity.SlotInfo;

@RepositoryRestResource(collectionResourceRel = "slotInfo", path = "slot_info")
public interface SlotInfoRepository extends JpaRepository<SlotInfo, Integer>{

}
