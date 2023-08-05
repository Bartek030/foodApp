package pl.bartek030.foodApp.infrastructure.database.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodAppUserEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;

import java.util.List;

@Repository
public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity, Long> {
    @Query(value = """
            SELECT rst FROM RestaurantEntity rst
            WHERE rst.restaurantId IN (:idList)
            """)
    Page<RestaurantEntity> findAllById(@Param("idList") List<Long> restaurantsIdList, Pageable pageable);

    List<RestaurantEntity> findAllByFoodAppUser(FoodAppUserEntity foodAppUserEntity);
}
