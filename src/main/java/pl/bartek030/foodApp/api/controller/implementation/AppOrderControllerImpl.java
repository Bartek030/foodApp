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

        AppOrder appOrder = appOrderService.addOrder(orderDetailsCreationDTO.stream()
                .map(orderDetailsCreationDtoMapper::map)
                .toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(appOrderDtoMapper.map(appOrder));
    }

    @Override
    public ResponseEntity<AppOrderDTO> cancelOrder(final Long appOrderId) {
        AppOrder appOrder = appOrderService.cancelOrder(appOrderId);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(appOrderDtoMapper.map(appOrder));
    }

    @Override
    public ResponseEntity<AppOrderDTO> markAsDelivered(final Long appOrderId) {
        AppOrder appOrder = appOrderService.markAsDelivered(appOrderId);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(appOrderDtoMapper.map(appOrder));
    }

    @Override
    public ResponseEntity<List<AppOrderDTO>> getOrdersByRestaurant(final Long restaurantId) {
        List<AppOrder> appOrders = appOrderService.getOrdersByRestaurant(restaurantId);
        return ResponseEntity.ok(
                appOrders.stream()
                        .map(appOrderDtoMapper::map)
                        .toList()
        );
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
