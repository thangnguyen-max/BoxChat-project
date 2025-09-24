package com.example.boxchat_java.controller;

import com.example.boxchat_java.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files/upload")
public class UploadController {

    @Autowired
    private UploadFileService uploadFileService;

    @PostMapping(produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.ok(""); // Không có file → trả về chuỗi rỗng
        }
        try {
            // Lưu file vào thư mục uploads (ví dụ: D:/BoxChat_Java/file-upload/)
            String fileName = uploadFileService.uploadFile(file, "uploads");

            return ResponseEntity.ok(fileName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed: " + e.getMessage());
        }
    }
}

