package pl.bartek030.foodApp.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.business.dao.RestaurantDeliveryAddressDao;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.business.serviceModel.RestaurantDeliveryAddress;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantDeliveryAddressEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.DeliveryAddressDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.RestaurantDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.RestaurantDeliveryAddressDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.RestaurantDeliveryAddressJpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RestaurantDeliveryAddressRepository implements RestaurantDeliveryAddressDao {

    private final RestaurantDeliveryAddressJpaRepository restaurantDeliveryAddressJpaRepository;
    private final RestaurantDeliveryAddressDaoMapper restaurantDeliveryAddressDaoMapper;
    private final DeliveryAddressDaoMapper deliveryAddressDaoMapper;
    private final RestaurantDaoMapper restaurantDaoMapper;

    @Override
    public List<RestaurantDeliveryAddress> findByAddress(final DeliveryAddress deliveryAddress) {
        final List<RestaurantDeliveryAddressEntity> addresses =
                restaurantDeliveryAddressJpaRepository.findByDeliveryAddress(
                        restaurantDeliveryAddressDaoMapper.mapToEntity(deliveryAddress)
                );
        return addresses.stream()
                .map(restaurantDeliveryAddressDaoMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Optional<RestaurantDeliveryAddress> findByAddressAndRestaurant(final DeliveryAddress address, final Restaurant restaurant) {
        final Optional<RestaurantDeliveryAddressEntity> deliveryAddress =
                restaurantDeliveryAddressJpaRepository.findByDeliveryAddressAndRestaurant(
                        deliveryAddressDaoMapper.mapToEntity(address),
                        restaurantDaoMapper.mapToEntity(restaurant)
                );
        return deliveryAddress.map(restaurantDeliveryAddressDaoMapper::mapFromEntity);
    }
}
