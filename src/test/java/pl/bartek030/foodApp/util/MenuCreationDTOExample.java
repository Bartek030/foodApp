package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.api.dto.MenuCreationDTO;

@UtilityClass
public class MenuCreationDTOExample {

    public MenuCreationDTO someMenuCreationDTO1() {
        return MenuCreationDTO.builder()
                .name("KEBAB")
                .category("fastfood")
                .restaurantId(1L)
                .build();
    }
}
