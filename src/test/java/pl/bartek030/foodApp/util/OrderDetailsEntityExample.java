package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.infrastructure.database.entity.OrderDetailsEntity;

@UtilityClass
public class OrderDetailsEntityExample {

    public OrderDetailsEntity someOrderDetailsEntity1() {
        return OrderDetailsEntity.builder()
                .orderDetailsId(1L)
                .quantity(1)
                .food(FoodEntityExample.someFoodEntity1())
                .appOrder(AppOrderEntityExample.someAppOrderEntity1())
                .build();
    }

    public OrderDetailsEntity someOrderDetailsEntity2() {
        return OrderDetailsEntity.builder()
                .orderDetailsId(2L)
                .quantity(2)
                .food(FoodEntityExample.someFoodEntity2())
                .appOrder(AppOrderEntityExample.someAppOrderEntity1())
                .build();
    }

    public OrderDetailsEntity someOrderDetailsEntity3() {
        return OrderDetailsEntity.builder()
                .orderDetailsId(3L)
                .quantity(3)
                .food(FoodEntityExample.someFoodEntity3())
                .appOrder(AppOrderEntityExample.someAppOrderEntity1())
                .build();
    }
}
