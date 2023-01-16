package com.mrgoropeza.portfoliobackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mrgoropeza.portfoliobackend.model.User;
import com.mrgoropeza.portfoliobackend.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findOneByName(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario " + username  + "no encontrado."));

        return new UserDetailsImpl(user);
    }

    
    
}
