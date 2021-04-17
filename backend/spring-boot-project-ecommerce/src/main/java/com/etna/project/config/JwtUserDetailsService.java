package com.etna.project.config;

import com.etna.project.entity.JwtUserDetails;
import com.etna.project.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public JwtUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new JwtUserDetails(userRepository.getByUsername(s));
    }
}
