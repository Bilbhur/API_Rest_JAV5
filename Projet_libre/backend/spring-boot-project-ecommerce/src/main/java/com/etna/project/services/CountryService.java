package com.etna.project.services;

import com.etna.project.dao.CountryRepository;
import com.etna.project.entity.Country;
import com.etna.project.exception.CustomConflictException;
import com.etna.project.exception.CustomResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService implements IModelService<Country>{

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> getList(Integer page, Integer limit) {
        PageRequest pageable = PageRequest.of(page, limit);
        return countryRepository.getListByPage(pageable);
    }

    @Override
    public Country getOneById(Integer id) {
        Optional<Country> country = countryRepository.findById(id);

        if (country.isEmpty())
            return null;

        return country.get();
    }

    @Override
    public Country create(Country entity) {
        Country country = countryRepository.getByName(entity.getName());
        if (null != country)
            throw new CustomConflictException();

        countryRepository.save(entity);
        return entity;
    }

    @Override
    public Country update(Integer id, Country entity) {
        Optional<Country> country = countryRepository.findById(id);

        if (country.isEmpty())
            throw new CustomResourceNotFoundException();
//            return null;

        Country countryFound = country.get();

        countryFound.setName(entity.getName());
        countryFound.setCode(entity.getCode());

        countryRepository.save(countryFound);

        return countryFound;
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            Optional<Country> country = countryRepository.findById(id);

            if (country.isEmpty())
                throw new CustomResourceNotFoundException();
//                return null;

            Country countryFound = country.get();

            countryRepository.delete(countryFound);
        } catch (Exception e) {
            throw new CustomResourceNotFoundException();
//            return false;
        }
        return true;
    }

}
