package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.api.dto.FoodCreationDTO;

import java.math.BigDecimal;

@UtilityClass
public class FoodCreationDTOExample {

    public FoodCreationDTO someFoodCreationDTO1() {
        return FoodCreationDTO.builder()
                .name("PIZZA")
                .price(BigDecimal.valueOf(30.50))
                .description("Some description")
                .menuId(1L)
                .build();
    }
}
