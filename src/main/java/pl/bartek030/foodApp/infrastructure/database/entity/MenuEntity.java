package pl.bartek030.foodApp.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@With
@Getter
@Setter
@EqualsAndHashCode(of = "menuId")
@ToString(of = {"menuId", "name", "category"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menu")
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    private Set<FoodEntity> foods;
}
