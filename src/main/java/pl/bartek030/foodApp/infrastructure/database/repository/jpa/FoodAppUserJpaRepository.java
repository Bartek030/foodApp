package pl.bartek030.foodApp.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodAppUserEntity;

import java.util.Optional;

@Repository
public interface FoodAppUserJpaRepository extends JpaRepository<FoodAppUserEntity, Long> {
    Optional<FoodAppUserEntity> findByEmail(String email);
}
