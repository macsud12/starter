package com.macs.starter.util;

import com.macs.starter.model.BasicResponse;
import com.macs.starter.model.ErrorCode;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

/**
 * Http utils
 */
public final class HttpUtils {

    /**
     * Fill response object with error information
     *
     * @param response  response object
     * @param errorCode error code object
     * @param message   optional message
     */
    public static final void fillWithError(HttpServletResponse response, ErrorCode errorCode, String message) {
        try {
            String customMessage = message == null ? errorCode.getMessage() : message;
            response.getWriter().write(new BasicResponse<String>(errorCode, customMessage).toJSON());
            response.setStatus(errorCode.getStatus());
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

}
