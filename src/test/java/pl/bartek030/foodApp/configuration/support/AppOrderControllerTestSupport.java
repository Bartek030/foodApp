package pl.bartek030.foodApp.configuration.support;

import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.bartek030.foodApp.api.controller.AppOrderController;
import pl.bartek030.foodApp.api.dto.AppOrderDTO;
import pl.bartek030.foodApp.api.dto.OrderDetailsCreationDTO;

import java.util.List;

public interface AppOrderControllerTestSupport {

    RequestSpecification requestSpecification();

    default AppOrderDTO[] getAppOrdersByUser() {
        return requestSpecification()
                .get(AppOrderController.APP_ORDER_URL + AppOrderController.USER_APP_ORDER_ID, 3L)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(AppOrderDTO[].class);
    }

    default AppOrderDTO saveAppOrder(final List<OrderDetailsCreationDTO> orderDetailsCreationDTO) {
        return requestSpecification()
                .body(orderDetailsCreationDTO)
                .post(AppOrderController.APP_ORDER_URL + AppOrderController.NEW_APP_ORDER_URL)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .and()
                .extract()
                .as(AppOrderDTO.class);
    }
}
