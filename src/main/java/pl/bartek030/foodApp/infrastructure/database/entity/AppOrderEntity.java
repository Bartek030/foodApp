package pl.bartek030.foodApp.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "appOrderId")
@ToString(of = {"appOrderId", "number", "totalCost", "state"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_order")
public class AppOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_order_id")
    private Long appOrderId;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "total_cost", nullable = false)
    private BigDecimal totalCost;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "ordered_at", nullable = false)
    private OffsetDateTime orderedAt;

    @Column(name = "planned_delivery_time", nullable = false)
    private OffsetDateTime plannedDeliveryTime;

    @Column(name = "additional_information", nullable = false)
    private String additionalInformation;

    @Column(name = "restaurant_id", nullable = false)
    private String restaurantId;

    @Column(name = "food_app_user_id", nullable = false)
    private String foodAppUserId;
}
