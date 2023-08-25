package pl.bartek030.foodApp.backendTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.bartek030.foodApp.api.dto.*;
import pl.bartek030.foodApp.configuration.RestAssuredIntegrationTestBase;
import pl.bartek030.foodApp.configuration.support.FoodAppUserControllerTestSupport;
import pl.bartek030.foodApp.configuration.support.FoodControllerTestSupport;
import pl.bartek030.foodApp.configuration.support.MenuControllerTestSupport;
import pl.bartek030.foodApp.configuration.support.RestaurantControllerTestSupport;
import pl.bartek030.foodApp.util.*;

public class FoodControllerImplRestAssuredITController
        extends RestAssuredIntegrationTestBase
        implements FoodControllerTestSupport,
        MenuControllerTestSupport,
        RestaurantControllerTestSupport,
        FoodAppUserControllerTestSupport {

//    @Test
//    void shouldReturnListOfFoodsFromMenu() {
//        // given
//        final FoodCreationDTO food1 = FoodCreationDTOExample.someFoodCreationDTO1();
//        final FoodCreationDTO food2 = FoodCreationDTOExample.someFoodCreationDTO2();
//        final FoodCreationDTO food3 = FoodCreationDTOExample.someFoodCreationDTO3();
//
//        final MenuCreationDTO menuCreationDTO = MenuCreationDTOExample.someMenuCreationDTO1();
//        final RestaurantCreationDTO restaurantCreationDTO = RestaurantCreationDTOExample.someRestaurantCreationDTO1();
//        final FoodAppUserCreationDTO foodAppUserCreationDTO = FoodAppUserCreationDTOExample.someFoodAppUserCreationDTO4();
//
//        final FoodDTO expected1 = FoodDTOExample.someFoodDTO1();
//        final FoodDTO expected2 = FoodDTOExample.someFoodDTO2();
//        final FoodDTO expected3 = FoodDTOExample.someFoodDTO3();
//
//
//        // when
//        saveFoodAppUser(foodAppUserCreationDTO);
//        saveRestaurant(restaurantCreationDTO);
//        saveMenu(menuCreationDTO);
//        saveFood(food1);
//        saveFood(food2);
//        saveFood(food3);
//
//        final FoodDTO[] foodFromMenu = getFoodFromMenu();
//
//        // then
//        Assertions.assertThat(foodFromMenu)
//                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("foodId", "menu", "price")
//                .containsAnyOf(expected1, expected2, expected3);
//    }
}
