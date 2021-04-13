package com.etna.project.dao;

import com.etna.project.entity.Country;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@Repository
public interface CountryRepository extends PagingAndSortingRepository<Country, Integer> {

    @Query("SELECT c FROM Country c ORDER BY c.id ASC")
    public List<Country> getListByPage(Pageable pageable);

    @Query("SELECT c FROM Country c WHERE c.name = :name")
    public Country getByName(String name);
}
