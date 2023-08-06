package pl.bartek030.foodApp.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.infrastructure.database.entity.AppOrderEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodAppUserEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;

import java.util.List;

@Repository
public interface AppOrderJpaRepository extends JpaRepository<AppOrderEntity, Long> {
    List<AppOrderEntity> findAllByFoodAppUser(FoodAppUserEntity foodAppUser);

    List<AppOrderEntity> findAllByRestaurant(RestaurantEntity restaurantEntity);
}
