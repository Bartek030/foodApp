package pl.bartek030.foodApp.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.bartek030.foodApp.infrastructure.database.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

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

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @Column(name = "total_cost", nullable = false)
    private BigDecimal totalCost;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private OrderStatus status;

    @Column(name = "ordered_at", nullable = false)
    private OffsetDateTime orderedAt;

    @Column(name = "planned_delivery_time", nullable = false)
    private OffsetDateTime plannedDeliveryTime;

    @Column(name = "additional_information")
    private String additionalInformation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_app_user_id", nullable = false)
    private FoodAppUserEntity foodAppUser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appOrder")
    private Set<OrderDetailsEntity> orderDetails;
}
