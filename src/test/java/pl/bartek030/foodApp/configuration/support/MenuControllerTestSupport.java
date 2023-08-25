package pl.bartek030.foodApp.configuration.support;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.bartek030.foodApp.api.controller.rest.MenuRestController;
import pl.bartek030.foodApp.api.dto.MenuCreationDTO;
import pl.bartek030.foodApp.api.dto.MenuDTO;

public interface MenuControllerTestSupport {

    RequestSpecification requestSpecification();

    default MenuDTO[] getMenuFromRestaurant(final Long restaurantId) {
        return requestSpecification()
                .get(MenuRestController.MENU_URL + MenuRestController.RESTAURANTS_MENUS_URL, restaurantId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(MenuDTO[].class);
    }

    default ExtractableResponse<Response> saveMenu(final MenuCreationDTO menuCreationDTO) {
        return requestSpecification()
                .body(menuCreationDTO)
                .post(MenuRestController.MENU_URL + MenuRestController.NEW_MENU_URL)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .and()
                .extract();
    }
}
