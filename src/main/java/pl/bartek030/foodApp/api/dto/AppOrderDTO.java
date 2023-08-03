package pl.bartek030.foodApp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;
import pl.bartek030.foodApp.business.serviceModel.OrderDetails;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.infrastructure.database.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppOrderDTO {

    private Long appOrderId;
    private String number;
    private BigDecimal totalCost;
    private OrderStatus status;
    private OffsetDateTime orderedAt;
    private OffsetDateTime plannedDeliveryTime;
    private String additionalInformation;
    private Restaurant restaurant;
    private Set<OrderDetails> orderDetails;
}
