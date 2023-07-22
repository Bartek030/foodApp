package pl.bartek030.foodApp.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "foodAppUserId")
@ToString(of = {"foodAppUserId", "name", "surname", "email", "phone"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "food_app_user")
public class FoodAppUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_app_user_id")
    private Long foodAppUserId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false, unique = true)
    private AddressEntity address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "foodAppUser")
    private Set<RestaurantEntity> restaurants;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "foodAppUser")
    private Set<AppOrderEntity> appOrders;
}
