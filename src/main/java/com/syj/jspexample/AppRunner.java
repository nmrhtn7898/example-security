package com.syj.jspexample;

import com.syj.jspexample.app.entity.Categories;
import com.syj.jspexample.app.entity.Users;
import com.syj.jspexample.app.enums.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {

    private final EntityManager entityManager;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void run(ApplicationArguments args) {
//        Users user = new Users();
//        user.setEmail("nmrhtn7898@naver.com");
//        user.setPassword(passwordEncoder.encode("1234"));
//        user.setAuth(Auth.ADMIN);
//        entityManager.persist(user);
//
//        Categories categories = new Categories();
//        Categories categories2 = new Categories();
//        categories.setName("free");
//        categories2.setName("users");
//        entityManager.persist(categories);
//        entityManager.persist(categories2);
    }
}
