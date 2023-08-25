package pl.bartek030.foodApp.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(ImageController.IMAGE_URL)
public interface ImageController {

    String IMAGE_URL = "/image";
    String UPLOAD_IMAGE_URL = "/new";

    @PostMapping(UPLOAD_IMAGE_URL)
    String uploadImage(@RequestParam("file") MultipartFile file, @RequestParam Long foodId);
}
