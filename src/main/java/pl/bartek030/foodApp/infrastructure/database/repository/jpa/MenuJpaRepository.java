package pl.bartek030.foodApp.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.infrastructure.database.entity.MenuEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;

import java.util.List;

@Repository
public interface MenuJpaRepository extends JpaRepository<MenuEntity, Long> {
    List<MenuEntity> findAllByRestaurant(RestaurantEntity restaurant);
}
