package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.api.dto.FoodCreationDTO;

import java.math.BigDecimal;

@UtilityClass
public class FoodCreationDTOExample {

    public FoodCreationDTO someFoodCreationDTO1() {
        return FoodCreationDTO.builder()
                .name("PIZZA")
                .price(BigDecimal.valueOf(30.00))
                .description("Mała Hawajska")
                .menuId(3L)
                .build();
    }

    public FoodCreationDTO someFoodCreationDTO2() {
        return FoodCreationDTO.builder()
                .name("PIZZA")
                .price(BigDecimal.valueOf(40.00))
                .description("Średnia Wiejska")
                .menuId(3L)
                .build();
    }

    public FoodCreationDTO someFoodCreationDTO3() {
        return FoodCreationDTO.builder()
                .name("PIZZA")
                .price(BigDecimal.valueOf(50.00))
                .description("Duża Rodzinna")
                .menuId(3L)
                .build();
    }
}
