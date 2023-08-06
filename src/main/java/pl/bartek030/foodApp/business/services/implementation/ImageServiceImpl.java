package pl.bartek030.foodApp.business.services.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.bartek030.foodApp.business.services.ImageService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void uploadImage(final MultipartFile file, final Long foodId) {
        try {
            Path imagePath = Paths.get(uploadPath + foodId + ".jpg");
            Files.write(imagePath, file.getBytes());
        } catch (IOException e) {
            // TODO: CUSTOM EXCEPTION USING ENUM STATUS MESSAGE
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resource getImage(final String filename) {
        try {
            Path imagePath = Paths.get(uploadPath + filename + ".jpg");
            final UrlResource imageResource = new UrlResource(imagePath.toUri());
            if(imageResource.exists()) {
                return imageResource;
            } else {
                Path defaultImagePath = Paths.get(uploadPath + "logo.jpg");
                return new UrlResource(defaultImagePath.toUri());
            }
        } catch (MalformedURLException e) {
            // TODO: CUSTOM EXCEPTION OR LEAVE NULL
            return null;
        }
    }
}
