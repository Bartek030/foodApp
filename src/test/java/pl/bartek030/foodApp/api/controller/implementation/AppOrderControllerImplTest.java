package pl.bartek030.foodApp.api.controller.implementation;

import lombok.AllArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pl.bartek030.foodApp.api.controller.rest.AppOrderRestController;
import pl.bartek030.foodApp.api.dto.mapper.AppOrderDtoMapper;
import pl.bartek030.foodApp.business.serviceModel.AppOrder;
import pl.bartek030.foodApp.business.services.AppOrderService;
import pl.bartek030.foodApp.util.AppOrderDTOExample;
import pl.bartek030.foodApp.util.AppOrderExample;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = AppOrderControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class AppOrderControllerImplTest {

    @MockBean
    private AppOrderDtoMapper appOrderDtoMapper;

    @MockBean
    private AppOrderService appOrderService;

    private MockMvc mockMvc;

    @Test
    void getOrdersByRestaurantWorksCorrectly() throws Exception {
        // given
        Long restaurantId = 1L;

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
        String endpoint = AppOrderControllerImpl.APP_ORDER_URL + AppOrderControllerImpl.RESTAURANT_APP_ORDER_ID;
        mockMvc.perform(get(endpoint, restaurantId))
                .andExpect(status().isOk())
                .andExpect(view().name("restaurant-app-orders"));
    }
}