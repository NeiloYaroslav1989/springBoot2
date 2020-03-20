package com.neilo.springboot2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //Включаем авторизацию
                .authorizeRequests()
                    //Разрешаем всем доступ к главной страничке
                    .antMatchers("/", "/registration").permitAll()
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
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select username, password, active " +
                                      "from usr " +
                                      "where username=?")
                .authoritiesByUsernameQuery("select u.username, ur.roles " +
                                            "from usr u inner " +
                                            "join user_role ur on u.id = ur.user_id " +
                                            "where u.username=?");
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
