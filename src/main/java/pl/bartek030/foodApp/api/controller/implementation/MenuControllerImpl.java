package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.bartek030.foodApp.api.controller.MenuController;
import pl.bartek030.foodApp.api.dto.MenuCreationDTO;
import pl.bartek030.foodApp.api.dto.MenuDTO;
import pl.bartek030.foodApp.api.dto.mapper.MenuDtoMapper;
import pl.bartek030.foodApp.business.services.MenuService;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class MenuControllerImpl implements MenuController {

    private final MenuDtoMapper menuDtoMapper;
    private final MenuService menuService;

    @Override
    public ResponseEntity<List<MenuDTO>> getRestaurantsMenus(final Long restaurantId) {
        return null;
    }

    @Override
    public ResponseEntity<MenuDTO> addMenu(final MenuCreationDTO menu) {
        menuService.addMenu(menuDtoMapper.map(menu));
        return ResponseEntity
                .created(URI.create(
                        // TODO: magic number to remove after Spring Security implementation
                        MENU_URL + ID_PLACEHOLDER.formatted(1)
                )).build();
    }
}
