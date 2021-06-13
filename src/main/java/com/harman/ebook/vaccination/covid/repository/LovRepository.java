package com.harman.ebook.vaccination.covid.repository;

import com.harman.ebook.vaccination.covid.entity.Lov;

import java.util.List;
import java.util.Optional;
import javax.persistence.QueryHint;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Bhagwat Rokade
 * @project Ingestion
 * @created 5/30/2021
 */
@RepositoryRestResource(collectionResourceRel = "lov", path = "lov")
public interface LovRepository extends JpaRepository<Lov, Integer> {
    @QueryHints(value = {@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true")})
    List<Lov> findBylovtypeid(@Param("lovtypeid") Short lovtypeid, Pageable page);

    @QueryHints(value = {@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true")})
    List<Lov> findBylovtypeidAndIsActive(@Param("lovtypeid") Short lovtypeid,
                                         @Param("isActive") Boolean isActive, Pageable page);

    @QueryHints(value = {@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true")})
    @Query("SELECT l FROM Lov l WHERE l.lovtypeid = :lovtypeid AND l.isActive = :isActive")
    List<Lov> getLovByLovtypeIdIsActive(@Param("lovtypeid") Short lovtypeid,
                                        @Param("isActive") Boolean isActive);

    @QueryHints(value = {@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true")})
    Optional<Lov> findBylovtypeidAndValueAndIsActive(@Param("lovtypeid") Short lovtypeid,
                                                     @Param("value") String value,
                                                     @Param("isActive") Boolean isActive);

    @QueryHints(value = {@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true")})
    Optional<Lov> findBylovtypeidAndValueid(@Param("lovtypeid") Short lovtypeid,
                                            @Param("valueid") Integer valueid);

    @QueryHints(value = {@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true")})
    Lov findBylovtypeidAndValue(@Param("lovtypeid") Short lovtypeid, @Param("value") String value);

    @QueryHints(value = {@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true")})
    Lov findBylovid(@Param("lovId") Integer lovId);

}
