package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.api.dto.FoodDTO;

import java.math.BigDecimal;

@UtilityClass
public class FoodDTOExample {

    public FoodDTO someFoodDTO1() {
        return FoodDTO.builder()
                .foodId(1L)
                .name("PIZZA")
                .price(BigDecimal.valueOf(30))
                .description("Mała Hawajska")
                .menu(MenuExample.someMenu1())
                .build();
    }

    public FoodDTO someFoodDTO2() {
        return FoodDTO.builder()
                .foodId(2L)
                .name("PIZZA")
                .price(BigDecimal.valueOf(40))
                .description("Średnia Wiejska")
                .menu(MenuExample.someMenu1())
                .build();
    }

    public FoodDTO someFoodDTO3() {
        return FoodDTO.builder()
                .foodId(1L)
                .name("PIZZA")
                .price(BigDecimal.valueOf(50))
                .description("Duża Rodzinna")
                .menu(MenuExample.someMenu1())
                .build();
    }
}
