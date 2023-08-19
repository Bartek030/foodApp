package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.infrastructure.database.entity.AppOrderEntity;
import pl.bartek030.foodApp.infrastructure.database.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@UtilityClass
public class AppOrderEntityExample {

    public AppOrderEntity someAppOrderEntity1() {
        return AppOrderEntity.builder()
                .number("XXX-YYY-1")
                .totalCost(BigDecimal.valueOf(30.50))
                .status(OrderStatus.ORDERED)
                .orderedAt(OffsetDateTime.of(2022, 10, 10, 10, 10, 10, 1000, ZoneOffset.UTC))
                .restaurant(RestaurantEntityExample.someRestaurantEntity1())
                .foodAppUser(FoodAppUserEntityExample.someFoodAppUserEntity1())
                .build();
    }

    public AppOrderEntity someAppOrderEntity2() {
        return AppOrderEntity.builder()
                .number("XXX-YYY-2")
                .totalCost(BigDecimal.valueOf(30.50))
                .status(OrderStatus.ORDERED)
                .orderedAt(OffsetDateTime.of(2022, 10, 10, 10, 10, 10, 1000, ZoneOffset.UTC))
                .restaurant(RestaurantEntityExample.someRestaurantEntity1())
                .foodAppUser(FoodAppUserEntityExample.someFoodAppUserEntity1())
                .build();
    }

    public AppOrderEntity someAppOrderEntity3() {
        return AppOrderEntity.builder()
                .number("XXX-YYY-3")
                .totalCost(BigDecimal.valueOf(30.50))
                .status(OrderStatus.ORDERED)
                .orderedAt(OffsetDateTime.of(2022, 10, 10, 10, 10, 10, 1000, ZoneOffset.UTC))
                .restaurant(RestaurantEntityExample.someRestaurantEntity1())
                .foodAppUser(FoodAppUserEntityExample.someFoodAppUserEntity1())
                .build();
    }
}
