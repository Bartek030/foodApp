package pl.bartek030.foodApp.business.services.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pl.bartek030.foodApp.business.dao.ImageDAO;
import pl.bartek030.foodApp.business.serviceModel.Image;
import pl.bartek030.foodApp.business.services.ImageService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageDAO imageDAO;

    @Override
    @Transactional
    public void uploadImage(final MultipartFile file, final Long foodId) {
        try {
            final Image image = Image.builder()
                    .id(foodId)
                    .name(file.getName())
                    .content(file.getBytes())
                    .build();
            imageDAO.saveImage(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Resource getImage(final Long foodId) {
        byte[] image = imageDAO.findById(foodId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();
        return new ByteArrayResource(image);
    }
}
