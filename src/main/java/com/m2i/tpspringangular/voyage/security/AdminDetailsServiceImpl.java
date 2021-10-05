package com.m2i.tpspringangular.voyage.security;

import com.m2i.tpspringangular.voyage.entities.AdminEntity;
import com.m2i.tpspringangular.voyage.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public AdminDetailsImpl loadUserByUsername(String usernameField ) throws
            UsernameNotFoundException {
        AdminEntity admin = adminRepository.findByUsername(usernameField);
        System.out.println(usernameField);
        System.out.println(admin);
        if(admin == null) {
            throw new UsernameNotFoundException("No user named " + usernameField);
        } else {
            return new AdminDetailsImpl(admin);
        }
    }
}
