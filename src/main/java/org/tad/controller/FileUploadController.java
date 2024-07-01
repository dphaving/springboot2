package org.tad.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName FileUploadController
 * @Description FileUploadController
 * @Author Tad
 * @Date 2024/7/1 23:45
 * @Version 1.0
 */
@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public String upload(
            @RequestParam("email") String email,
            @RequestParam("userName") String userName,
            @RequestPart("headImg") MultipartFile headImg,
            @RequestPart("photos") MultipartFile[] photos) throws IOException {
        if (!headImg.isEmpty()) {
            String originalFilename = headImg.getOriginalFilename();
            headImg.transferTo(new File("D:\\study\\" + originalFilename));
        }
        for (MultipartFile photo : photos) {
            if (!photo.isEmpty()) {
                String originalFilename = photo.getOriginalFilename();
                photo.transferTo(new File("D:\\study\\" + originalFilename));
            }
        }

        return "ok";
    }

}
