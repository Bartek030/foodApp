package pl.bartek030.foodApp.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.bartek030.foodApp.configuration.PersistenceContainerTestConfiguration;
import pl.bartek030.foodApp.infrastructure.database.entity.AddressEntity;
import pl.bartek030.foodApp.util.AddressEntityExample;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class AddressJpaRepositoryIT {

    private AddressJpaRepository addressJpaRepository;

    @Test
    void shouldReturnAddressEntityByAddressData() {
        // given
        final String country = "COUNTRY1";
        final String city = "CITY1";
        final String street = "STREET1";
        final String number = "22";
        final String zipCode = "38-300";
        AddressEntity address = AddressEntityExample.someAddressEntity1();
        addressJpaRepository.saveAndFlush(address);

        // when
        final Optional<AddressEntity> result = addressJpaRepository.findByCountryAndCityAndStreetAndNumberAndZipCode(
                country, city, street, number, zipCode
        );

        // then
        assertTrue(result.isPresent());
        assertEquals(country, result.get().getCountry());
        assertEquals(city, result.get().getCity());
        assertEquals(street, result.get().getStreet());
        assertEquals(number, result.get().getNumber());
        assertEquals(zipCode, result.get().getZipCode());
    }
}