package com.quest.etna.repositories;

import com.quest.etna.model.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

    @Query("SELECT a FROM Address a WHERE a.city = :city")
    Address findAddressByCity(String city);
    
    @Query("SELECT a FROM Address a WHERE a.id = :id")
    Address findAddressById(int id);
    
    @Query("SELECT a FROM Address a")
    Address findAllAddress();
}
