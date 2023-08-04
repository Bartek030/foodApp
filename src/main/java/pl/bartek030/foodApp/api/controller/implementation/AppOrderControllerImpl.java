package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.bartek030.foodApp.api.controller.AppOrderController;
import pl.bartek030.foodApp.api.dto.AppOrderDTO;
import pl.bartek030.foodApp.api.dto.OrderDetailsCreationDTO;
import pl.bartek030.foodApp.api.dto.mapper.AppOrderDtoMapper;
import pl.bartek030.foodApp.api.dto.mapper.OrderDetailsCreationDtoMapper;
import pl.bartek030.foodApp.business.serviceModel.AppOrder;
import pl.bartek030.foodApp.business.services.AppOrderService;

import java.util.List;

@RestController
@AllArgsConstructor
public class AppOrderControllerImpl implements AppOrderController {

    private final OrderDetailsCreationDtoMapper orderDetailsCreationDtoMapper;
    private final AppOrderDtoMapper appOrderDtoMapper;

    private final AppOrderService appOrderService;

    @Override
    public ResponseEntity<AppOrderDTO> addOrder(final List<OrderDetailsCreationDTO> orderDetailsCreationDTO) {

        final AppOrder appOrder = appOrderService.addOrder(orderDetailsCreationDTO.stream()
                .map(orderDetailsCreationDtoMapper::map)
                .toList());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<String> cancelOrder(final Long orderId) {
        return null;
    }

    @Override
    public ResponseEntity<List<AppOrderDTO>> getOrdersByRestaurant(final Long restaurantId) {
        return null;
    }

    @Override
    public ResponseEntity<String> markAsDelivered(final Long orderId) {
        return null;
    }

    @Override
    public ResponseEntity<List<AppOrderDTO>> getUsersAppOrders(final Long userId) {
        List<AppOrder> appOrders = appOrderService.getOrdersByUser(userId);
        return ResponseEntity.ok(
                appOrders.stream()
                        .map(appOrderDtoMapper::map)
                        .toList()
                );
    }
}
