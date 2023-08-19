package pl.bartek030.foodApp.configuration.support;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.bartek030.foodApp.api.controller.FoodController;
import pl.bartek030.foodApp.api.controller.MenuController;
import pl.bartek030.foodApp.api.dto.FoodDTO;
import pl.bartek030.foodApp.api.dto.MenuCreationDTO;
import pl.bartek030.foodApp.api.dto.MenuDTO;

public interface MenuControllerTestSupport {

    RequestSpecification requestSpecification();

    default MenuDTO[] getMenuFromRestaurant() {
        return requestSpecification()
                .get(MenuController.MENU_URL + MenuController.RESTAURANTS_MENUS_URL, 8L)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(MenuDTO[].class);
    }

    default ExtractableResponse<Response> saveMenu(final MenuCreationDTO menuCreationDTO) {
        return requestSpecification()
                .body(menuCreationDTO)
                .post(MenuController.MENU_URL + MenuController.NEW_MENU_URL)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .and()
                .extract();
    }
}
