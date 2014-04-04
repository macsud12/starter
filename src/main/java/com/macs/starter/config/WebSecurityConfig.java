package com.macs.starter.config;

import com.macs.starter.auth.StarterAuthProvider;
import com.macs.starter.filter.ClientIdFilter;
import com.macs.starter.filter.TokenAuthFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;

/**
 * Web Security config
 */
@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String TOKEN_KEY = "23498ur9djk40jjkw-03rjslkdc";
    public static final String USER_ROLE = "USER";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers("/**/note/**/").authenticated();
        http.csrf().disable();
        http.logout().logoutUrl("/signout");
        http.addFilterBefore(checkTokenFilter(), RememberMeAuthenticationFilter.class);
        http.addFilterBefore(checkClientIdFilter(), TokenAuthFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                authenticationProvider(authProvider());
    }

    @Bean
    public javax.servlet.Filter checkTokenFilter() {
        return new TokenAuthFilter();
    }

    @Bean
    public javax.servlet.Filter checkClientIdFilter() {
        return new ClientIdFilter();
    }

    @Bean
    public AuthenticationProvider authProvider() {
        return new StarterAuthProvider(TOKEN_KEY);
    }
}
