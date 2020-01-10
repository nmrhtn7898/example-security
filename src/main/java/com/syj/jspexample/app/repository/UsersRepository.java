package com.syj.jspexample.app.repository;

import com.syj.jspexample.app.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Transactional(readOnly = true)
    Optional<Users> findByEmail(String email);

    @Transactional(readOnly = true)
    Boolean existsByEmail(String email);

}
