package pl.bartek030.foodApp.business.dao;

import pl.bartek030.foodApp.business.serviceModel.Image;

import java.util.Optional;

public interface ImageDAO {
    void saveImage(Image image);

    Optional<Image> findById(Long foodId);
}
