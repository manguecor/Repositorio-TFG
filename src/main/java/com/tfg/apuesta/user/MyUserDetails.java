package com.tfg.apuesta.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    /* ~ Properties
    ----------------------------------------------------------------------- */
    private String username;
    private String password;
    private boolean active;
    private List<SimpleGrantedAuthority> authorities;


    /* ~ Methods
    ----------------------------------------------------------------------- */
    public MyUserDetails(User user) {
        this.authorities = new ArrayList<SimpleGrantedAuthority>();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.active = user.isEnabled();
    } // end constructor

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
} 
