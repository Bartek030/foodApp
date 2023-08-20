package pl.bartek030.foodApp.api.controller.rest.implementation;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.bartek030.foodApp.api.controller.rest.MenuRestController;
import pl.bartek030.foodApp.api.dto.MenuCreationDTO;
import pl.bartek030.foodApp.api.dto.MenuDTO;
import pl.bartek030.foodApp.api.dto.mapper.MenuCreationDtoMapper;
import pl.bartek030.foodApp.api.dto.mapper.MenuDtoMapper;
import pl.bartek030.foodApp.business.services.MenuService;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class MenuRestControllerRestImpl implements MenuRestController {

    private final MenuCreationDtoMapper menuCreationDtoMapper;
    private final MenuDtoMapper menuDtoMapper;
    private final MenuService menuService;

    @Override
    public ResponseEntity<MenuCreationDTO> addMenu(final MenuCreationDTO menu) {
        menuService.addMenu(menuCreationDtoMapper.map(menu));
        return ResponseEntity
                .created(URI.create(
                        // TODO: magic number to remove after Spring Security implementation
                        MENU_URL + ID_PLACEHOLDER.formatted(1)
                )).build();
    }

    @Override
    public ResponseEntity<List<MenuDTO>> getRestaurantsMenus(final Long restaurantId) {
        return ResponseEntity.ok(
                menuService.getMenusByRestaurantId(restaurantId)
                        .stream()
                        .map(menuDtoMapper::map)
                        .toList()
        );
    }
}
