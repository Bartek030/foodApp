package pl.bartek030.foodApp.backendTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.bartek030.foodApp.api.dto.*;
import pl.bartek030.foodApp.configuration.RestAssuredIntegrationTestBase;
import pl.bartek030.foodApp.configuration.support.FoodAppUserControllerTestSupport;
import pl.bartek030.foodApp.configuration.support.MenuControllerTestSupport;
import pl.bartek030.foodApp.configuration.support.RestaurantControllerTestSupport;
import pl.bartek030.foodApp.util.*;

public class MenuControllerImplRestAssuredIT
        extends RestAssuredIntegrationTestBase
        implements MenuControllerTestSupport,
        RestaurantControllerTestSupport,
        FoodAppUserControllerTestSupport {

    @Test
    void shouldReturnListOfMenusFromRestaurant() {
        // given
        final MenuCreationDTO menu1 = MenuCreationDTOExample.someMenuCreationDTO1();
        final MenuCreationDTO menu2 = MenuCreationDTOExample.someMenuCreationDTO1();
        final MenuCreationDTO menu3 = MenuCreationDTOExample.someMenuCreationDTO1();
        final RestaurantCreationDTO restaurantCreationDTO = RestaurantCreationDTOExample.someRestaurantCreationDTO1();
        final FoodAppUserCreationDTO foodAppUserCreationDTO = FoodAppUserCreationDTOExample.someFoodAppUserCreationDTO1();

        final MenuDTO expected1 = MenuDTOExample.someMenuDTO1();
        final MenuDTO expected2 = MenuDTOExample.someMenuDTO2();
        final MenuDTO expected3 = MenuDTOExample.someMenuDTO3();


        // when
        saveFoodAppUser(foodAppUserCreationDTO);
        saveRestaurant(restaurantCreationDTO);
        saveMenu(menu1);
        saveMenu(menu2);
        saveMenu(menu3);

        final MenuDTO[] menuFromRestaurant = getMenuFromRestaurant();

        // then
        Assertions.assertThat(menuFromRestaurant)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("menuId", "restaurant")
                .containsAnyOf(expected1, expected2, expected3);
    }
}
