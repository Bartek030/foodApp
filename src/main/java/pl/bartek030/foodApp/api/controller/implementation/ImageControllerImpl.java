package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import pl.bartek030.foodApp.api.controller.ImageController;
import pl.bartek030.foodApp.business.services.ImageService;

@Controller
@AllArgsConstructor
public class ImageControllerImpl implements ImageController {

    private final ImageService imageService;

    @Override
    public String uploadImage(final MultipartFile file, final Long foodId) {
        imageService.uploadImage(file, foodId);
        return "add-picture-success";
    }
}
