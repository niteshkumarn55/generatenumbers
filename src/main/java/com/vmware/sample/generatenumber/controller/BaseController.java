package com.vmware.sample.generatenumber.controller;

import com.vmware.sample.generatenumber.exceptionhandlers.FileSyncException;
import com.vmware.sample.generatenumber.exceptionhandlers.ServiceError;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseController {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({SecurityException.class})
    public ServiceError handleSecurityException(SecurityException ossEx) {
        log.error("Service Error:", ossEx);
        ServiceError error = new ServiceError();
        error.setHttpStatus(HttpStatus.UNAUTHORIZED.value());
        error.setErrorCode("E00039");
        error.setDebugMessage(ossEx.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({FileSyncException.class})
    public ServiceError handleAppException(FileSyncException ossEx) {
        log.error("Service Error:", ossEx);
        ServiceError error = new ServiceError();
        error.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        error.setErrorCode(ossEx.getErrorCode().getErrorCode());
        error.setMessageArgs(ossEx.getMessageArgs());
        error.setDebugMessage(StringUtils.isBlank(ossEx.getDebugMessage()) ? ossEx.getMessage() : ossEx.getDebugMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler({Throwable.class})
    public ServiceError handleRuntimeException(Throwable tEx) {
        log.error("Runtime Error:", tEx);
        ServiceError error = new ServiceError();
        error.setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        error.setErrorCode("ITEM50000");
        error.setDebugMessage(tEx.getMessage());
        return error;
    }
}
