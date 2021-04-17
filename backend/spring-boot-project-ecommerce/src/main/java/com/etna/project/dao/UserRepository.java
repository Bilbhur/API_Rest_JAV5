package com.etna.project.dao;

import com.etna.project.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u ORDER BY u.id ASC")
    public List<User> getListByPage(Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getByUsername(String username);
}
