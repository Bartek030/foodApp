package pl.bartek030.foodApp.configuration.support;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.bartek030.foodApp.api.controller.FoodAppUserController;
import pl.bartek030.foodApp.api.dto.FoodAppUserCreationDTO;

public interface FoodAppUserControllerTestSupport {

    RequestSpecification requestSpecification();

    default ExtractableResponse<Response> saveFoodAppUser(final FoodAppUserCreationDTO foodAppUserCreationDTO) {
        return requestSpecification()
                .body(foodAppUserCreationDTO)
                .post(FoodAppUserController.FOOD_APP_USER_URL + FoodAppUserController.USER_REGISTER_URL)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .and()
                .extract();
    }
}
