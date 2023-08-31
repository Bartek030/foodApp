package pl.bartek030.foodApp.infrastructure.database.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodAppUserEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.FoodAppUserDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.RestaurantDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.bartek030.foodApp.util.FoodAppUserEntityExample;
import pl.bartek030.foodApp.util.FoodAppUserExample;
import pl.bartek030.foodApp.util.RestaurantEntityExample;
import pl.bartek030.foodApp.util.RestaurantExample;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantRepositoryTest {

    @InjectMocks
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantJpaRepository restaurantJpaRepository;

    @Mock
    private RestaurantDaoMapper restaurantDaoMapper;

    @Mock
    private FoodAppUserDaoMapper foodAppUserDaoMapper;

    @Test
    void shouldFindByIdCorrectly() {
        // given
        Long id = 1L;

        when(restaurantJpaRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(RestaurantEntityExample.someRestaurantEntity1()));
        when(restaurantDaoMapper.mapRestaurantFromEntity(any(RestaurantEntity.class)))
                .thenReturn(RestaurantExample.someRestaurant1());

        // when
        final Optional<Restaurant> result = restaurantRepository.findById(id);

        // then
        assertTrue(result.isPresent());
        assertEquals("SOME RESTAURANT 1", result.get().getName());
    }

    @Test
    void shouldAddRestaurantCorrectly() {
        // given
        final Restaurant restaurant = RestaurantExample.someRestaurant1();

        when(restaurantDaoMapper.mapRestaurantToEntity(any(Restaurant.class)))
                .thenReturn(RestaurantEntityExample.someRestaurantEntity1());
        when(restaurantJpaRepository.save(any(RestaurantEntity.class)))
                .thenReturn(RestaurantEntityExample.someRestaurantEntity1());

        // when
        restaurantRepository.addRestaurant(restaurant);

        // then
        verify(restaurantDaoMapper, times(1)).mapRestaurantToEntity(any(Restaurant.class));
    }

    @Test
    void shouldFindRestaurantsByIdListCorrectly() {
        // given
        final List<Long> idList = List.of(1L, 2L, 3L);
        final Integer page = 1;

        when(restaurantJpaRepository.findAllById(anyList(), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(
                        RestaurantEntityExample.someRestaurantEntity1(),
                        RestaurantEntityExample.someRestaurantEntity2(),
                        RestaurantEntityExample.someRestaurantEntity3()
                )));
        when(restaurantDaoMapper.mapRestaurantFromEntity(any(RestaurantEntity.class)))
                .thenReturn(RestaurantExample.someRestaurant1())
                .thenReturn(RestaurantExample.someRestaurant2())
                .thenReturn(RestaurantExample.someRestaurant3());

        // when
        final List<Restaurant> result = restaurantRepository.findRestaurantsByIdList(idList, page);

        // then
        assertEquals(3, result.size());
    }

    @Test
    void shouldFindRestaurantByFoodAppUserCorrectly() {
        // given
        final FoodAppUser foodAppUser = FoodAppUserExample.someFoodAppUser1();

        when(foodAppUserDaoMapper.mapFoodAppUserToEntity(any(FoodAppUser.class)))
                .thenReturn(FoodAppUserEntityExample.someFoodAppUserEntity1());
        when(restaurantJpaRepository.findAllByFoodAppUser(any(FoodAppUserEntity.class)))
                .thenReturn(List.of(
                        RestaurantEntityExample.someRestaurantEntity1(),
                        RestaurantEntityExample.someRestaurantEntity2()
                ));
        when(restaurantDaoMapper.mapRestaurantFromEntity(any(RestaurantEntity.class)))
                .thenReturn(RestaurantExample.someRestaurant1());

        // when
        final List<Restaurant> result = restaurantRepository.findByFoodAppUser(foodAppUser);

        // then
        assertEquals(2, result.size());
    }
}