package pl.bartek030.foodApp.api.controller.rest.implementation;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.bartek030.foodApp.api.controller.rest.ImageRestController;
import pl.bartek030.foodApp.api.dto.FileUploadMessage;
import pl.bartek030.foodApp.business.services.ImageService;

@RestController
@AllArgsConstructor
public class ImageRestControllerImpl implements ImageRestController {

    private final ImageService imageService;

    @Override
    public ResponseEntity<FileUploadMessage> uploadImage(final MultipartFile file, final Long foodId) {
        imageService.uploadImage(file, foodId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(FileUploadMessage.of(FileUploadMessage.StatusMessage.SUCCESS));
    }

    @Override
    public ResponseEntity<Resource> getImage(final Long foodId) {
        Resource resource = imageService.getImage(foodId);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
