package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "orderDetailsId")
@ToString(of = {"food", "quantity", "appOrder"})
public class OrderDetails {

    Long orderDetailsId;
    Integer quantity;
    Food food;
    AppOrder appOrder;
}
