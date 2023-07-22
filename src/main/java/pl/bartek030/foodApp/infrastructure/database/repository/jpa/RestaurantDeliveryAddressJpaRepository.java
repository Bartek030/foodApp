package pl.bartek030.foodApp.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.infrastructure.database.entity.AddressEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantDeliveryAddressEntity;

@Repository
public interface RestaurantDeliveryAddressJpaRepository extends JpaRepository<RestaurantDeliveryAddressEntity, Long> {
}
