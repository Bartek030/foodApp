package pl.bartek030.foodApp.api.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.bartek030.foodApp.api.dto.FileUploadMessage;

@RequestMapping(ImageController.IMAGE_URL)
public interface ImageController {

    String IMAGE_URL = "/image";
    String UPLOAD_IMAGE_URL = "/new/{foodId}";
    String GET_IMAGE_URL = "/{foodId}";

    @PostMapping(UPLOAD_IMAGE_URL)
    ResponseEntity<FileUploadMessage> uploadImage(
            @RequestParam("file") MultipartFile file,
            @PathVariable Long foodId
    );

    @GetMapping(value = GET_IMAGE_URL, produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<Resource> getImage(@PathVariable String foodId);
}
