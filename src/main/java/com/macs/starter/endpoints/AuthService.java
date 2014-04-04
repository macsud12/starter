package com.macs.starter.endpoints;

import com.macs.starter.filter.TokenAuthFilter;
import com.macs.starter.model.BasicResponse;
import com.macs.starter.model.SessionToken;
import com.macs.starter.model.User;
import com.macs.starter.storage.TokenHolder;
import com.macs.starter.storage.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceException;

/**
 * Auth Service
 */
@RestController
@RequestMapping("/starter/v1/")
public class AuthService {

    @Autowired
    protected UserHolder userHolder;

    @Autowired
    protected TokenHolder tokenHolder;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public BasicResponse<String> auth(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            HttpServletRequest request, HttpServletResponse response) {

        SecurityContextHolder.clearContext();

        User user = userHolder.getUser(username, password);

        if (user == null) {
            throw new WebServiceException("Invalid credentials");
        }

        SessionToken sessionToken = new SessionToken(user.getUsername());
        tokenHolder.save(sessionToken);
        response.setHeader(TokenAuthFilter.TOKEN_HEADER, sessionToken.getId());
        return new BasicResponse<String>("Authenticated");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public BasicResponse<String> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        request.getSession().invalidate();
        tokenHolder.delete(request.getHeader(TokenAuthFilter.TOKEN_HEADER));
        return new BasicResponse<String>("Logged out");
    }


    @RequestMapping("/current")
    public
    @ResponseBody
    BasicResponse<Object> current() {
        return new BasicResponse<Object>(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
