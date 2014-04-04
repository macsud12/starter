package com.macs.starter.storage;

import com.macs.starter.model.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;

/**
 * User Holder
 */
@Component
public class UserHolder {
    private HashSet<User> users = new HashSet<User>();


    public UserHolder() {
        // Dummy data
        users.add(new User("maks", "pass"));
        users.add(new User("maksB", "passB"));
    }


    public void save(User user) {
        users.add(user);
    }

    public boolean delete(String username) {
        for (Iterator<User> it = users.iterator(); it.hasNext(); ) {
            if (it.next().getUsername().equals(username)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public User getUser(String username, String password) {
        // Keep in mind that passwords should be hashed an salted in DB!!!
        for (Iterator<User> it = users.iterator(); it.hasNext(); ) {
            User next = it.next();
            if (next.getUsername().equals(username) && next.getPassword().equals(password)) {
                return next;
            }
        }
        return null;
    }
}
