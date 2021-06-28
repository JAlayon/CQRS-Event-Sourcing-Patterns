package com.alayon.springbank.user.oauth20.services;

import com.alayon.springbank.user.oauth20.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        var userFromDb = userRepository.findByUsername(username);
        if (userFromDb.isEmpty()){
            throw new UsernameNotFoundException("Incorrect Username / Password supplied!");
        }

        var user = userFromDb.get();
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getAccount().getUsername())
                .password(user.getAccount().getPassword())
                .authorities(user.getAccount().getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
