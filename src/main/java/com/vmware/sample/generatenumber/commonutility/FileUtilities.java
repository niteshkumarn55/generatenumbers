package com.vmware.sample.generatenumber.commonutility;

import com.vmware.sample.generatenumber.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class FileUtilities {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    public File createFile(String taskId){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream(taskId+".txt");
        String basePathOfClass = getClass()
                .getProtectionDomain().getCodeSource().getLocation().getFile();
        File file = new File(basePathOfClass, taskId+".txt");
        return file;
    }

    public String readFile(String filePath) throws IOException {
        String data = null;
        data = new String(Files.readAllBytes(Paths.get(filePath)));
        return data;
    }

    public String getFilePath(String taskId){
        String basePathOfClass = getClass()
                .getProtectionDomain().getCodeSource().getLocation().getFile();
        String path = basePathOfClass+"/"+taskId+".txt";
        return path;
    }
}
