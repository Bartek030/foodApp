package pl.bartek030.foodApp.configuration.support;

import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.bartek030.foodApp.api.controller.rest.RestaurantRestController;
import pl.bartek030.foodApp.api.dto.RestaurantCreationDTO;
import pl.bartek030.foodApp.api.dto.RestaurantDTO;

public interface RestaurantControllerTestSupport {

    RequestSpecification requestSpecification();

    default RestaurantDTO saveRestaurant(final RestaurantCreationDTO restaurantCreationDTO) {
        return requestSpecification()
                .body(restaurantCreationDTO)
                .post(RestaurantRestController.RESTAURANT_URL + RestaurantRestController.NEW_RESTAURANT_URL)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .and()
                .extract()
                .as(RestaurantDTO.class);
    }
}
