package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.bartek030.foodApp.api.controller.FoodAppUserController;
import pl.bartek030.foodApp.api.dto.RedirectMessageDTO;

@RestController
@AllArgsConstructor
public class FoodAppUserControllerImpl implements FoodAppUserController {


    @Override
    public ResponseEntity<RedirectMessageDTO> redirectToLogin() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(RedirectMessageDTO.of("true"));
    }
}
