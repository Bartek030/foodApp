package pl.bartek030.foodApp.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "orderDetailsId")
@ToString(of = {"orderDetailsId", "quantity"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_details_id")
    private Long orderDetailsId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    private FoodEntity food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_order_id", nullable = false)
    private AppOrderEntity appOrder;
}
