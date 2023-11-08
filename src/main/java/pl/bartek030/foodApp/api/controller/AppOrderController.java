package pl.bartek030.foodApp.api.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(AppOrderController.APP_ORDER_URL)
public interface AppOrderController {

    String APP_ORDER_URL = "/app-order";
    String USER_APP_ORDER_ID = "/user";
    String RESTAURANT_APP_ORDER_ID = "/restaurant/{restaurantId}";

    @GetMapping(RESTAURANT_APP_ORDER_ID)
    String getOrdersByRestaurant(@PathVariable Long restaurantId, final Model model);

    @GetMapping(USER_APP_ORDER_ID)
    String getUsersAppOrders(final Model model);
}
