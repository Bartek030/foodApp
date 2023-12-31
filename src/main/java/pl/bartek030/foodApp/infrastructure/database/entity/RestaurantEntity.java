package pl.bartek030.foodApp.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

@With
@Getter
@Setter
@EqualsAndHashCode(of = "restaurantId")
@ToString(of = {"restaurantId", "name"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_app_user_id", nullable = false)
    private FoodAppUserEntity foodAppUser;

    @OneToOne(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.JOIN)
    @JoinColumn(name = "address_id", nullable = false, unique = true)
    private AddressEntity address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<MenuEntity> menus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<AppOrderEntity> appOrders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<RestaurantDeliveryAddressEntity> restaurantDeliveryAddresses;
}
