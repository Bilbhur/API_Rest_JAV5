package com.etna.project.service;

import com.etna.project.dao.UserRepository;
import com.etna.project.entity.Country;
import com.etna.project.entity.User;
import com.etna.project.exception.CustomConflictException;
import com.etna.project.exception.CustomInternalServerErrorException;
import com.etna.project.exception.CustomResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IModelService<User>{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getList(Integer page, Integer limit) {
        PageRequest pageable = PageRequest.of(page, limit);
        return userRepository.getListByPage(pageable);
    }

    @Override
    public User getOneById(Integer id) {
        return userRepository.findById(id).orElseThrow(CustomResourceNotFoundException::new);
    }

    @Override
    public User create(User entity) {
        User user = userRepository.getByUsername(entity.getUsername());
        if (null != user)
            throw new CustomConflictException();

        return userRepository.save(entity);
    }

    @Override
    public User update(Integer id, User entity) {
        User user = userRepository.findById(id).orElseThrow(CustomResourceNotFoundException::new);

        user.setUsername(entity.getUsername());

        return userRepository.save(user);
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            User user = userRepository.findById(id).orElseThrow(CustomResourceNotFoundException::new);
            userRepository.delete(user);
        } catch (Exception e) {
            throw new CustomInternalServerErrorException();
        }
        return true;
    }
}
