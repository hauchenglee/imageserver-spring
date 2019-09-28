package com.server.controller;

import com.server.util.Constants;
import lombok.extern.log4j.Log4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@RestController
@Log4j
public class ImageController {
    @PostMapping(value = "/api/image")
    public ResponseEntity<InputStreamResource> getImage(@RequestParam("filename") String filename, @RequestParam("ext") String ext) throws FileNotFoundException {
        String filePath = Constants.IMAGE_UPLOAD_URL + File.separator + filename + "." + ext;
        log.info("filename: " + filename + ", ext: " + ext);
        log.info("filepath: " + filePath);
        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("inline", filename);

        MediaType mediaType;
        switch (ext) {
            case "jpg":
            case "jpeg":
                mediaType = MediaType.IMAGE_JPEG;
                break;
            case "png":
                mediaType = MediaType.IMAGE_PNG;
                break;
            case "gif":
                mediaType = MediaType.IMAGE_GIF;
                break;
            default:
                // not allow image ext type, will throw exception
                mediaType = MediaType.ALL;
                break;
        }

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(mediaType)
                .body(new InputStreamResource(inputStream));
    }
}
