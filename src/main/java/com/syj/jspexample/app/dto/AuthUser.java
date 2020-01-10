package com.syj.jspexample.app.dto;

import com.syj.jspexample.app.entity.Users;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AuthUser extends User {

    private final Long id;

    private final String email;

    private final LocalDateTime created;

    private final LocalDateTime modified;

    private final List<GrantedAuthority> authorities;

    public AuthUser(Users user, List<GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.id = user.getId();
        this.email = user.getEmail();
        this.created = user.getCreated();
        this.modified = user.getModified();
        this.authorities = authorities;
    }

}
