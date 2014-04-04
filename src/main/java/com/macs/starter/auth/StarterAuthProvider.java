package com.macs.starter.auth;

import com.macs.starter.config.WebSecurityConfig;
import com.macs.starter.model.SessionToken;
import com.macs.starter.storage.TokenHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;

/**
 * Token Auth Provider
 */
public class StarterAuthProvider extends RememberMeAuthenticationProvider {

    @Autowired
    TokenHolder tokenHolder;

    public StarterAuthProvider(String key) {
        super(key);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }

        if (getKey().hashCode() != ((RememberMeAuthenticationToken) authentication).getKeyHash()) {
            throw new AuthenticationServiceException("Token Not Valid. Key mismatch");
        }

        SessionToken validToken = tokenHolder.getValidToken((String) authentication.getPrincipal());

        // No token
        if (validToken == null) {
            throw new AuthenticationServiceException("Token Not Valid");
        }

        // Expired token
        if (System.currentTimeMillis() > validToken.getValidTillTimestamp()) {
            throw new AuthenticationServiceException("Token Expired");
        }

        return new RememberMeAuthenticationToken(getKey(), validToken.getUsername(), Arrays.asList(new SimpleGrantedAuthority(WebSecurityConfig.USER_ROLE)));
    }
}
