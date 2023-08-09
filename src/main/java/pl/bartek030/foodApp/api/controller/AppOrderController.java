package pl.bartek030.foodApp.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartek030.foodApp.api.dto.AppOrderDTO;
import pl.bartek030.foodApp.api.dto.OrderDetailsCreationDTO;

import java.util.List;

@RequestMapping(AppOrderController.APP_ORDER_URL)
public interface AppOrderController {

    String APP_ORDER_URL = "/app-order";
    String CANCEL_APP_ORDER_URL = "/cancelled/{appOrderId}";
    String MARK_DELIVERED_APP_ORDER_URL = "/delivered/{appOrderId}";
    String NEW_APP_ORDER_URL = "/new";
    String USER_APP_ORDER_ID = "/user/{userId}";
    String RESTAURANT_APP_ORDER_ID = "/restaurant/{restaurantId}";

    @PostMapping(NEW_APP_ORDER_URL)
    ResponseEntity<AppOrderDTO> addOrder(@RequestBody List<OrderDetailsCreationDTO> orderDetailsCreationDTO);

    @PatchMapping(CANCEL_APP_ORDER_URL)
    ResponseEntity<AppOrderDTO> cancelOrder(@PathVariable Long appOrderId);

    @GetMapping(RESTAURANT_APP_ORDER_ID)
    ResponseEntity<List<AppOrderDTO>> getOrdersByRestaurant(@PathVariable Long restaurantId);

    @PatchMapping(MARK_DELIVERED_APP_ORDER_URL)
    ResponseEntity<AppOrderDTO> markAsDelivered(@PathVariable Long orderId);

    @GetMapping(USER_APP_ORDER_ID)
    ResponseEntity<List<AppOrderDTO>> getUsersAppOrders(@PathVariable Long userId);
}
