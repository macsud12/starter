package com.macs.starter.filter;

import com.macs.starter.model.ErrorCode;
import com.macs.starter.storage.ClientIdHolder;
import com.macs.starter.util.HttpUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter for checking Client ID
 */
@Component
public class ClientIdFilter extends OncePerRequestFilter {
    public static final String CLIENT_ID_HEADER = "client-id";

    private static final Log log = LogFactory.getLog(ClientIdFilter.class);

    @Autowired
    ClientIdHolder clientIdHolder;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (!clientIdHolder.isValid(request.getHeader(CLIENT_ID_HEADER))) {
            HttpUtils.fillWithError(response, ErrorCode.FORBIDDEN, "Client ID is wrong");
        } else {
            filterChain.doFilter(request, response);
        }
    }


}
