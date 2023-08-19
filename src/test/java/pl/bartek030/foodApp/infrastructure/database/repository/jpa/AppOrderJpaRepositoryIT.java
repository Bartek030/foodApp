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
import pl.bartek030.foodApp.infrastructure.database.entity.AppOrderEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodAppUserEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;
import pl.bartek030.foodApp.util.AddressEntityExample;
import pl.bartek030.foodApp.util.AppOrderEntityExample;
import pl.bartek030.foodApp.util.FoodAppUserEntityExample;
import pl.bartek030.foodApp.util.RestaurantEntityExample;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class AppOrderJpaRepositoryIT {

    private AppOrderJpaRepository appOrderJpaRepository;
    private RestaurantJpaRepository restaurantJpaRepository;
    private FoodAppUserJpaRepository foodAppUserJpaRepository;
    private AddressJpaRepository addressJpaRepository;

    @Test
    void shouldFindAppOrderListByFoodAppUser() {
        // given
        final AddressEntity addressEntity = addressJpaRepository.saveAndFlush(AddressEntityExample.someAddressEntity1());

        final FoodAppUserEntity foodAppUserEntity =
                foodAppUserJpaRepository.saveAndFlush(FoodAppUserEntityExample.someFoodAppUserEntity1()
                        .withAddress(addressEntity));

        final RestaurantEntity restaurantEntity =
                restaurantJpaRepository.saveAndFlush(RestaurantEntityExample.someRestaurantEntity1()
                .withFoodAppUser(foodAppUserEntity)
                .withAddress(addressEntity));

        final AppOrderEntity entity1 = AppOrderEntityExample.someAppOrderEntity1()
                .withRestaurant(restaurantEntity)
                .withFoodAppUser(foodAppUserEntity);
        final AppOrderEntity entity2 = AppOrderEntityExample.someAppOrderEntity2()
                .withRestaurant(restaurantEntity)
                .withFoodAppUser(foodAppUserEntity);
        final AppOrderEntity entity3 = AppOrderEntityExample.someAppOrderEntity3()
                .withRestaurant(restaurantEntity)
                .withFoodAppUser(foodAppUserEntity);

        appOrderJpaRepository.saveAllAndFlush(List.of(entity1, entity2, entity3));

        // when
        final List<AppOrderEntity> result = appOrderJpaRepository.findAllByFoodAppUser(foodAppUserEntity);

        // then
        assertEquals(3, result.size());
    }

    @Test
    void shouldFindAppOrderListByRestaurant() {
        // given
        final AddressEntity addressEntity = addressJpaRepository.saveAndFlush(AddressEntityExample.someAddressEntity1());

        final FoodAppUserEntity foodAppUserEntity =
                foodAppUserJpaRepository.saveAndFlush(FoodAppUserEntityExample.someFoodAppUserEntity1()
                .withAddress(addressEntity));

        final RestaurantEntity restaurantEntity =
                restaurantJpaRepository.saveAndFlush(RestaurantEntityExample.someRestaurantEntity1()
                        .withAddress(addressEntity)
                        .withFoodAppUser(foodAppUserEntity));

        final AppOrderEntity entity1 = AppOrderEntityExample.someAppOrderEntity1()
                .withRestaurant(restaurantEntity)
                .withFoodAppUser(foodAppUserEntity);
        final AppOrderEntity entity2 = AppOrderEntityExample.someAppOrderEntity2()
                .withRestaurant(restaurantEntity)
                .withFoodAppUser(foodAppUserEntity);
        final AppOrderEntity entity3 = AppOrderEntityExample.someAppOrderEntity3()
                .withRestaurant(restaurantEntity)
                .withFoodAppUser(foodAppUserEntity);

        appOrderJpaRepository.saveAllAndFlush(List.of(entity1, entity2, entity3));

        // when
        final List<AppOrderEntity> result = appOrderJpaRepository.findAllByRestaurant(restaurantEntity);

        // then
        assertEquals(3, result.size());
    }
}