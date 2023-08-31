package pl.bartek030.foodApp.infrastructure.database.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartek030.foodApp.business.serviceModel.Menu;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.infrastructure.database.entity.MenuEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.MenuDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.RestaurantDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.MenuJpaRepository;
import pl.bartek030.foodApp.util.MenuEntityExample;
import pl.bartek030.foodApp.util.MenuExample;
import pl.bartek030.foodApp.util.RestaurantEntityExample;
import pl.bartek030.foodApp.util.RestaurantExample;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuRepositoryTest {

    @InjectMocks
    private MenuRepository menuRepository;

    @Mock
    private MenuJpaRepository menuJpaRepository;

    @Mock
    private MenuDaoMapper menuDaoMapper;

    @Mock
    private RestaurantDaoMapper restaurantDaoMapper;

    @Test
    void shouldFindByIdCorrectly() {
        // given
        Long id = 1L;

        when(menuJpaRepository.findById(any(Long.class))).thenReturn(Optional.of(MenuEntityExample.someMenuEntity1()));
        when(menuDaoMapper.mapMenuFromEntity(any(MenuEntity.class))).thenReturn(MenuExample.someMenu1());

        // when
        final Optional<Menu> result = menuRepository.findById(id);

        // then
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getMenuId());
    }

    @Test
    void shouldAddMenuCorrectly() {
        // given
        final Menu menu = MenuExample.someMenu1();
        final MenuEntity menuEntity = MenuEntityExample.someMenuEntity1();

        when(menuDaoMapper.mapMenuToEntity(any(Menu.class))).thenReturn(MenuEntityExample.someMenuEntity1());
        when(menuJpaRepository.save(any(MenuEntity.class))).thenReturn(menuEntity);

        // when
        menuRepository.addMenu(menu);

        // then
        verify(menuJpaRepository, times(1)).save(menuEntity);
    }

    @Test
    void shouldFindMenusFromRestaurant() {
        // given
        final Restaurant restaurant = RestaurantExample.someRestaurant1();

        when(restaurantDaoMapper.mapRestaurantToEntity(any(Restaurant.class)))
                .thenReturn(RestaurantEntityExample.someRestaurantEntity1());
        when(menuJpaRepository.findAllByRestaurant(any(RestaurantEntity.class)))
                .thenReturn(List.of(
                        MenuEntityExample.someMenuEntity1(),
                        MenuEntityExample.someMenuEntity2(),
                        MenuEntityExample.someMenuEntity3()
                ));
        when(menuDaoMapper.mapMenuFromEntityWithRestaurant(any(MenuEntity.class)))
                .thenReturn(MenuExample.someMenu1())
                .thenReturn(MenuExample.someMenu2())
                .thenReturn(MenuExample.someMenu3());

        // when
        final List<Menu> result = menuRepository.findByRestaurant(restaurant);

        // then
        assertEquals(3, result.size());
    }
}