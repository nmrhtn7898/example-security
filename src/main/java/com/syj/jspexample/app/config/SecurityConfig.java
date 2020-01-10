package com.syj.jspexample.app.config;

import com.syj.jspexample.app.enums.Auth;
import com.syj.jspexample.app.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UsersService usersService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/boards/**").hasAnyAuthority(Auth.ADMIN.getValue(), Auth.USER.getValue())
                    .antMatchers("/api/boards/**").hasAnyAuthority(Auth.ADMIN.getValue(), Auth.USER.getValue())
                    .antMatchers("/**").permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/index")
                    .permitAll()
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/index")
                    .invalidateHttpSession(true)
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/login")
                .and()
                    .csrf()
                    .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersService).passwordEncoder(passwordEncoder);
    }

}
