package pl.bartek030.foodApp.configuration.support;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.bartek030.foodApp.api.controller.RestaurantController;
import pl.bartek030.foodApp.api.dto.RestaurantCreationDTO;

public interface RestaurantControllerTestSupport {

    RequestSpecification requestSpecification();

    default ExtractableResponse<Response> saveRestaurant(final RestaurantCreationDTO restaurantCreationDTO) {
        return requestSpecification()
                .body(restaurantCreationDTO)
                .post(RestaurantController.RESTAURANT_URL + RestaurantController.NEW_RESTAURANT_URL)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .and()
                .extract();
    }
}
