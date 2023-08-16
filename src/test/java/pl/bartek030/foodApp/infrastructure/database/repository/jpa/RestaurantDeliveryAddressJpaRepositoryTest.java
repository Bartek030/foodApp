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
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantDeliveryAddressEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;
import pl.bartek030.foodApp.util.DeliveryAddressEntityExample;
import pl.bartek030.foodApp.util.RestaurantDeliveryAddressEntityExample;
import pl.bartek030.foodApp.util.RestaurantEntityExample;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class RestaurantDeliveryAddressJpaRepositoryTest {

    private RestaurantDeliveryAddressJpaRepository restaurantDeliveryAddressJpaRepository;
    private DeliveryAddressJpaRepository deliveryAddressJpaRepository;
    private RestaurantJpaRepository restaurantJpaRepository;

    @Test
    void shouldReturnRestaurantDeliveryAddressListByDeliveryAddress() {
        // given
        final DeliveryAddressEntity deliveryAddressEntity =
                deliveryAddressJpaRepository.saveAndFlush(DeliveryAddressEntityExample.someDeliveryAddressEntity1());
        final RestaurantDeliveryAddressEntity entity1 =
                RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity1();
        final RestaurantDeliveryAddressEntity entity2 =
                RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity2();
        final RestaurantDeliveryAddressEntity entity3 =
                RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity3();

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
        final DeliveryAddressEntity deliveryAddressEntity =
                deliveryAddressJpaRepository.saveAndFlush(DeliveryAddressEntityExample.someDeliveryAddressEntity1());
        final RestaurantEntity restaurantEntity =
                restaurantJpaRepository.saveAndFlush(RestaurantEntityExample.someRestaurantEntity1());
        restaurantDeliveryAddressJpaRepository
                .saveAndFlush(RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity1());
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
        final RestaurantEntity restaurantEntity =
                restaurantJpaRepository.saveAndFlush(RestaurantEntityExample.someRestaurantEntity1());
        final RestaurantDeliveryAddressEntity entity1 =
                RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity1();
        final RestaurantDeliveryAddressEntity entity2 =
                RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity2();
        final RestaurantDeliveryAddressEntity entity3 =
                RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity3();

        restaurantDeliveryAddressJpaRepository.saveAllAndFlush(List.of(entity1, entity2, entity3));

        // when
        final List<RestaurantDeliveryAddressEntity> result =
                restaurantDeliveryAddressJpaRepository.findByRestaurant(restaurantEntity);

        // then
        assertEquals(3, result.size());
    }
}