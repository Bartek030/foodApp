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
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;
import pl.bartek030.foodApp.util.AddressEntityExample;
import pl.bartek030.foodApp.util.FoodAppUserEntityExample;
import pl.bartek030.foodApp.util.RestaurantEntityExample;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class RestaurantJpaRepositoryIT {

    private RestaurantJpaRepository restaurantJpaRepository;
    private FoodAppUserJpaRepository foodAppUserJpaRepository;
    private AddressJpaRepository addressJpaRepository;

    @Test
    void shouldFindRestaurantsByIdList() {
        // given

        final AddressEntity addressEntity1 = addressJpaRepository.saveAndFlush(AddressEntityExample.someAddressEntity1());
        final AddressEntity addressEntity2 = addressJpaRepository.saveAndFlush(AddressEntityExample.someAddressEntity2());
        final AddressEntity addressEntity3 = addressJpaRepository.saveAndFlush(AddressEntityExample.someAddressEntity3());

        final FoodAppUserEntity foodAppUserEntity =
                foodAppUserJpaRepository.saveAndFlush(FoodAppUserEntityExample.someFoodAppUserEntity1()
                        .withAddress(addressEntity1));

        final RestaurantEntity entity1 = RestaurantEntityExample.someRestaurantEntity1()
                .withFoodAppUser(foodAppUserEntity)
                .withAddress(addressEntity1);
        final RestaurantEntity entity2 = RestaurantEntityExample.someRestaurantEntity2()
                .withFoodAppUser(foodAppUserEntity)
                .withAddress(addressEntity2);
        final RestaurantEntity entity3 = RestaurantEntityExample.someRestaurantEntity3()
                .withFoodAppUser(foodAppUserEntity)
                .withAddress(addressEntity3);

        restaurantJpaRepository.saveAllAndFlush(List.of(entity1, entity2, entity3));

        // when
        final List<RestaurantEntity> result = restaurantJpaRepository.findAllById(List.of(
                entity1.getRestaurantId(),
                entity3.getRestaurantId()
        ));

        // then
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnAllRestaurantsByFoodAppUser() {
        // given
        final AddressEntity addressEntity1 = addressJpaRepository.saveAndFlush(AddressEntityExample.someAddressEntity1());
        final AddressEntity addressEntity2 = addressJpaRepository.saveAndFlush(AddressEntityExample.someAddressEntity2());
        final AddressEntity addressEntity3 = addressJpaRepository.saveAndFlush(AddressEntityExample.someAddressEntity3());

        final FoodAppUserEntity foodAppUserEntity =
                foodAppUserJpaRepository.saveAndFlush(FoodAppUserEntityExample.someFoodAppUserEntity1()
                        .withAddress(addressEntity1));

        final RestaurantEntity entity1 = RestaurantEntityExample.someRestaurantEntity1()
                .withFoodAppUser(foodAppUserEntity)
                .withAddress(addressEntity1);
        final RestaurantEntity entity2 = RestaurantEntityExample.someRestaurantEntity2()
                .withFoodAppUser(foodAppUserEntity)
                .withAddress(addressEntity2);
        final RestaurantEntity entity3 = RestaurantEntityExample.someRestaurantEntity3()
                .withFoodAppUser(foodAppUserEntity)
                .withAddress(addressEntity3);

        foodAppUserJpaRepository.saveAndFlush(foodAppUserEntity);
        restaurantJpaRepository.saveAllAndFlush(List.of(entity1, entity2, entity3));

        // when
        final List<RestaurantEntity> result = restaurantJpaRepository.findAllByFoodAppUser(foodAppUserEntity);

        // then
        assertEquals(3, result.size());
    }
}