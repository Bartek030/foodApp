package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.api.dto.AppOrderDTO;
import pl.bartek030.foodApp.infrastructure.database.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@UtilityClass
public class AppOrderDTOExample {

    public AppOrderDTO someAppOrderDto1() {
        return AppOrderDTO.builder()
                .appOrderId(1L)
                .number("XXX-YYY-1")
                .totalCost(BigDecimal.valueOf(150.50))
                .status(OrderStatus.ORDERED)
                .orderedAt(OffsetDateTime.of(2022, 10, 10, 10, 10, 10, 1000, ZoneOffset.UTC))
                .plannedDeliveryTime(OffsetDateTime.of(2022, 10, 10, 14, 14, 14, 1000, ZoneOffset.UTC))
                .restaurant(RestaurantExample.someRestaurant1())
                .build();
    }

    public AppOrderDTO someAppOrderDto2() {
        return AppOrderDTO.builder()
                .appOrderId(2L)
                .number("XXX-YYY-2")
                .totalCost(BigDecimal.valueOf(150.50))
                .status(OrderStatus.ORDERED)
                .orderedAt(OffsetDateTime.of(2022, 10, 20, 20, 20, 20, 1000, ZoneOffset.UTC))
                .plannedDeliveryTime(OffsetDateTime.of(2022, 10, 20, 21, 20, 20, 1000, ZoneOffset.UTC))
                .restaurant(RestaurantExample.someRestaurant1())
                .build();
    }

    public AppOrderDTO someAppOrderDto3() {
        return AppOrderDTO.builder()
                .appOrderId(3L)
                .number("XXX-YYY-3")
                .totalCost(BigDecimal.valueOf(150.50))
                .status(OrderStatus.ORDERED)
                .orderedAt(OffsetDateTime.of(2022, 10, 15, 15, 15, 15, 1000, ZoneOffset.UTC))
                .plannedDeliveryTime(OffsetDateTime.of(2022, 10, 15, 16, 15, 15, 1000, ZoneOffset.UTC))
                .restaurant(RestaurantExample.someRestaurant1())
                .build();
    }
}
