package pl.bartek030.foodApp.configuration.support;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.bartek030.foodApp.api.controller.rest.DeliveryAddressRestController;
import pl.bartek030.foodApp.api.dto.DeliveryAddressCreationDTO;

public interface DeliveryAddressControllerTestSupport {

    RequestSpecification requestSpecification();

    default ExtractableResponse<Response> saveDeliveryAddress(final DeliveryAddressCreationDTO deliveryAddressCreationDTO) {
        return requestSpecification()
                .body(deliveryAddressCreationDTO)
                .post(DeliveryAddressRestController.DELIVERY_ADDRESS_URL + DeliveryAddressRestController.NEW_DELIVERY_ADDRESS_URL)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .and()
                .extract();
    }
}
