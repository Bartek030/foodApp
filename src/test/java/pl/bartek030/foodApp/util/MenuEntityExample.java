package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.infrastructure.database.entity.MenuEntity;

@UtilityClass
public class MenuEntityExample {

    public MenuEntity someMenuEntity1() {
        return MenuEntity.builder()
                .menuId(1L)
                .name("MENU1")
                .category("PIZZA")
                .restaurant(RestaurantEntityExample.someRestaurantEntity1())
                .build();
    }

    public MenuEntity someMenuEntity2() {
        return MenuEntity.builder()
                .menuId(2L)
                .name("MENU2")
                .category("PIZZA")
                .restaurant(RestaurantEntityExample.someRestaurantEntity1())
                .build();
    }

    public MenuEntity someMenuEntity3() {
        return MenuEntity.builder()
                .menuId(3L)
                .name("MENU3")
                .category("PIZZA")
                .restaurant(RestaurantEntityExample.someRestaurantEntity1())
                .build();
    }
}
