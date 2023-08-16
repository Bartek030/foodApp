package pl.bartek030.foodApp.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.bartek030.foodApp.configuration.PersistenceContainerTestConfiguration;
import pl.bartek030.foodApp.infrastructure.database.entity.DeliveryAddressEntity;
import pl.bartek030.foodApp.util.DeliveryAddressEntityExample;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class DeliveryAddressJpaRepositoryTest {

    private DeliveryAddressJpaRepository deliveryAddressJpaRepository;

    @Test
    void shouldReturnDeliveryAddressEntityByAddressData() {
        // given
        final String country = "COUNTRY1";
        final String city = "CITY1";
        final String street = "STREET1";
        final DeliveryAddressEntity deliveryAddressEntity = DeliveryAddressEntityExample.someDeliveryAddressEntity1();
        deliveryAddressJpaRepository.saveAndFlush(deliveryAddressEntity);

        // when
        final Optional<DeliveryAddressEntity> result =
                deliveryAddressJpaRepository.findByCountryAndCityAndStreet(country, city, street);

        // then
        assertTrue(result.isPresent());
        assertEquals(country, result.get().getCountry());
        assertEquals(city, result.get().getCity());
        assertEquals(street, result.get().getStreet());
    }
}