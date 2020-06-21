package com.vmware.sample.generatenumber.service;


import com.vmware.sample.generatenumber.exceptionhandlers.FileSyncException;
import com.vmware.sample.generatenumber.model.NumberGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import java.util.Map;

@SpringBootTest
@ContextConfiguration
public class FileServiceImplTest {

    @Autowired
    FileServiceImpl fileService;

    @Test
    public void writeToFileTest(){
        NumberGenerator numberGenerator = new NumberGenerator();
        numberGenerator.setGoal(10);
        numberGenerator.setStep(2);
        ResponseEntity responseEntity = fileService.writeToFile(numberGenerator);
        Map response = (Map) responseEntity.getBody();
        Assert.notNull(response,"Response came as null");
        Assert.hasText((String) response.get("taskId"),"Task ID has no value");
    }

    @Test
    public void getStatusTest(){
        NumberGenerator numberGenerator = new NumberGenerator();
        numberGenerator.setGoal(10);
        numberGenerator.setStep(2);
        ResponseEntity responseEntity = fileService.writeToFile(numberGenerator);
        Map response = (Map) responseEntity.getBody();
        ResponseEntity responseEntity2 = fileService.getStatus((String) response.get("taskId"));
        String status = ((Map)responseEntity2.getBody()).get("result").toString();
        Assert.isTrue(status.equalsIgnoreCase("success"),"No status");
    }

    @Test
    public void getStatusFail(){
        NumberGenerator numberGenerator = new NumberGenerator();
        numberGenerator.setGoal(10);
        numberGenerator.setStep(2);
        Assertions.assertThrows(FileSyncException.class, () -> {
            fileService.getStatus("some test");
        });
    }

    @Test
    public void getNumberList(){
        NumberGenerator numberGenerator = new NumberGenerator();
        numberGenerator.setGoal(10);
        numberGenerator.setStep(2);
        ResponseEntity responseEntity = fileService.writeToFile(numberGenerator);
        Map response = (Map) responseEntity.getBody();
        ResponseEntity responseEntity2 = fileService.getNumberList((String) response.get("taskId"));
        String result = ((Map)responseEntity2.getBody()).get("result").toString();
        Assert.isTrue(result.equalsIgnoreCase("10,8,6,4,2,0"),"No proper result");
    }
}
