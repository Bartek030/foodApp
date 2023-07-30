package pl.bartek030.foodApp.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.business.dao.RestaurantDeliveryAddressDao;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.business.serviceModel.RestaurantDeliveryAddress;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantDeliveryAddressEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.RestaurantDeliveryAddressDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.RestaurantDeliveryAddressJpaRepository;

import java.util.List;

@Repository
@AllArgsConstructor
public class RestaurantDeliveryAddressRepository implements RestaurantDeliveryAddressDao {

    private final RestaurantDeliveryAddressJpaRepository repository;
    private final RestaurantDeliveryAddressDaoMapper mapper;

    @Override
    public List<RestaurantDeliveryAddress> findByAddress(final DeliveryAddress deliveryAddress) {
        final List<RestaurantDeliveryAddressEntity> addresses =
                repository.findByDeliveryAddress(mapper.mapToEntity(deliveryAddress));
        return addresses.stream()
                .map(mapper::mapFromEntity)
                .toList();
    }
}
