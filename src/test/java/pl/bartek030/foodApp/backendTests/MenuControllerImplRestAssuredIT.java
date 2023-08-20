package pl.bartek030.foodApp.backendTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.bartek030.foodApp.api.dto.*;
import pl.bartek030.foodApp.api.dto.mapper.MenuCreationDtoMapper;
import pl.bartek030.foodApp.configuration.RestAssuredIntegrationTestBase;
import pl.bartek030.foodApp.configuration.support.FoodAppUserControllerTestSupport;
import pl.bartek030.foodApp.configuration.support.MenuControllerTestSupport;
import pl.bartek030.foodApp.configuration.support.RestaurantControllerTestSupport;
import pl.bartek030.foodApp.infrastructure.database.entity.AddressEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodAppUserEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.AddressJpaRepository;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.FoodAppUserJpaRepository;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.bartek030.foodApp.util.*;

public class MenuControllerImplRestAssuredIT
        extends RestAssuredIntegrationTestBase
        implements MenuControllerTestSupport,
        RestaurantControllerTestSupport,
        FoodAppUserControllerTestSupport {

    private AddressJpaRepository addressJpaRepository;
    private FoodAppUserJpaRepository foodAppUserJpaRepository;
    private RestaurantJpaRepository restaurantJpaRepository;
    private MenuCreationDtoMapper menuCreationDtoMapper;


//    @Test
//    void shouldReturnListOfMenusFromRestaurant() {
//        // given
//        final AddressEntity addressEntity = addressJpaRepository.saveAndFlush(AddressEntityExample.someAddressEntity1());
//
//        final FoodAppUserEntity foodAppUserEntity =
//                foodAppUserJpaRepository.saveAndFlush(FoodAppUserEntityExample.someFoodAppUserEntity1()
//                        .withAddress(addressEntity));
//
//        final RestaurantEntity restaurantEntity =
//                restaurantJpaRepository.saveAndFlush(RestaurantEntityExample.someRestaurantEntity1()
//                        .withFoodAppUser(foodAppUserEntity)
//                        .withAddress(addressEntity));
//
////        final FoodAppUserCreationDTO foodAppUserCreationDTO = FoodAppUserCreationDTOExample.someFoodAppUserCreationDTO1();
////        final RestaurantCreationDTO restaurantCreationDTO = RestaurantCreationDTOExample.someRestaurantCreationDTO1();
//
//        final MenuCreationDTO menu1 = MenuCreationDTOExample.someMenuCreationDTO1()
//                .withRestaurantId(restaurantEntity.getRestaurantId());
//        final MenuCreationDTO menu2 = MenuCreationDTOExample.someMenuCreationDTO1()
//                .withRestaurantId(restaurantEntity.getRestaurantId());
//        final MenuCreationDTO menu3 = MenuCreationDTOExample.someMenuCreationDTO1()
//                .withRestaurantId(restaurantEntity.getRestaurantId());
//
//        // when
////        saveFoodAppUser(foodAppUserCreationDTO);
////        saveRestaurant(restaurantCreationDTO);
//        saveMenu(menu1);
//        saveMenu(menu2);
//        saveMenu(menu3);
//
//        final MenuDTO[] menuFromRestaurant = getMenuFromRestaurant(restaurantEntity.getRestaurantId());
//
//        // then
//        Assertions.assertThat(menuFromRestaurant)
//                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("menuId", "restaurant")
//                .containsAnyOf(expected1, expected2, expected3);
//    }
}
