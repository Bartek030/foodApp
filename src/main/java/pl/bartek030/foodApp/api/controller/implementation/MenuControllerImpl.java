package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import pl.bartek030.foodApp.api.controller.MenuController;
import pl.bartek030.foodApp.api.dto.MenuCreationDTO;
import pl.bartek030.foodApp.api.dto.MenuDTO;
import pl.bartek030.foodApp.api.dto.mapper.MenuCreationDtoMapper;
import pl.bartek030.foodApp.api.dto.mapper.MenuDtoMapper;
import pl.bartek030.foodApp.business.services.MenuService;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MenuControllerImpl implements MenuController {

    private final MenuCreationDtoMapper menuCreationDtoMapper;
    private final MenuDtoMapper menuDtoMapper;
    private final MenuService menuService;

    @Override
    public String addMenu(final MenuCreationDTO menu) {
        menuService.addMenu(menuCreationDtoMapper.map(menu));
        return "menu-success";
    }

    @Override
    public String getRestaurantsMenus(final Long restaurantId, final Model model) {
        final List<MenuDTO> menuDTOList = menuService.getMenusByRestaurantId(restaurantId)
                .stream()
                .map(menuDtoMapper::map)
                .toList();

        model.addAllAttributes(Map.of("menus", menuDTOList));
        return "menuList";
    }

    @Override
    public String getOwnersRestaurantsMenus(final Long restaurantId, final Model model) {
        final List<MenuDTO> menuDTOList = menuService.getMenusByRestaurantId(restaurantId)
                .stream()
                .map(menuDtoMapper::map)
                .toList();

        model.addAllAttributes(Map.of("menus", menuDTOList));
        model.addAttribute("restaurantId", restaurantId);
        return "owner-menuList";
    }
}
