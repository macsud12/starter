package com.macs.starter.config;

import com.macs.starter.auth.StarterAuthProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Config
 */
@Configuration
public class Config {

    private static final Log log = LogFactory.getLog(Config.class);

    @Bean
    public StarterAuthProvider starterAuthProvider() {
        return new StarterAuthProvider(WebSecurityConfig.TOKEN_KEY);
    }

}
