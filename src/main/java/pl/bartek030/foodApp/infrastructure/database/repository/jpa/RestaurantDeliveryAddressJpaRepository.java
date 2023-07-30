package pl.bartek030.foodApp.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.infrastructure.database.entity.DeliveryAddressEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantDeliveryAddressEntity;

import java.util.List;

@Repository
public interface RestaurantDeliveryAddressJpaRepository extends JpaRepository<RestaurantDeliveryAddressEntity, Long> {
    List<RestaurantDeliveryAddressEntity> findByDeliveryAddress(DeliveryAddressEntity deliveryAddressEntity);
}
