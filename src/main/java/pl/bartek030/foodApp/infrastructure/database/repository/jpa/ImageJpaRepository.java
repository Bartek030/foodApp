package pl.bartek030.foodApp.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartek030.foodApp.infrastructure.database.entity.ImageEntity;

public interface ImageJpaRepository extends JpaRepository<ImageEntity, Long> {
}
