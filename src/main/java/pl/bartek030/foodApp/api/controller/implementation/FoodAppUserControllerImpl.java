package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.bartek030.foodApp.api.controller.FoodAppUserController;
import pl.bartek030.foodApp.api.dto.AppUserLoginDTO;
import pl.bartek030.foodApp.api.dto.FoodAppUserCreationDTO;
import pl.bartek030.foodApp.api.dto.FoodAppUserDTO;
import pl.bartek030.foodApp.api.dto.RedirectMessageDTO;
import pl.bartek030.foodApp.api.dto.mapper.AppUserLoginDtoMapper;
import pl.bartek030.foodApp.api.dto.mapper.FoodAppUserCreationDtoMapper;
import pl.bartek030.foodApp.business.services.FoodAppUserService;

@RestController
@AllArgsConstructor
public class FoodAppUserControllerImpl implements FoodAppUserController {

    private final FoodAppUserCreationDtoMapper foodAppUserCreationDtoMapper;
    private final AppUserLoginDtoMapper appUserLoginDtoMapper;
    private final FoodAppUserService foodAppUserService;

    @Override
    public ResponseEntity<RedirectMessageDTO> redirectToLogin() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(RedirectMessageDTO.of("true"));
    }

    @Override
    public ResponseEntity<FoodAppUserDTO> userLogin(AppUserLoginDTO appUserLoginDTO) {
        foodAppUserService.authenticateUser(appUserLoginDtoMapper.map(appUserLoginDTO));
        return ResponseEntity.ok()
                .header("Location", "index.html")
                .build();
    }

    @Override
    public ResponseEntity<FoodAppUserDTO> userRegistration(FoodAppUserCreationDTO foodAppUserCreationDTO) {
        foodAppUserService.addUser(foodAppUserCreationDtoMapper.map(foodAppUserCreationDTO));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
