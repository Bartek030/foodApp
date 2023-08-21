package pl.bartek030.foodApp.api.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bartek030.foodApp.api.dto.AppUserLoginDTO;
import pl.bartek030.foodApp.api.dto.FoodAppUserCreationDTO;

@RequestMapping(FoodAppUserController.FOOD_APP_USER_URL)
public interface FoodAppUserController {

    String FOOD_APP_USER_URL = "/user";
    String USER_LOGIN_PAGE_URL = "/loginPage";
    String USER_LOGIN_FAILURE_URL = "/login-failure";
    String USER_REGISTER_URL = "/registration";
    String REGISTRATION_PAGE_URL = "/registrationPage";

    @GetMapping(USER_LOGIN_PAGE_URL)
    String getLoginPage();

    @GetMapping(REGISTRATION_PAGE_URL)
    String getRegistrationPage();

    @GetMapping(USER_LOGIN_FAILURE_URL)
    String loginFailure(@ModelAttribute("appUserLoginDTO") AppUserLoginDTO appUserLoginDTO);

    @PostMapping(USER_REGISTER_URL)
    String userRegistration(
            @Valid @ModelAttribute FoodAppUserCreationDTO foodAppUserCreationDTO
    );
}
