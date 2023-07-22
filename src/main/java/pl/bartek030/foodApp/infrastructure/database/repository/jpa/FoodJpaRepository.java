package pl.bartek030.foodApp.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.infrastructure.database.entity.AddressEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodEntity;

@Repository
public interface FoodJpaRepository extends JpaRepository<FoodEntity, Long> {
}
