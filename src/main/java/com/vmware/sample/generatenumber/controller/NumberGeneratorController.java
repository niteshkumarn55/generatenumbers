package com.vmware.sample.generatenumber.controller;

import com.vmware.sample.generatenumber.exceptionhandlers.ErrorCodeGenerateNumberEnum;
import com.vmware.sample.generatenumber.exceptionhandlers.FileSyncException;
import com.vmware.sample.generatenumber.model.NumberGenerator;
import com.vmware.sample.generatenumber.service.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class NumberGeneratorController {

    @Autowired
    FileServiceImpl fileService;

    @PostMapping(value = "/generate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> generate(@RequestBody NumberGenerator generator) {
        if(generator!=null){
            return fileService.writeToFile(generator);
        }else{
            throw new FileSyncException(ErrorCodeGenerateNumberEnum.DATA_NULL);
        }
    }

    @GetMapping(value = "/tasks/{UUID}/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStatus(@PathVariable String UUID){
        return fileService.getStatus(UUID);
    }

    @GetMapping(value = "/tasks/{UUID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStatus(@PathVariable String UUID, @RequestParam(defaultValue = "get_numlist") String action){
        if(action.equalsIgnoreCase("get_numlist")) {
            return fileService.getNumberList(UUID);
        }else{
            throw new FileSyncException(ErrorCodeGenerateNumberEnum.DATA_NULL);
        }

    }

}
