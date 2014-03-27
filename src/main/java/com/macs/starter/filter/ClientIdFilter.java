package com.macs.starter.filter;

import com.macs.starter.auth.ClientIdService;
import com.macs.starter.auth.StarterAuthProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Maksim_Alipov.
 */
public class ClientIdFilter extends OncePerRequestFilter {

    private static final Log log = LogFactory.getLog(ClientIdFilter.class);

    @Autowired
    ClientIdService clientIdService;

    @Autowired
    @Qualifier("starterAuthProvider")
    StarterAuthProvider starterAuthProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        response.addHeader("token:", "filter");
        if (clientIdService.isClientIdValid(request.getHeader("client-id"))) {
            log.info("auth started");
            starterAuthProvider.authenticate(new UsernamePasswordAuthenticationToken("user", "password", Arrays.asList(new SimpleGrantedAuthority("USER"))));
            log.info("auth finished");
        }

        filterChain.doFilter(request, response);
    }
}
