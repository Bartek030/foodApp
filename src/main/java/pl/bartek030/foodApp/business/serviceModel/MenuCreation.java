package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"name", "category", "restaurantId"})
@ToString(of = {"name"})
public class MenuCreation {
    String name;
    String category;

    Long restaurantId;
}
