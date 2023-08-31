package pl.bartek030.foodApp.business.services.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartek030.foodApp.business.serviceModel.Menu;
import pl.bartek030.foodApp.business.serviceModel.MenuCreation;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.infrastructure.database.repository.MenuRepository;
import pl.bartek030.foodApp.util.MenuCreationExample;
import pl.bartek030.foodApp.util.MenuExample;
import pl.bartek030.foodApp.util.RestaurantExample;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuServiceImplTest {

    @InjectMocks
    MenuServiceImpl menuService;

    @Mock
    MenuRepository menuDao;

    @Mock
    RestaurantServiceImpl restaurantService;

    @Test
    void givenMenuIdShouldFindMenu() {
        // given
        Long menuId = 1L;
        final Menu expected = MenuExample.someMenu1();

        when(menuDao.findById(any(Long.class))).thenReturn(Optional.of(expected));

        // when
        final Menu actual = menuService.findById(menuId);

        // then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void givenMenuIdShouldThrownException() {
        // given
        Long menuId = 1L;
        final String expectedExceptionMessage = "Menu with id: [%s] not found".formatted(menuId);

        when(menuDao.findById(any(Long.class))).thenThrow(new RuntimeException(expectedExceptionMessage));

        // when then
        final RuntimeException actualException = assertThrows(
                RuntimeException.class,
                () ->  menuService.findById(menuId)
        );
        assertEquals(expectedExceptionMessage, actualException.getMessage());
    }

    @Test
    void givenMenuDataShouldAddMenu() {
        // given
        final MenuCreation menuCreation = MenuCreationExample.someMenuCreation1();
        final Menu menu = MenuExample.someMenu1();

        when(restaurantService.findById(any(Long.class))).thenReturn(RestaurantExample.someRestaurant1());
        doNothing().when(menuDao).addMenu(any(Menu.class));

        // when
        menuService.addMenu(menuCreation);

        // then
        verify(restaurantService, times(1)).findById(menuCreation.getRestaurantId());
    }

    @Test
    void givenRestaurantIdShouldReturnMenuList() {
        // given
        Long restaurantId = 1L;
        List<Menu> menus = List.of(
                MenuExample.someMenu1(),
                MenuExample.someMenu2(),
                MenuExample.someMenu3()
        );
        final Restaurant restaurant = RestaurantExample.someRestaurant1();

        when(restaurantService.findById(any(Long.class))).thenReturn(restaurant);
        when(menuDao.findByRestaurant(any(Restaurant.class))).thenReturn(menus);

        // when
        final List<Menu> actualList = menuService.getMenusByRestaurantId(restaurantId);

        // then
        assertEquals(menus.size(), actualList.size());
    }

}