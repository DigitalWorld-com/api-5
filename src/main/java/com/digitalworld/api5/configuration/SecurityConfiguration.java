package com.digitalworld.api5.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/convert/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/convert/conversion").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .httpBasic();
        //assignEndpointPermissions(http);
    }

    public void assignEndpointPermissions(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers(HttpMethod.DELETE, "/convert/delete/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/convert/conversion/{id}").hasRole("ADMIN");
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("abc123"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("abc123"))
                .roles("USER")
                .build();
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(admin, user);
        return userDetailsManager;
    }
}
