package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "foodId")
@ToString(of = {"name"})
public class Food {

    Long foodId;
    String name;
    BigDecimal price;
    String description;
    Menu menu;
    Set<OrderDetails> orderDetails;
}
