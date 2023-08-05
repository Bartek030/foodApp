package pl.bartek030.foodApp.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.business.dao.RestaurantDAO;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.RestaurantDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.RestaurantJpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RestaurantRepository implements RestaurantDAO {

    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantDaoMapper restaurantDaoMapper;

    @Override
    public Optional<Restaurant> findById(final Long restaurantId) {
        return restaurantJpaRepository.findById(restaurantId)
                .map(restaurantDaoMapper::mapRestaurantFromEntity);
    }

    @Override
    public void addRestaurant(final Restaurant restaurant) {
        restaurantJpaRepository.save(restaurantDaoMapper.mapRestaurantToEntity(restaurant));
    }

    @Override
    public List<Restaurant> findRestaurantsByIdList(final List<Long> restaurantsIdList, final Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("name"));
        final Page<RestaurantEntity> allById = restaurantJpaRepository.findAllById(restaurantsIdList, pageable);
        return allById.stream()
                .map(restaurantDaoMapper::mapRestaurantFromEntity)
                .toList();
    }
}
