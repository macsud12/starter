package com.macs.starter.auth;

import org.springframework.stereotype.Component;

/**
 * Created by Maksim_Alipov.
 */
@Component
public class ClientIdService {

    public boolean isClientIdValid(String clientId) {
        return clientId != null && clientId.equals("app");
    }

}
