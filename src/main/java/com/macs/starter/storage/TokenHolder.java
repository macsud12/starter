package com.macs.starter.storage;

import com.macs.starter.model.SessionToken;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Token Holder
 */
@Component
public class TokenHolder {
    private HashMap<String, SessionToken> tokens = new HashMap<String, SessionToken>();

    public void save(SessionToken token) {
        tokens.put(token.getId(), token);
    }

    public void delete(String tokenId) {
        tokens.remove(tokenId);
    }

    public SessionToken getValidToken(String tokenId) {
        return tokens.get(tokenId);
    }
}
