package com.etna.project.dao;

import com.etna.project.entity.Country;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@Repository
//@RepositoryRestResource(collectionResourceRel = "countries", path = "countries")
public interface CountryRepository extends PagingAndSortingRepository<Country, Integer> {

    @Query("Select c From Country c order by c.id ASC")
    public List<Country> getListByPage(Pageable pageable);
}
