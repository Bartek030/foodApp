package pl.bartek030.foodApp.api.controller.implementation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
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
    private final HttpServletRequest request;

    @Override
    public ResponseEntity<FoodAppUserDTO> userLoginSuccess() {
        final HttpSession session = request.getSession();
        return ResponseEntity.ok()
                .body(FoodAppUserDTO.builder().foodAppUserId(23L).build());
    }

    @Override
    public ResponseEntity<FoodAppUserDTO> userLoginFailure() {
        return ResponseEntity.ok()
//                .header("Location", "index2.html")
                .body(FoodAppUserDTO.builder().foodAppUserId(245L).build());
    }

    @Override
    public ResponseEntity<FoodAppUserDTO> userRegistration(FoodAppUserCreationDTO foodAppUserCreationDTO) {
        foodAppUserService.addUser(foodAppUserCreationDtoMapper.map(foodAppUserCreationDTO));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
