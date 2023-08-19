package pl.bartek030.foodApp.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.bartek030.foodApp.configuration.PersistenceContainerTestConfiguration;
import pl.bartek030.foodApp.infrastructure.database.entity.*;
import pl.bartek030.foodApp.util.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class RestaurantDeliveryAddressJpaRepositoryIT {

    private RestaurantDeliveryAddressJpaRepository restaurantDeliveryAddressJpaRepository;
    private DeliveryAddressJpaRepository deliveryAddressJpaRepository;
    private RestaurantJpaRepository restaurantJpaRepository;
    private AddressJpaRepository addressJpaRepository;
    private FoodAppUserJpaRepository foodAppUserJpaRepository;

    @Test
    void shouldReturnRestaurantDeliveryAddressListByDeliveryAddress() {
        // given
        final DeliveryAddressEntity deliveryAddressEntity =
                deliveryAddressJpaRepository.saveAndFlush(DeliveryAddressEntityExample.someDeliveryAddressEntity1());

        final AddressEntity addressEntity = addressJpaRepository.saveAndFlush(AddressEntityExample.someAddressEntity1());

        final FoodAppUserEntity foodAppUserEntity =
                foodAppUserJpaRepository.saveAndFlush(FoodAppUserEntityExample.someFoodAppUserEntity1()
                .withAddress(addressEntity));

        final RestaurantEntity restaurantEntity =
                restaurantJpaRepository.saveAndFlush(RestaurantEntityExample.someRestaurantEntity1()
                .withAddress(addressEntity)
                .withFoodAppUser(foodAppUserEntity));

        final RestaurantDeliveryAddressEntity entity1 =
                RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity1()
                        .withDeliveryAddress(deliveryAddressEntity)
                        .withRestaurant(restaurantEntity);
        final RestaurantDeliveryAddressEntity entity2 =
                RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity2()
                        .withDeliveryAddress(deliveryAddressEntity)
                        .withRestaurant(restaurantEntity);
        final RestaurantDeliveryAddressEntity entity3 =
                RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity3()
                        .withDeliveryAddress(deliveryAddressEntity)
                        .withRestaurant(restaurantEntity);

        restaurantDeliveryAddressJpaRepository.saveAllAndFlush(List.of(entity1, entity2, entity3));

        // when
        final List<RestaurantDeliveryAddressEntity> result =
                restaurantDeliveryAddressJpaRepository.findByDeliveryAddress(deliveryAddressEntity);

        // then
        assertEquals(3, result.size());
    }

    @Test
    void shouldReturnRestaurantDeliveryAddressByDeliveryAddressAndRestaurant() {
        // given
        final AddressEntity addressEntity = addressJpaRepository.saveAndFlush(AddressEntityExample.someAddressEntity1());

        final FoodAppUserEntity foodAppUserEntity =
                foodAppUserJpaRepository.saveAndFlush(FoodAppUserEntityExample.someFoodAppUserEntity1()
                        .withAddress(addressEntity));

        final DeliveryAddressEntity deliveryAddressEntity =
                deliveryAddressJpaRepository.saveAndFlush(DeliveryAddressEntityExample.someDeliveryAddressEntity1());

        final RestaurantEntity restaurantEntity =
                restaurantJpaRepository.saveAndFlush(RestaurantEntityExample.someRestaurantEntity1()
                        .withFoodAppUser(foodAppUserEntity)
                        .withAddress(addressEntity));

        restaurantDeliveryAddressJpaRepository
                .saveAndFlush(RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity1()
                        .withDeliveryAddress(deliveryAddressEntity)
                        .withRestaurant(restaurantEntity));

        // when
        final Optional<RestaurantDeliveryAddressEntity> result =
                restaurantDeliveryAddressJpaRepository.findByDeliveryAddressAndRestaurant(
                        deliveryAddressEntity, restaurantEntity
                );

        // then
        assertTrue(result.isPresent());
        assertEquals(30, result.get().getDeliveryTime());
    }

    @Test
    void shouldReturnRestaurantDeliveryAddressesByRestaurant() {
        // given
        final AddressEntity addressEntity = addressJpaRepository.saveAndFlush(AddressEntityExample.someAddressEntity1());

        final FoodAppUserEntity foodAppUserEntity =
                foodAppUserJpaRepository.saveAndFlush(FoodAppUserEntityExample.someFoodAppUserEntity1()
                .withAddress(addressEntity));

        final RestaurantEntity restaurantEntity =
                restaurantJpaRepository.saveAndFlush(RestaurantEntityExample.someRestaurantEntity2()
                        .withFoodAppUser(foodAppUserEntity)
                        .withAddress(addressEntity));

        final DeliveryAddressEntity deliveryAddressEntity =
                deliveryAddressJpaRepository.saveAndFlush(DeliveryAddressEntityExample.someDeliveryAddressEntity1());

        final RestaurantDeliveryAddressEntity entity1 =
                RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity1()
                        .withDeliveryAddress(deliveryAddressEntity)
                        .withRestaurant(restaurantEntity);
        final RestaurantDeliveryAddressEntity entity2 =
                RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity2()
                        .withDeliveryAddress(deliveryAddressEntity)
                        .withRestaurant(restaurantEntity);
        final RestaurantDeliveryAddressEntity entity3 =
                RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity3()
                        .withDeliveryAddress(deliveryAddressEntity)
                        .withRestaurant(restaurantEntity);

        deliveryAddressJpaRepository.saveAndFlush(DeliveryAddressEntityExample.someDeliveryAddressEntity1());
        restaurantDeliveryAddressJpaRepository.saveAllAndFlush(List.of(entity1, entity2, entity3));

        // when
        final List<RestaurantDeliveryAddressEntity> result =
                restaurantDeliveryAddressJpaRepository.findByRestaurant(restaurantEntity);

        // then
        assertEquals(2, result.size());
    }
}