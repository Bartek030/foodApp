package pl.bartek030.foodApp.infrastructure.database.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.business.serviceModel.RestaurantDeliveryAddress;
import pl.bartek030.foodApp.infrastructure.database.entity.DeliveryAddressEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantDeliveryAddressEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.DeliveryAddressDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.RestaurantDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.RestaurantDeliveryAddressDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.RestaurantDeliveryAddressJpaRepository;
import pl.bartek030.foodApp.util.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantDeliveryAddressRepositoryTest {

    @InjectMocks
    private RestaurantDeliveryAddressRepository restaurantDeliveryAddressRepository;

    @Mock
    private RestaurantDeliveryAddressJpaRepository restaurantDeliveryAddressJpaRepository;

    @Mock
    private RestaurantDeliveryAddressDaoMapper restaurantDeliveryAddressDaoMapper;

    @Mock
    private DeliveryAddressDaoMapper deliveryAddressDaoMapper;

    @Mock
    private RestaurantDaoMapper restaurantDaoMapper;

    @Test
    void shouldReturnAllByDeliveryAddress() {
        // given
        final DeliveryAddress deliveryAddress = DeliveryAddressExample.someDeliveryAddress1();

        when(deliveryAddressDaoMapper.mapDeliveryAddressToEntity(any(DeliveryAddress.class)))
                .thenReturn(DeliveryAddressEntityExample.someDeliveryAddressEntity1());
        when(restaurantDeliveryAddressJpaRepository.findByDeliveryAddress(any(DeliveryAddressEntity.class)))
                .thenReturn(List.of(
                        RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity1(),
                        RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity2(),
                        RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity3()
                ));
        when(restaurantDeliveryAddressDaoMapper.mapRestaurantDeliveryAddressFromEntity(
                any(RestaurantDeliveryAddressEntity.class)))
                .thenReturn(RestaurantDeliveryAddressExample.someRestaurantDeliveryAddress1())
                .thenReturn(RestaurantDeliveryAddressExample.someRestaurantDeliveryAddress2())
                .thenReturn(RestaurantDeliveryAddressExample.someRestaurantDeliveryAddress3());

        // when
        final List<RestaurantDeliveryAddress> result =
                restaurantDeliveryAddressRepository.findAllByAddress(deliveryAddress);

        // then
        assertEquals(3, result.size());
    }

    @Test
    void shouldFindRestaurantDeliveryAddressByAddressAndRestaurantCorrectly() {
        // given
        final Restaurant restaurant = RestaurantExample.someRestaurant1();
        final DeliveryAddress deliveryAddress = DeliveryAddressExample.someDeliveryAddress1();

        when(deliveryAddressDaoMapper.mapDeliveryAddressToEntity(any(DeliveryAddress.class)))
                .thenReturn(DeliveryAddressEntityExample.someDeliveryAddressEntity1());
        when(restaurantDaoMapper.mapRestaurantToEntity(any(Restaurant.class)))
                .thenReturn(RestaurantEntityExample.someRestaurantEntity1());
        when(restaurantDeliveryAddressJpaRepository.findByDeliveryAddressAndRestaurant(
                any(DeliveryAddressEntity.class), any(RestaurantEntity.class)))
                .thenReturn(Optional.of(RestaurantDeliveryAddressEntityExample
                        .someRestaurantDeliveryAddressEntity1()));
        when(restaurantDeliveryAddressDaoMapper.mapRestaurantDeliveryAddressFromEntity(
                any(RestaurantDeliveryAddressEntity.class)))
                .thenReturn(RestaurantDeliveryAddressExample.someRestaurantDeliveryAddress1());

        // when
        final Optional<RestaurantDeliveryAddress> result =
                restaurantDeliveryAddressRepository.findByAddressAndRestaurant(deliveryAddress, restaurant);

        // then
        assertTrue(result.isPresent());
        assertEquals(50, result.get().getDeliveryTime());
    }

    @Test
    void shouldFindAllByRestaurant() {
        // given
        final Restaurant restaurant = RestaurantExample.someRestaurant1();

        when(restaurantDaoMapper.mapRestaurantToEntity(any(Restaurant.class)))
                .thenReturn(RestaurantEntityExample.someRestaurantEntity1());
        when(restaurantDeliveryAddressJpaRepository.findByRestaurant(any(RestaurantEntity.class)))
                .thenReturn(List.of(
                        RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity1(),
                        RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity2(),
                        RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity3()
                ));
        when(restaurantDeliveryAddressDaoMapper.mapRestaurantDeliveryAddressFromEntity(
                any(RestaurantDeliveryAddressEntity.class)))
                .thenReturn(RestaurantDeliveryAddressExample.someRestaurantDeliveryAddress1())
                .thenReturn(RestaurantDeliveryAddressExample.someRestaurantDeliveryAddress2())
                .thenReturn(RestaurantDeliveryAddressExample.someRestaurantDeliveryAddress3());

        // when
        final List<RestaurantDeliveryAddress> result =
                restaurantDeliveryAddressRepository.findAllByRestaurant(restaurant);

        // then
        assertEquals(3, result.size());
    }

    @Test
    void shouldFindAllByDeliveryAddress() {
        // given
        final DeliveryAddress deliveryAddress = DeliveryAddressExample.someDeliveryAddress1();

        when(deliveryAddressDaoMapper.mapDeliveryAddressToEntity(any(DeliveryAddress.class)))
                .thenReturn(DeliveryAddressEntityExample.someDeliveryAddressEntity1());
        when(restaurantDeliveryAddressJpaRepository.findByDeliveryAddress(any(DeliveryAddressEntity.class)))
                .thenReturn(List.of(
                        RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity1(),
                        RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity2(),
                        RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity3()
                ));
        when(restaurantDeliveryAddressDaoMapper.mapRestaurantDeliveryAddressFromEntity(
                any(RestaurantDeliveryAddressEntity.class)))
                .thenReturn(RestaurantDeliveryAddressExample.someRestaurantDeliveryAddress1())
                .thenReturn(RestaurantDeliveryAddressExample.someRestaurantDeliveryAddress2())
                .thenReturn(RestaurantDeliveryAddressExample.someRestaurantDeliveryAddress3());

        // when
        final List<RestaurantDeliveryAddress> result =
                restaurantDeliveryAddressRepository.findByDeliveryAddress(deliveryAddress);

        // then
        assertEquals(3, result.size());
    }

    @Test
    void shouldAddRestaurantDeliveryAddressCorrectly() {
        // given
        final RestaurantDeliveryAddress restaurantDeliveryAddress =
                RestaurantDeliveryAddressExample.someRestaurantDeliveryAddress1();

        when(restaurantDeliveryAddressDaoMapper.mapRestaurantDeliveryAddressToEntity(any(RestaurantDeliveryAddress.class)))
                .thenReturn(RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity1());
        when(restaurantDeliveryAddressJpaRepository.save(any(RestaurantDeliveryAddressEntity.class)))
                .thenReturn(RestaurantDeliveryAddressEntityExample.someRestaurantDeliveryAddressEntity1());
        // when
        restaurantDeliveryAddressRepository.addRestaurantDeliveryAddress(restaurantDeliveryAddress);

        // then
        verify(restaurantDeliveryAddressDaoMapper, times(1))
                .mapRestaurantDeliveryAddressToEntity(any(RestaurantDeliveryAddress.class));
    }
}