package com.macs.starter.endpoints;

import com.macs.starter.model.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Created by Maksim_Alipov.
 */
@Controller
@Component
@RequestMapping("/starter/v1/")
public class AuthService {

    @Autowired
    protected AuthenticationProvider authenticationProvider;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public @ResponseBody
    BasicResponse<String> auth(
            @RequestParam(value = "username", required = true) String name,
            @RequestParam(value = "password", required = true) String password,
            HttpServletRequest request, HttpServletResponse response) {

        SecurityContextHolder.clearContext();
        Authentication auth = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(name, password, Arrays.asList(new SimpleGrantedAuthority("USER"))));
        SecurityContextHolder.getContext().setAuthentication(auth);
        response.setHeader("Auth", "Success");
        return new BasicResponse<String>("Success auth");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public @ResponseBody
    BasicResponse<String> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        request.getSession().invalidate();
        return  new BasicResponse<String>("Success logout");
    }



    @RequestMapping("/current")
    public @ResponseBody
    BasicResponse<Object> current() {
        return new BasicResponse<Object>(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
