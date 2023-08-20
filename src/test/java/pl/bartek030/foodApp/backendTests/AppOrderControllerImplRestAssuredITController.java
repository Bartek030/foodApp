package pl.bartek030.foodApp.backendTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.bartek030.foodApp.api.dto.*;
import pl.bartek030.foodApp.configuration.RestAssuredIntegrationTestBase;
import pl.bartek030.foodApp.configuration.support.*;
import pl.bartek030.foodApp.util.*;

import java.util.List;

public class AppOrderControllerImplRestAssuredITController
        extends RestAssuredIntegrationTestBase
        implements AppOrderControllerTestSupport,
        FoodControllerTestSupport,
        RestaurantControllerTestSupport,
        MenuControllerTestSupport,
        FoodAppUserControllerTestSupport,
        DeliveryAddressControllerTestSupport{

//    @Test
//    void shouldReturnAppOrdersByUser() {
//        // given
//        final FoodCreationDTO food1 = FoodCreationDTOExample.someFoodCreationDTO1();
//        final FoodCreationDTO food2 = FoodCreationDTOExample.someFoodCreationDTO2();
//        final FoodCreationDTO food3 = FoodCreationDTOExample.someFoodCreationDTO3();
//        final MenuCreationDTO menuCreationDTO = MenuCreationDTOExample.someMenuCreationDTO1();
//        final RestaurantCreationDTO restaurantCreationDTO = RestaurantCreationDTOExample.someRestaurantCreationDTO1();
//        final FoodAppUserCreationDTO foodAppUserCreationDTO = FoodAppUserCreationDTOExample.someFoodAppUserCreationDTO1();
//        final DeliveryAddressCreationDTO deliveryAddressCreationDTO = DeliveryAddressCreationDTOExample.someDeliveryAddressCreationDTO1();
//
//        final List<OrderDetailsCreationDTO> orderDetailsCreationList = List.of(
//                OrderDetailsCreationDTOExample.someOrderDetailsCreationDTO1(),
//                OrderDetailsCreationDTOExample.someOrderDetailsCreationDTO2(),
//                OrderDetailsCreationDTOExample.someOrderDetailsCreationDTO3()
//        );
//
//        // when
//        saveFoodAppUser(foodAppUserCreationDTO);
//        saveRestaurant(restaurantCreationDTO);
//        saveDeliveryAddress(deliveryAddressCreationDTO);
//        saveMenu(menuCreationDTO);
//        saveFood(food1);
//        saveFood(food2);
//        saveFood(food3);
//        final AppOrderDTO appOrderDTO = saveAppOrder(orderDetailsCreationList);
//
//        final AppOrderDTO[] appOrderDTOList = getAppOrdersByUser();
//
//        // then
//        Assertions.assertThat(appOrderDTOList)
//                .usingRecursiveFieldByFieldElementComparatorIgnoringFields(
//                        "appOrderId", "orderedAt", "plannedDeliveryTime", "restaurant", "orderDetails")
//                .containsAnyOf(appOrderDTO);
//    }

}
