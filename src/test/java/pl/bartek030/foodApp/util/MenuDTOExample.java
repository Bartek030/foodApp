package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.api.dto.MenuDTO;

@UtilityClass
public class MenuDTOExample {

    public MenuDTO someMenuDTO1() {
        return MenuDTO.builder()
                .menuId(1L)
                .name("KEBAB")
                .category("fastfood")
                .restaurant(RestaurantExample.someRestaurant1())
                .build();
    }

    public MenuDTO someMenuDTO2() {
        return MenuDTO.builder()
                .menuId(2L)
                .name("Pizza")
                .category("fastfood")
                .restaurant(RestaurantExample.someRestaurant1())
                .build();
    }

    public MenuDTO someMenuDTO3() {
        return MenuDTO.builder()
                .menuId(1L)
                .name("Zupy")
                .category("zupy")
                .restaurant(RestaurantExample.someRestaurant1())
                .build();
    }
}
