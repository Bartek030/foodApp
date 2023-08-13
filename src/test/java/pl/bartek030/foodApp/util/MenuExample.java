package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.business.serviceModel.Menu;

@UtilityClass
public class MenuExample {

    public Menu someMenu1() {
        return Menu.builder()
                .menuId(1L)
                .name("KEBAB")
                .category("fastfood")
                .restaurant(RestaurantExample.someRestaurant1())
                .build();
    }
}
