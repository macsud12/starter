package com.macs.starter.config;

import com.macs.starter.auth.StarterAuthProvider;
import com.macs.starter.filter.ClientIdFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;

/**
* Created by Maksim_Alipov.
*/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    StarterAuthProvider starterAuthProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers("/**/note/**/").authenticated();
        http.csrf().disable();
        http.logout().logoutUrl("/signout");
        http.addFilterBefore(checkTokenFilter(), RememberMeAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                authenticationProvider(starterAuthProvider);
    }

    @Bean
    public javax.servlet.Filter checkTokenFilter() {
        return new ClientIdFilter();
    }
}
