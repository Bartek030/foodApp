package pl.bartek030.foodApp.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bartek030.foodApp.api.dto.AppUserLoginDTO;
import pl.bartek030.foodApp.api.dto.FoodAppUserCreationDTO;
import pl.bartek030.foodApp.api.dto.FoodAppUserDTO;
import pl.bartek030.foodApp.api.dto.RedirectMessageDTO;

@RequestMapping(FoodAppUserController.FOOD_APP_USER_URL)
public interface FoodAppUserController {

    String FOOD_APP_USER_URL = "/user";
    String REDIRECT_URL = "/login-redirect";
    String USER_LOGIN_URL = "/login";
    String USER_REGISTER_URL = "/registration";

    @GetMapping(REDIRECT_URL)
    ResponseEntity<RedirectMessageDTO> redirectToLogin();

    @PostMapping(USER_LOGIN_URL)
    ResponseEntity<FoodAppUserDTO> userLogin(
            @RequestBody AppUserLoginDTO appUserLoginDTO
    );

    @PostMapping(USER_REGISTER_URL)
    ResponseEntity<FoodAppUserDTO> userRegistration(
            @RequestBody FoodAppUserCreationDTO foodAppUserCreationDTO
    );
}
