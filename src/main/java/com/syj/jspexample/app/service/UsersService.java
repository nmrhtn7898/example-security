package com.syj.jspexample.app.service;

import com.syj.jspexample.app.dto.AuthUser;
import com.syj.jspexample.app.entity.Users;
import com.syj.jspexample.app.enums.Auth;
import com.syj.jspexample.app.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = usersRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("user not exist [email : " + email + "]")
                );
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (users.getAuth().equals(Auth.ADMIN)) {
            authorities.add(new SimpleGrantedAuthority(Auth.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Auth.USER.getValue()));
        }
        return new AuthUser(users, authorities);
    }

    @Transactional(readOnly = true)
    public Boolean available(String email) {
        return !usersRepository.existsByEmail(email);
    }

    public Users join(Users users) {
        usersRepository
                .findByEmail(users.getEmail())
                .ifPresent(u -> { throw new RuntimeException();}); // TODO : 임시 Exception
        users.setPassword(getPassword(users.getPassword()));
        return usersRepository.save(users);
    }

    public Users update(Users users) {
        Users updateUser = usersRepository
                .findByEmail(users.getEmail())
                .orElseThrow(RuntimeException::new); // TODO : 임시 Exception
        updateUser.setPassword(getPassword(users.getPassword()));
        return updateUser;
    }

    public Users delete(Users users) {
        Users deleteUser = usersRepository
                .findByEmail(users.getEmail())
                .orElseThrow(RuntimeException::new); // TODO : 임시 Exception
        deleteUser.setEnabled("N"); // TODO : 하드코딩 풀어아햠
        deleteUser.setDeleted("Y"); // TODO : 하드코딩 풀어아햠
        return deleteUser;
    }

    private String getPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
