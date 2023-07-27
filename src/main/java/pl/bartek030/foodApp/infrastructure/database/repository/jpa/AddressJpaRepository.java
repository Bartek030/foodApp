package pl.bartek030.foodApp.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bartek030.foodApp.infrastructure.database.entity.AddressEntity;

import java.util.Optional;

@Repository
public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {

    Optional<AddressEntity> findByCountryAndCityAndStreetAndNumberAndZipCode(
            final String country,
            final String city,
            final String street,
            final String number,
            final String zipCode
    );
}
