package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.business.serviceModel.FoodCreation;

import java.math.BigDecimal;

@UtilityClass
public class FoodCreationExample {

    public FoodCreation someFoodCreation1() {
        return FoodCreation.builder()
                .name("PIZZA")
                .price(BigDecimal.valueOf(30.50))
                .description("Some description")
                .menuId(1L)
                .build();
    }
}
