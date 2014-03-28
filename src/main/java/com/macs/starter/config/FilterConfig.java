package com.macs.starter.config;

import com.macs.starter.filter.ClientIdFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class FilterConfig {

    private static final Log log = LogFactory.getLog(FilterConfig.class);

    @Configuration
    protected static class ApplicationContextFilterConfiguration {
//        @Bean
//        public javax.servlet.Filter checkTokenFilter(ApplicationContext context) {
//            return new ClientIdFilter();
//        }
    }
}
