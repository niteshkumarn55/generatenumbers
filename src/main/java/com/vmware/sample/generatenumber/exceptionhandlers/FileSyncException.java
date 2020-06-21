package com.vmware.sample.generatenumber.exceptionhandlers;

import com.vmware.sample.generatenumber.exceptionhandlers.ErrorCodeEnum;

import java.util.Map;

public class FileSyncException extends BaseException {
    private static final long serialVersionUID = 88256725033191L;

    public FileSyncException(ErrorCodeEnum errorCode) {
        this.setErrorCode(errorCode);
        this.setDebugMessage(errorCode.getDefaultMessage());
    }

    public FileSyncException(ErrorCodeEnum errorCode, String debugMessage) {
        super(debugMessage);
        this.setErrorCode(errorCode);
        this.setDebugMessage(debugMessage);
    }

    public FileSyncException(ErrorCodeEnum errorCode, String debugMessage, Throwable originalException) {
        super(originalException);
        this.setErrorCode(errorCode);
        this.setDebugMessage(debugMessage);
    }

    public FileSyncException(ErrorCodeEnum errorCode, String debugMessage, Map<String, String> messageArgs) {
        this.setErrorCode(errorCode);
        this.setDebugMessage(debugMessage);
        this.messageArgs = messageArgs;
    }


}
