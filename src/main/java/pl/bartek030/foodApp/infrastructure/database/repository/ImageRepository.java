package pl.bartek030.foodApp.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.business.dao.ImageDAO;
import pl.bartek030.foodApp.business.serviceModel.Image;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.ImageDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.ImageJpaRepository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class ImageRepository implements ImageDAO {

    private final ImageJpaRepository imageJpaRepository;
    private final ImageDaoMapper imageDaoMapper;

    @Override
    public void saveImage(final Image image) {
        imageJpaRepository.save(imageDaoMapper.mapImageToEntity(image));
    }

    @Override
    public Optional<Image> findById(final Long foodId) {
        return imageJpaRepository.findById(foodId)
                .map(imageDaoMapper::mapFromEntity);
    }
}
