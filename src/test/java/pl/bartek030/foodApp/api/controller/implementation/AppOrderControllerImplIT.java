package pl.bartek030.foodApp.api.controller.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import pl.bartek030.foodApp.api.controller.AppOrderController;
import pl.bartek030.foodApp.api.dto.OrderDetailsCreationDTO;
import pl.bartek030.foodApp.api.dto.mapper.AppOrderDtoMapper;
import pl.bartek030.foodApp.api.dto.mapper.OrderDetailsCreationDtoMapper;
import pl.bartek030.foodApp.business.serviceModel.AppOrder;
import pl.bartek030.foodApp.business.serviceModel.OrderDetailsCreation;
import pl.bartek030.foodApp.business.services.AppOrderService;
import pl.bartek030.foodApp.util.AppOrderDTOExample;
import pl.bartek030.foodApp.util.AppOrderExample;
import pl.bartek030.foodApp.util.OrderDetailsCreationExample;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AppOrderControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations="classpath:application-test.yml")
@AllArgsConstructor(onConstructor = @__(@Autowired))
class AppOrderControllerImplIT {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private OrderDetailsCreationDtoMapper orderDetailsCreationDtoMapper;

    @MockBean
    private AppOrderDtoMapper appOrderDtoMapper;

    @MockBean
    private AppOrderService appOrderService;

    @Test
    void shouldAddOrderCorrectly() throws Exception {
        // given
        final List<OrderDetailsCreation> orderDetailsCreationList = List.of(
                OrderDetailsCreationExample.someOrderDetailsCreation1(),
                OrderDetailsCreationExample.someOrderDetailsCreation2(),
                OrderDetailsCreationExample.someOrderDetailsCreation3()
        );
        final AppOrder appOrder = AppOrderExample.someAppOrder1();

        when(appOrderService.addOrder(anyList())).thenReturn(appOrder);
        when(orderDetailsCreationDtoMapper.map(any(OrderDetailsCreationDTO.class)))
                .thenReturn(OrderDetailsCreationExample.someOrderDetailsCreation1())
                .thenReturn(OrderDetailsCreationExample.someOrderDetailsCreation2())
                .thenReturn(OrderDetailsCreationExample.someOrderDetailsCreation3());
        when(appOrderDtoMapper.map(any(AppOrder.class))).thenReturn(AppOrderDTOExample.someAppOrderDto1());

        ObjectWriter ow = objectMapper.writer();
        String requestJson = ow.writeValueAsString(orderDetailsCreationList);

        // when then
        String endpoint = AppOrderController.APP_ORDER_URL + AppOrderController.NEW_APP_ORDER_URL;
        mockMvc.perform(post(endpoint)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.number", Matchers.is(appOrder.getNumber())))
                .andExpect(jsonPath("$.totalCost", Matchers.is(appOrder.getTotalCost()), BigDecimal.class));
    }

    @Test
    void shouldCancelOrderCorrectly() throws Exception {
        // given
        Long appOrderId = 1L;
        final AppOrder appOrder = AppOrderExample.someAppOrder1();

        when(appOrderService.cancelOrder(any(Long.class))).thenReturn(appOrder);
        when(appOrderDtoMapper.map(any(AppOrder.class))).thenReturn(AppOrderDTOExample.someAppOrderDto1());

        // when then
        String endpoint = AppOrderController.APP_ORDER_URL + AppOrderController.CANCEL_APP_ORDER_URL;
        mockMvc.perform(patch(endpoint, appOrderId))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.number", Matchers.is(appOrder.getNumber())))
                .andExpect(jsonPath("$.totalCost", Matchers.is(appOrder.getTotalCost()), BigDecimal.class));
    }

    @Test
    void shouldMarkOrderAsDelivered() throws Exception {
        // given
        Long appOrderId = 1L;
        final AppOrder appOrder = AppOrderExample.someAppOrder1();

        when(appOrderService.markAsDelivered(any(Long.class))).thenReturn(appOrder);
        when(appOrderDtoMapper.map(any(AppOrder.class))).thenReturn(AppOrderDTOExample.someAppOrderDto1());

        // when then
        String endpoint = AppOrderController.APP_ORDER_URL + AppOrderController.MARK_DELIVERED_APP_ORDER_URL;
        mockMvc.perform(patch(endpoint, appOrderId))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.number", Matchers.is(appOrder.getNumber())))
                .andExpect(jsonPath("$.totalCost", Matchers.is(appOrder.getTotalCost()), BigDecimal.class));
    }

    @Test
    void shouldReturnOrdersByRestaurant() throws Exception {
        // given
        Long restaurantId = 1L;
        final AppOrder appOrder = AppOrderExample.someAppOrder1();

        final List<AppOrder> appOrders = List.of(
                AppOrderExample.someAppOrder1(),
                AppOrderExample.someAppOrder2(),
                AppOrderExample.someAppOrder3()
        );
        when(appOrderService.getOrdersByRestaurant(any(Long.class))).thenReturn(appOrders);
        when(appOrderDtoMapper.map(any(AppOrder.class)))
                .thenReturn(AppOrderDTOExample.someAppOrderDto1())
                .thenReturn(AppOrderDTOExample.someAppOrderDto2())
                .thenReturn(AppOrderDTOExample.someAppOrderDto3());

        // when then
        String endpoint = AppOrderController.APP_ORDER_URL + AppOrderController.RESTAURANT_APP_ORDER_ID;
        mockMvc.perform(get(endpoint, restaurantId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].number", Matchers.is(appOrder.getNumber())))
                .andExpect(jsonPath("$[0].totalCost", Matchers.is(appOrder.getTotalCost()), BigDecimal.class));
    }

    @Test
    void shouldReturnOrdersByUser() throws Exception {
        // given
        Long userId = 1L;
        final AppOrder appOrder = AppOrderExample.someAppOrder1();

        final List<AppOrder> appOrders = List.of(
                AppOrderExample.someAppOrder1(),
                AppOrderExample.someAppOrder2(),
                AppOrderExample.someAppOrder3()
        );
        when(appOrderService.getOrdersByUser(any(Long.class))).thenReturn(appOrders);
        when(appOrderDtoMapper.map(any(AppOrder.class)))
                .thenReturn(AppOrderDTOExample.someAppOrderDto1())
                .thenReturn(AppOrderDTOExample.someAppOrderDto2())
                .thenReturn(AppOrderDTOExample.someAppOrderDto3());

        // when then
        String endpoint = AppOrderController.APP_ORDER_URL + AppOrderController.USER_APP_ORDER_ID;
        mockMvc.perform(get(endpoint, userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].number", Matchers.is(appOrder.getNumber())))
                .andExpect(jsonPath("$[0].totalCost", Matchers.is(appOrder.getTotalCost()), BigDecimal.class));
    }
}