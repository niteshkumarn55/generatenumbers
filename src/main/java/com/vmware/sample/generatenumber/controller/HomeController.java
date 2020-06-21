package com.vmware.sample.generatenumber.controller;

import com.vmware.sample.generatenumber.exceptionhandlers.ErrorCodeGenerateNumberEnum;
import com.vmware.sample.generatenumber.exceptionhandlers.FileSyncException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-test")
public class HomeController extends BaseController {

    @GetMapping(value = "/get-home", produces = MediaType.APPLICATION_JSON_VALUE)
    public String home(){
        throw new FileSyncException(ErrorCodeGenerateNumberEnum.FILE_NOT_FOUND);
    }
}
