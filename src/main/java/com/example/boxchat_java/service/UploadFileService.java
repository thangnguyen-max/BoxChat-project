package com.example.boxchat_java.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class UploadFileService {

    public String uploadFile(MultipartFile file, String targetFolder) {
        if (file.isEmpty()) {
            return "";
        }
        String rootPath = "D:/BoxChat_java";
        try {
            byte[] bytes = file.getBytes();

            File dir = new File(rootPath + File.separator + targetFolder);
            if (!dir.exists()) {
                dir.mkdirs();
            }


            String fileName = file.getOriginalFilename();
            File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);


            try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                stream.write(bytes);
                return fileName;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

