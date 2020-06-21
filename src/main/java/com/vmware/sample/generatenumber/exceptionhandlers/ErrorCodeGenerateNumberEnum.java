package com.vmware.sample.generatenumber.exceptionhandlers;


import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum ErrorCodeGenerateNumberEnum implements ErrorCodeEnum {

    FILE_NOT_FOUND("FILE10001", "FILE_NOT_FOUND"),
    INVALID_ID_ERROR("FILE10002", "INVALID_ID_ERROR"),
    INTERNAL_ERROR("FILE10003", "INTERNAL_ERROR"),
    DATA_NULL("FILE10004", "DATA_IS_NULL"),
    DATA_INCORRECT("FILE10005", "DATA_INCORRECT");


    private static final Map<String, ErrorCodeGenerateNumberEnum> LOOKUP = new HashMap();
    private static ServiceEnum serviceEnum;
    private String errorCode;
    private String name;

    ErrorCodeGenerateNumberEnum(String errorCode, String name) {
        this.errorCode = errorCode;
        this.name = name;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getName() {
        return this.name;
    }

    public int getServiceId() {
        return serviceEnum.getServiceId();
    }

    public String getMessageKey() {
        return this.name;
    }

    public String getDefaultMessage() {
        switch(this) {
            case FILE_NOT_FOUND:
                return "File not found";
            case INVALID_ID_ERROR:
                return "Not a valid Id";
            default:
                return "We are not able to process your request at this time.";
        }
    }

    public static ErrorCodeGenerateNumberEnum get(String errorCode) {
        return (ErrorCodeGenerateNumberEnum)LOOKUP.get(errorCode);
    }

    public String getEnumName() {
        return this.name();
    }

    static {
        Iterator var0 = EnumSet.allOf(ErrorCodeGenerateNumberEnum.class).iterator();

        while(var0.hasNext()) {
            ErrorCodeGenerateNumberEnum e = (ErrorCodeGenerateNumberEnum)var0.next();
            LOOKUP.put(e.getErrorCode(), e);
        }

        serviceEnum = ServiceEnum.FILEGENERATOR;
    }
}
