package com.harman.ebook.vaccination.covid.repository;

import com.harman.ebook.vaccination.covid.entity.LovType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Bhagwat Rokade
 * @project Ingestion
 * @created 5/30/2021
 */
@RepositoryRestResource(collectionResourceRel = "lovtype", path = "lovtype")
public interface LovTypeRepostiory extends JpaRepository<LovType, Short> {

}
