package com.vmware.sample.generatenumber.contants;

public enum StatusEnum {

    SUCCESS("SUCCESS"),
    INPROGRESS("INPROGRESS"),
    ERROR("ERROR");
    private String  status;

    private StatusEnum(String status) {
        this.status = status;
    }

    public String value(){
        return this.status;
    }

}
