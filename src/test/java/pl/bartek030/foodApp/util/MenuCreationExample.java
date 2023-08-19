package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.business.serviceModel.MenuCreation;

@UtilityClass
public class MenuCreationExample {

    public MenuCreation someMenuCreation1() {
        return MenuCreation.builder()
                .name("KEBAB")
                .category("fastfood")
                .restaurantId(1L)
                .build();
    }

}
