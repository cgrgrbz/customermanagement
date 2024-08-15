package com.cgrgrbz.customerapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        //disable CSRF and authorizeHttpRequests
        httpSecurity.csrf((csrf -> csrf.disable()));
        httpSecurity.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/swagger-ui.html").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/customers").hasRole("JUNIOR")
                                .requestMatchers(HttpMethod.GET, "/api/customers/**").hasRole("JUNIOR")
                                .requestMatchers(HttpMethod.POST, "/api/customers").hasRole("JUNIOR")
                                .requestMatchers(HttpMethod.PUT, "/api/customers").hasRole("SENIOR")
                                .requestMatchers(HttpMethod.DELETE, "/api/customers/**").hasRole("SENIOR")
                                .requestMatchers(HttpMethod.GET, "/customers/list").hasRole("JUNIOR")
                                .requestMatchers(HttpMethod.GET, "/customers/pageForAdd").hasRole("JUNIOR")
                                .requestMatchers(HttpMethod.GET, "/customers/pageForUpdate").hasRole("SENIOR")
                                .requestMatchers(HttpMethod.POST, "/customers/save").hasRole("JUNIOR")
                                .requestMatchers(HttpMethod.GET, "/customers/delete").hasRole("SENIOR"));

        //use HTTP basic authentication
        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    /*
    * Disable CSRF and authorize http request for all users to perform POST, PUT, DELETE methods
    * otherwise we have 401 authentication error (only GET method works)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(configurer ->
                        configurer.requestMatchers("/api/customers/**").permitAll());
        return httpSecurity.build();
    }
     */



}
