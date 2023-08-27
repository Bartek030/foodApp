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
import pl.bartek030.foodApp.infrastructure.database.entity.FoodAppUserEntity;
import pl.bartek030.foodApp.util.AddressEntityExample;
import pl.bartek030.foodApp.util.FoodAppUserEntityExample;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class FoodAppUserJpaRepositoryTest {

    private FoodAppUserJpaRepository foodAppUserJpaRepository;
    private AddressJpaRepository addressJpaRepository;

    @Test
    void shouldReturnFoodAppUserByEmail() {
        // given
        final AddressEntity addressEntity = AddressEntityExample.someAddressEntity1();
        final FoodAppUserEntity foodAppUserEntity = FoodAppUserEntityExample.someFoodAppUserEntity1()
                .withAddress(addressEntity);

        addressJpaRepository.saveAndFlush(addressEntity);
        foodAppUserJpaRepository.saveAndFlush(foodAppUserEntity);

        // when
        final Optional<FoodAppUserEntity> result = foodAppUserJpaRepository.findByEmail(foodAppUserEntity.getEmail());

        // then
        assertTrue(result.isPresent());
        assertEquals(foodAppUserEntity.getEmail(), result.get().getEmail());
    }
}