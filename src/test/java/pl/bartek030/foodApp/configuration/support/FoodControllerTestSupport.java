package pl.bartek030.foodApp.configuration.support;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.bartek030.foodApp.api.controller.FoodController;
import pl.bartek030.foodApp.api.dto.FoodCreationDTO;
import pl.bartek030.foodApp.api.dto.FoodDTO;

public interface FoodControllerTestSupport {

    RequestSpecification requestSpecification();

    default FoodDTO[] getFoodFromMenu() {
        return requestSpecification()
                .get(FoodController.FOOD_URL + FoodController.MENUS_FOODS_URL, 3L)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(FoodDTO[].class);
    }

    default ExtractableResponse<Response> saveFood(final FoodCreationDTO foodCreationDTO) {
        return requestSpecification()
                .body(foodCreationDTO)
                .post(FoodController.FOOD_URL + FoodController.NEW_FOOD_URL)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .and()
                .extract();
    }
}
