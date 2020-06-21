package com.vmware.sample.generatenumber.service;

import com.vmware.sample.generatenumber.commonutility.FileUtilities;
import com.vmware.sample.generatenumber.contants.StatusEnum;
import com.vmware.sample.generatenumber.exceptionhandlers.ErrorCodeGenerateNumberEnum;
import com.vmware.sample.generatenumber.exceptionhandlers.FileSyncException;
import com.vmware.sample.generatenumber.model.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FileServiceImpl {

    @Autowired
    private FileUtilities fileUtilities;

    private static Map<String, String> status = new HashMap<>();


    public ResponseEntity<?> writeToFile(NumberGenerator generator) throws FileSyncException {
        String taskId = UUID.randomUUID().toString();
        try {
            status.put(taskId, StatusEnum.INPROGRESS.value());
            File f1 = fileUtilities.createFile(taskId);
            FileWriter fw = new FileWriter(f1.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            String num = generator(generator);
            bw.write(num);
            bw.close();
            status.put(taskId,StatusEnum.SUCCESS.value());
        }catch (IOException e){
            status.put(taskId,StatusEnum.ERROR.value());
            throw new FileSyncException(ErrorCodeGenerateNumberEnum.INVALID_ID_ERROR);
        }catch (Exception e){
            status.put(taskId,StatusEnum.ERROR.value());
            throw new FileSyncException(ErrorCodeGenerateNumberEnum.INTERNAL_ERROR);
        }
        Map<String, String> response = new HashMap<>();
        response.put("taskId", taskId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Async
    private String generator(NumberGenerator numGen) throws InterruptedException {
        int goal = numGen.getGoal();
        int step = numGen.getStep();
        String nums = null;
        while (goal>=0){
            if(nums != null){
                nums = nums+","+goal;
            }else{
                nums = Integer.toString(goal);

            }
            goal = goal - step;
        }
        return nums;
    }

    public ResponseEntity<?> getNumberList(String taskId) throws FileSyncException{
        try {
            if(taskId != null) {
                String numberList = fileUtilities.readFile(fileUtilities.getFilePath(taskId));
                Map<String, String> response = new HashMap<>();
                response.put("result", numberList);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                throw new FileSyncException(ErrorCodeGenerateNumberEnum.INVALID_ID_ERROR);
            }
        }catch (IOException e){
            throw new FileSyncException(ErrorCodeGenerateNumberEnum.FILE_NOT_FOUND);
        }
    }

    public ResponseEntity<?> getStatus(String taskId){
        if(taskId!=null){
            String stat = null;
            stat = status.get(taskId);
            if(stat!=null){
                Map<String, String> response = new HashMap<>();
                response.put("result", stat);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                throw new FileSyncException(ErrorCodeGenerateNumberEnum.INVALID_ID_ERROR);
            }
        }else{
            throw new FileSyncException(ErrorCodeGenerateNumberEnum.INVALID_ID_ERROR);
        }
    }

}
