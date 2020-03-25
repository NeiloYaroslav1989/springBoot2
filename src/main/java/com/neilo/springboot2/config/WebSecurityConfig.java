package com.neilo.springboot2.config;

import com.neilo.springboot2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Для того, чтобы Спринг проверял роли пользователя
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //Включаем авторизацию
                .authorizeRequests()
                    //Разрешаем всем доступ к главной страничке
                    .antMatchers("/", "/registration", "/static/**", "/activate/*").permitAll()
                    //Для других страниц требуем авторизацию
                    .anyRequest().authenticated()
                .and()
                    .formLogin() //Включаем форму логин
                    .loginPage("/login") //Форма логина находится по такому шаблону
                    .permitAll() //Разрешаем доступ к форме логина всем пользователям
                .and()
                    .logout()
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

// Вместо этого простого метода с одной ролью мы будем использовать БД с юзерами и ролями
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("u")
//                        .password("p")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}
