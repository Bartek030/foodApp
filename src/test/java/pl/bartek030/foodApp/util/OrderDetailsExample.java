package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.business.serviceModel.OrderDetails;

@UtilityClass
public class OrderDetailsExample {

    public OrderDetails someOrderDetails1() {
        return OrderDetails.builder()
                .orderDetailsId(1L)
                .quantity(1)
                .food(FoodExample.someFood1())
                .appOrder(AppOrderExample.someAppOrder1())
                .build();
    }

    public OrderDetails someOrderDetails2() {
        return OrderDetails.builder()
                .orderDetailsId(2L)
                .quantity(2)
                .food(FoodExample.someFood2())
                .appOrder(AppOrderExample.someAppOrder1())
                .build();
    }

    public OrderDetails someOrderDetails3() {
        return OrderDetails.builder()
                .orderDetailsId(3L)
                .quantity(3)
                .food(FoodExample.someFood3())
                .appOrder(AppOrderExample.someAppOrder1())
                .build();
    }
}
