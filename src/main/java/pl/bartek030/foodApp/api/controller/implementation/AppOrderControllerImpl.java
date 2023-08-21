package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import pl.bartek030.foodApp.api.controller.AppOrderController;
import pl.bartek030.foodApp.api.dto.AppOrderDTO;
import pl.bartek030.foodApp.api.dto.OrderDetailsCreationDTO;
import pl.bartek030.foodApp.api.dto.mapper.AppOrderDtoMapper;
import pl.bartek030.foodApp.api.dto.mapper.OrderDetailsCreationDtoMapper;
import pl.bartek030.foodApp.business.serviceModel.AppOrder;
import pl.bartek030.foodApp.business.services.AppOrderService;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class AppOrderControllerImpl implements AppOrderController {

    private final OrderDetailsCreationDtoMapper orderDetailsCreationDtoMapper;
    private final AppOrderDtoMapper appOrderDtoMapper;

    private final AppOrderService appOrderService;

    @Override
    public String addOrder(final List<OrderDetailsCreationDTO> orderDetailsCreationDTO) {
        AppOrder appOrder = appOrderService.addOrder(orderDetailsCreationDTO.stream()
                .map(orderDetailsCreationDtoMapper::map)
                .toList());
        return null;
    }

    @Override
    public String getOrdersByRestaurant(final Long restaurantId, final Model model) {
        List<AppOrder> appOrders = appOrderService.getOrdersByRestaurant(restaurantId);

        final List<AppOrderDTO> appOrderDTOList = appOrders.stream()
                .map(appOrderDtoMapper::map)
                .toList();

        model.addAllAttributes(Map.of("appOrders", appOrderDTOList));
        return "restaurant-app-orders";
    }

    @Override
    public String markAsDelivered(final Long orderId) {
//        AppOrder appOrder = appOrderService.markAsDelivered(appOrderId);
        return null;
    }

    @Override
    public String getUsersAppOrders(final Model model) {
        final String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<AppOrder> appOrders = appOrderService.getOrdersByUser(email);

        final List<AppOrderDTO> appOrderDTOList = appOrders.stream()
                .map(appOrderDtoMapper::map)
                .toList();

        model.addAllAttributes(Map.of("appOrders", appOrderDTOList));
        return "app-orders";
    }
}
