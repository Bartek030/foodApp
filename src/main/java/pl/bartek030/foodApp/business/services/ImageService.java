package pl.bartek030.foodApp.business.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void uploadImage(MultipartFile file, final Long foodId);

    Resource getImage(final String filename);
}
