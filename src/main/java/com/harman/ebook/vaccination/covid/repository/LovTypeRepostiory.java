package com.harman.ebook.vaccination.covid.repository;

import com.harman.ebook.vaccination.covid.entity.LovType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * @author Bhagwat Rokade
 * @project Ingestion
 * @created 5/30/2021
 */
@Repository
public interface LovTypeRepostiory extends JpaRepository<LovType, Short> {

}
