package com.vmware.sample.generatenumber.commonutility;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ResponseMessage {

    private Long id;
    private String message;
    private String statusCode;
    private Date timestamp;

    public ResponseMessage(Long id, String message, String statusCode, Date timestamp) {
        this.id = id;
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
    }

}
