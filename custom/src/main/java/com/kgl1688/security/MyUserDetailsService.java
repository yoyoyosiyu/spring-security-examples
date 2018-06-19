package com.kgl1688.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class MyUserDetailsService implements UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List roles = new ArrayList<GrantedAuthority>();

        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        System.out.println("MyUserDetailsService loadUserByUsername");

        return new User("user", "password11", true, true, true, true, roles);
    }
}
