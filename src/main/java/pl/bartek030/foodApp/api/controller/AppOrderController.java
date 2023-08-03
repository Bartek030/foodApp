package pl.bartek030.foodApp.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartek030.foodApp.api.dto.AppOrderDTO;
import pl.bartek030.foodApp.api.dto.OrderDetailsCreationDTO;

import java.util.List;

@RequestMapping(AppOrderController.APP_ORDER_URL)
public interface AppOrderController {

    String APP_ORDER_URL = "/app-order";
    String APP_ORDER_ID_URL = "/{orderId}";
    String NEW_APP_ORDER_URL = "/new";

    @PostMapping(NEW_APP_ORDER_URL)
    ResponseEntity<AppOrderDTO> addOrder(@RequestBody List<OrderDetailsCreationDTO> orderDetailsCreationDTO);

    @DeleteMapping(APP_ORDER_ID_URL)
    ResponseEntity<String> cancelOrder(@PathVariable Long orderId);

    @GetMapping
    ResponseEntity<List<AppOrderDTO>> getOrdersByRestaurant(@RequestParam Long restaurantId);

    @PatchMapping(APP_ORDER_ID_URL)
    ResponseEntity<String> markAsDelivered(@PathVariable Long orderId);
}
