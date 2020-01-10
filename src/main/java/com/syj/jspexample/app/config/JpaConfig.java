package com.syj.jspexample.app.config;

import com.syj.jspexample.app.dto.AuthUser;
import com.syj.jspexample.app.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Objects;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaConfig {

    @Bean
    AuditorAware<Users> auditorProvider() {
        return () -> {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (Objects.isNull(auth) || !auth.isAuthenticated()) {
                return Optional.empty();
            }
            System.out.println(auth.getPrincipal() + "####");
            System.out.println(auth.getPrincipal().getClass() + "####");
            AuthUser authUser = (AuthUser) auth.getPrincipal();
            Users users = new Users();
            users.setId(authUser.getId());
            return Optional.of(users);
        };
    }

}
