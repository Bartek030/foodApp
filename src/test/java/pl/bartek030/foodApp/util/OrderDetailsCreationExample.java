package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.business.serviceModel.OrderDetailsCreation;

@UtilityClass
public class OrderDetailsCreationExample {

    public OrderDetailsCreation someOrderDetailsCreation1() {
        return OrderDetailsCreation.builder()
                .foodId(1L)
                .quantity(2)
                .name("KEBAB")
                .build();
    }

    public OrderDetailsCreation someOrderDetailsCreation2() {
        return OrderDetailsCreation.builder()
                .foodId(2L)
                .quantity(4)
                .name("PIZZA")
                .build();
    }

    public OrderDetailsCreation someOrderDetailsCreation3() {
        return OrderDetailsCreation.builder()
                .foodId(3L)
                .quantity(1)
                .name("BANAN")
                .build();
    }
}
