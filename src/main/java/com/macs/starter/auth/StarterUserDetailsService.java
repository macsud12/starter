package com.macs.starter.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by Maksim_Alipov.
 */
@Component("userDetailsService")
public class StarterUserDetailsService implements UserDetailsService {

    public StarterUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, "password", Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}
