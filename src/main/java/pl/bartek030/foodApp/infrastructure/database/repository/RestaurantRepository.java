package pl.bartek030.foodApp.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.business.dao.RestaurantDAO;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.RestaurantDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.RestaurantJpaRepository;

@Repository
@AllArgsConstructor
public class RestaurantRepository implements RestaurantDAO {

    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantDaoMapper restaurantDaoMapper;

    @Override
    public void addRestaurant(final Restaurant restaurant) {
        restaurantJpaRepository.save(restaurantDaoMapper.mapToEntity(restaurant));
    }
}
