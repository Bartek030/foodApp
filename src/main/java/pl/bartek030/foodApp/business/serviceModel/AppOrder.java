package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodAppUserEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.OrderDetailsEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;
import pl.bartek030.foodApp.infrastructure.database.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "appOrderId")
@ToString(of = {"number", "totalCost"})
public class AppOrder {

    Long appOrderId;
    String number;
    BigDecimal totalCost;
    OrderStatus status;
    OffsetDateTime orderedAt;
    OffsetDateTime plannedDeliveryTime;
    String additionalInformation;
    Restaurant restaurant;
    FoodAppUser foodAppUser;
    Set<OrderDetails> orderDetails;
}
