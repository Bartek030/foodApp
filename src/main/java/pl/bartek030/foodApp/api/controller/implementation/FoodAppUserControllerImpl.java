package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import pl.bartek030.foodApp.api.controller.FoodAppUserController;
import pl.bartek030.foodApp.api.dto.AppUserLoginDTO;
import pl.bartek030.foodApp.api.dto.FoodAppUserCreationDTO;
import pl.bartek030.foodApp.api.dto.mapper.FoodAppUserCreationDtoMapper;
import pl.bartek030.foodApp.business.services.FoodAppUserService;

@Controller
@AllArgsConstructor
public class FoodAppUserControllerImpl implements FoodAppUserController {

    private final FoodAppUserService foodAppUserService;
    private final FoodAppUserCreationDtoMapper foodAppUserCreationDtoMapper;

    @Override
    public String getLoginPage() {
        return "login";
    }

    @Override
    public String getRegistrationPage() {
        return "registration";
    }

    @Override
    public String loginFailure(final AppUserLoginDTO appUserLoginDTO) {
        return "login-failure";
    }

    @Override
    public String userRegistration(final FoodAppUserCreationDTO foodAppUserCreationDTO) {
        foodAppUserService.addUser(foodAppUserCreationDtoMapper.map(foodAppUserCreationDTO));
        return "registration-success";
    }
}
