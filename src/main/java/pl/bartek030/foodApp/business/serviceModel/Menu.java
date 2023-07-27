package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "menuId")
@ToString(of = {"name"})
public class Menu {
    Long menuId;
    String name;
    String category;
    Restaurant restaurant;
    Set<Food> foods;
}
