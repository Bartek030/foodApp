package pl.bartek030.foodApp.infrastructure.database.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartek030.foodApp.business.serviceModel.AppOrder;
import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.infrastructure.database.entity.AppOrderEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodAppUserEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.AppOrderDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.FoodAppUserDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.RestaurantDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.enums.OrderStatus;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.AppOrderJpaRepository;
import pl.bartek030.foodApp.util.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppOrderRepositoryTest {

    @InjectMocks
    private AppOrderRepository appOrderRepository;

    @Mock
    private AppOrderJpaRepository appOrderJpaRepository;

    @Mock
    AppOrderDaoMapper appOrderDaoMapper;

    @Mock
    FoodAppUserDaoMapper foodAppUserDaoMapper;

    @Mock
    RestaurantDaoMapper restaurantDaoMapper;

    @Test
    void shouldFindAppOrderById() {
        // given
        Long id = 1L;

        when(appOrderJpaRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(AppOrderEntityExample.someAppOrderEntity1()));
        when(appOrderDaoMapper.mapAppOrderFromEntityWithCollections(any(AppOrderEntity.class)))
                .thenReturn(AppOrderExample.someAppOrder1());

        // when
        final Optional<AppOrder> result = appOrderRepository.findById(id);

        // then
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getAppOrderId());
    }

    @Test
    void shouldCountAllAppOrdersCorrectly() {
        // given
        Long appOrdersNumber = 10L;
        when(appOrderJpaRepository.count()).thenReturn(appOrdersNumber);

        // when
        final Long result = appOrderRepository.countAllRecords();

        // then
        assertEquals(appOrdersNumber, result);
    }

    @Test
    void shouldAddAppOrderCorrectly() {
        // given
        final AppOrder appOrder = AppOrderExample.someAppOrder1();

        when(appOrderDaoMapper.mapAppOrderToEntity(any(AppOrder.class)))
                .thenReturn(AppOrderEntityExample.someAppOrderEntity1());
        when(appOrderJpaRepository.save(any(AppOrderEntity.class)))
                .thenReturn(AppOrderEntityExample.someAppOrderEntity1());
        when(appOrderDaoMapper.mapAppOrderFromEntity(any(AppOrderEntity.class)))
                .thenReturn(AppOrderExample.someAppOrder1());

        // when
        final AppOrder result = appOrderRepository.addAppOrder(appOrder);

        // then
        assertEquals(appOrder.getTotalCost(), result.getTotalCost());
        assertEquals(appOrder.getOrderedAt(), result.getOrderedAt());
        assertEquals(appOrder.getStatus(), result.getStatus());
        assertEquals(appOrder.getPlannedDeliveryTime(), result.getPlannedDeliveryTime());
    }

    @Test
    void shouldFindAppOrdersByRestaurant() {
        // given
        final Restaurant restaurant = RestaurantExample.someRestaurant1();

        when(restaurantDaoMapper.mapRestaurantToEntity(any(Restaurant.class)))
                .thenReturn(RestaurantEntityExample.someRestaurantEntity1());
        when(appOrderJpaRepository.findAllByRestaurant(any(RestaurantEntity.class)))
                .thenReturn(List.of(
                        AppOrderEntityExample.someAppOrderEntity1(),
                        AppOrderEntityExample.someAppOrderEntity2(),
                        AppOrderEntityExample.someAppOrderEntity3()
                ));
        when(appOrderDaoMapper.mapAppOrderFromEntityWithCollections(any(AppOrderEntity.class)))
                .thenReturn(AppOrderExample.someAppOrder1())
                .thenReturn(AppOrderExample.someAppOrder2())
                .thenReturn(AppOrderExample.someAppOrder3());

        // when
        final List<AppOrder> result = appOrderRepository.getAppOrdersByRestaurant(restaurant);

        // then
        assertEquals(3, result.size());
    }

    @Test
    void shouldFindAppOrdersByFoodAppUser() {
        // given
        final FoodAppUser foodAppUser = FoodAppUserExample.someFoodAppUser1();

        when(foodAppUserDaoMapper.mapFoodAppUserToEntity(any(FoodAppUser.class)))
                .thenReturn(FoodAppUserEntityExample.someFoodAppUserEntity1());
        when(appOrderJpaRepository.findAllByFoodAppUser(any(FoodAppUserEntity.class)))
                .thenReturn(List.of(
                        AppOrderEntityExample.someAppOrderEntity1(),
                        AppOrderEntityExample.someAppOrderEntity2(),
                        AppOrderEntityExample.someAppOrderEntity3()
                ));
        when(appOrderDaoMapper.mapAppOrderFromEntityWithCollections(any(AppOrderEntity.class)))
                .thenReturn(AppOrderExample.someAppOrder1())
                .thenReturn(AppOrderExample.someAppOrder2())
                .thenReturn(AppOrderExample.someAppOrder3());

        // when
        final List<AppOrder> result = appOrderRepository.getAppOrdersByUser(foodAppUser);

        // then
        assertEquals(3, result.size());
    }

    @Test
    void shouldUpdateAppOrderCorrectly() {
        // given
        Long id = 1L;

        when(appOrderJpaRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(AppOrderEntityExample.someAppOrderEntity1()));
        when(appOrderJpaRepository.save(any(AppOrderEntity.class)))
                .thenReturn(AppOrderEntityExample.someAppOrderEntity1());
        when(appOrderDaoMapper.mapAppOrderFromEntity(any(AppOrderEntity.class)))
                .thenReturn(AppOrderExample.someAppOrder1().withStatus(OrderStatus.DELIVERED));

        // when
        final AppOrder result = appOrderRepository.update(id, OrderStatus.DELIVERED);

        // then
        assertEquals(OrderStatus.DELIVERED, result.getStatus());
    }
}