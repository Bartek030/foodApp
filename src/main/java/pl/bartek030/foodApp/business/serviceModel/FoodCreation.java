package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;

import java.math.BigDecimal;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"name", "menuId"})
@ToString(of = {"name"})
public class FoodCreation {

    String name;
    BigDecimal price;
    String description;
    Long menuId;
}
