package com.macs.starter.filter;

import com.macs.starter.auth.StarterAuthProvider;
import com.macs.starter.config.WebSecurityConfig;
import com.macs.starter.model.ErrorCode;
import com.macs.starter.util.HttpUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Filter for checking token authorization
 */
@Component
public class TokenAuthFilter extends OncePerRequestFilter {

    public static final String TOKEN_HEADER = "access-token";


    private static final Log log = LogFactory.getLog(TokenAuthFilter.class);

    @Autowired
    @Qualifier("starterAuthProvider")
    StarterAuthProvider starterAuthProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String accessToken = request.getHeader(TOKEN_HEADER);
        if (accessToken != null) {
            try {
                Authentication auth = starterAuthProvider.authenticate(new RememberMeAuthenticationToken(WebSecurityConfig.TOKEN_KEY, accessToken,
                        Arrays.asList(new SimpleGrantedAuthority(WebSecurityConfig.USER_ROLE))));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (AuthenticationException e) {
                HttpUtils.fillWithError(response, ErrorCode.UNAUTHORIZED, "Provided Token is not valid");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
