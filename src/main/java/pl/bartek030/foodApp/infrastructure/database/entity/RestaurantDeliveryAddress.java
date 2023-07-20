package pl.bartek030.foodApp.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "restaurantDeliveryAddressId")
@ToString(of = {"restaurantDeliveryAddressId", "deliveryTime"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant_delivery_address")
public class RestaurantDeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_delivery_address_id")
    private Long restaurantDeliveryAddressId;

    @Column(name = "delivery_time", nullable = false)
    private OffsetDateTime deliveryTime;

    @Column(name = "delivery_address_id", nullable = false)
    private String deliveryAddressId;

    @Column(name = "restaurant_id", nullable = false)
    private String restaurantId;
}
