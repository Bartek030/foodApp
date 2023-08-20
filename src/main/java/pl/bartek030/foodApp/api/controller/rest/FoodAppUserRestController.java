package pl.bartek030.foodApp.api.controller.rest;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bartek030.foodApp.api.dto.FoodAppUserCreationDTO;
import pl.bartek030.foodApp.api.dto.FoodAppUserDTO;

@RequestMapping(FoodAppUserRestController.FOOD_APP_USER_URL)
public interface FoodAppUserRestController {

    String FOOD_APP_USER_URL = "/user";
    String USER_LOGIN_SUCCESS_URL = "/loginSuccess";
    String USER_LOGIN_FAILURE_URL = "/loginFailure";
    String USER_REGISTER_URL = "/registration";

    @GetMapping(USER_LOGIN_SUCCESS_URL)
    ResponseEntity<FoodAppUserDTO> userLoginSuccess();

    @GetMapping(USER_LOGIN_FAILURE_URL)
    ResponseEntity<FoodAppUserDTO> userLoginFailure();

    @PostMapping(USER_REGISTER_URL)
    ResponseEntity<FoodAppUserDTO> userRegistration(
            @Valid @RequestBody FoodAppUserCreationDTO foodAppUserCreationDTO
    );
}
