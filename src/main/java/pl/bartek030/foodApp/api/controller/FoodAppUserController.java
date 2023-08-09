package pl.bartek030.foodApp.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bartek030.foodApp.api.dto.RedirectMessageDTO;

@RequestMapping(FoodAppUserController.FOOD_APP_USER_URL)
public interface FoodAppUserController {

    String FOOD_APP_USER_URL = "/user";
    String REDIRECT_URL = "/login-redirect";

    @GetMapping(REDIRECT_URL)
    ResponseEntity<RedirectMessageDTO> redirectToLogin();
}
