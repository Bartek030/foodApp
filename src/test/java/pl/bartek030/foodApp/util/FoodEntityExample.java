package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodEntity;

import java.math.BigDecimal;

@UtilityClass
public class FoodEntityExample {

    public FoodEntity someFoodEntity1() {
        return FoodEntity.builder()
                .foodId(1L)
                .name("FOOD1")
                .price(BigDecimal.valueOf(10.50))
                .menu(MenuEntityExample.someMenuEntity1())
                .build();
    }

    public FoodEntity someFoodEntity2() {
        return FoodEntity.builder()
                .foodId(2L)
                .name("FOOD2")
                .price(BigDecimal.valueOf(10.50))
                .menu(MenuEntityExample.someMenuEntity1())
                .build();
    }

    public FoodEntity someFoodEntity3() {
        return FoodEntity.builder()
                .foodId(3L)
                .name("FOOD3")
                .price(BigDecimal.valueOf(10.50))
                .menu(MenuEntityExample.someMenuEntity1())
                .build();
    }

}
