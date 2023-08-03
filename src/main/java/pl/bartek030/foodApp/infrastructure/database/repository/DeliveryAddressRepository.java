package pl.bartek030.foodApp.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.business.dao.DeliveryAddressDao;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.infrastructure.database.entity.DeliveryAddressEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.DeliveryAddressDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.DeliveryAddressJpaRepository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class DeliveryAddressRepository implements DeliveryAddressDao {

    private final DeliveryAddressJpaRepository deliveryAddressJpaRepository;

    private final DeliveryAddressDaoMapper deliveryAddressDaoMapper;

    @Override
    public Optional<DeliveryAddress> findByCountryAndCityAndStreet(
            final String country,
            final String city,
            final String street
    ) {
        return deliveryAddressJpaRepository.findByCountryAndCityAndStreet(country, city, street)
                .map(deliveryAddressDaoMapper::mapFromEntity);
    }
}
