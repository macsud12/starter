package com.macs.starter.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.macs.starter.model.BasicResponse;
import com.macs.starter.model.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.intercept.RunAsUserToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Maksim_Alipov on 1/30/14.
 */
@Controller
@Component
@RequestMapping("/starter/v1/")
public class Auth {

    @Autowired
    protected AuthenticationProvider authenticationProvider;

    private static final String template = "Hello, %s!";

    @RequestMapping("/auth")
    public @ResponseBody
    BasicResponse auth(
            @RequestParam(value="name", required=false, defaultValue="World") String name) {
        BasicResponse basicResponse = new BasicResponse();
        Authentication auth = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken("user", "password", Arrays.asList(new SimpleGrantedAuthority("USER"))));
        SecurityContextHolder.getContext().setAuthentication(auth);
        basicResponse.setContent("Success auth");
        return basicResponse;
    }



    @RequestMapping("/current")
    public @ResponseBody
    BasicResponse current() {
        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setContent(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return basicResponse;
    }
}
