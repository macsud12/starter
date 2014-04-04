package com.macs.starter.storage;

import com.macs.starter.model.Client;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Client ID Holder
 */
@Component
public class ClientIdHolder {
    private HashMap<String, Client> ids = new HashMap<String, Client>();


    public ClientIdHolder() {
        // Dummy data
        ids.put("app1", new Client("app1", "app1@app1.com", "App 1"));
        ids.put("app2", new Client("app2", "app1@app1.com", "App 2"));
    }

    public boolean isValid(String id) {
        return ids.containsKey(id);
    }
}
