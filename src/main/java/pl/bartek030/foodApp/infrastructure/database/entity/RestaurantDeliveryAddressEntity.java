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
public class RestaurantDeliveryAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_delivery_address_id")
    private Long restaurantDeliveryAddressId;

    @Column(name = "delivery_time", nullable = false)
    private Integer deliveryTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_address_id")
    private DeliveryAddressEntity deliveryAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;
}
