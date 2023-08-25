package pl.bartek030.foodApp.business.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pl.bartek030.foodApp.business.services.ImageService;
import pl.bartek030.foodApp.infrastructure.database.entity.ImageEntity;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.ImageJpaRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Value("${upload.path}")
    private String uploadPath;

    private final ImageJpaRepository imageJpaRepository;

//    @Override
//    @Transactional
//    public void uploadImage(final MultipartFile file, final Long foodId) {
//        try {
//            Path imagePath = Paths.get(uploadPath + foodId + ".jpg");
//            Files.write(imagePath, file.getBytes());
//        } catch (IOException e) {
//            throw new RuntimeException("Unable to upload image");
//        }
//    }


    @Override
    public void uploadImage(final MultipartFile file, final Long foodId) {
        try {
            final ImageEntity imageEntity = ImageEntity.builder()
                    .id(foodId)
                    .name(file.getName())
                    .content(file.getBytes())
                    .build();
            imageJpaRepository.save(imageEntity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resource getImage(final Long foodId) {
        byte[] image = imageJpaRepository.findById(foodId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();

        return new ByteArrayResource(image);
    }

    //    @Override
//    @Transactional
//    public Resource getImage(final String filename) {
//        try {
//            Path imagePath = Paths.get(uploadPath + filename + ".jpg");
//            final UrlResource imageResource = new UrlResource(imagePath.toUri());
//            if(imageResource.exists()) {
//                return imageResource;
//            } else {
//                Path defaultImagePath = Paths.get(uploadPath + "logo.jpg");
//                return new UrlResource(defaultImagePath.toUri());
//            }
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("Unable to get image");
//        }
//    }
}
