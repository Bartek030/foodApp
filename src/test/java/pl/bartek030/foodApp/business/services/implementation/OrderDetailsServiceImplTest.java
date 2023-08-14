package pl.bartek030.foodApp.business.services.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartek030.foodApp.business.serviceModel.AppOrder;
import pl.bartek030.foodApp.business.serviceModel.OrderDetailsCreation;
import pl.bartek030.foodApp.infrastructure.database.repository.OrderDetailsRepository;
import pl.bartek030.foodApp.util.AppOrderExample;
import pl.bartek030.foodApp.util.FoodExample;
import pl.bartek030.foodApp.util.OrderDetailsCreationExample;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderDetailsServiceImplTest {

    @InjectMocks
    OrderDetailsServiceImpl orderDetailsService;

    @Mock
    OrderDetailsRepository orderDetailsDAO;

    @Mock
    FoodServiceImpl foodService;

    @Test
    void givenOrderListShouldCreateOrders() {
        // given
        final List<OrderDetailsCreation> orderDetailsCreations = List.of(
                OrderDetailsCreationExample.someOrderDetailsCreation1(),
                OrderDetailsCreationExample.someOrderDetailsCreation2(),
                OrderDetailsCreationExample.someOrderDetailsCreation3()
        );
        final AppOrder appOrder = AppOrderExample.someAppOrder1();

        when(foodService.findFoodById(any(Long.class)))
                .thenReturn(FoodExample.someFood1())
                .thenReturn(FoodExample.someFood2())
                .thenReturn(FoodExample.someFood3());
        doNothing().when(orderDetailsDAO).addAllOrderDetails(anyList());

        // when
        orderDetailsService.addOrders(orderDetailsCreations, appOrder);

        // then
        verify(foodService, times(3)).findFoodById(any(Long.class));
    }

    @Test
    void givenOrderDetailShouldCalculateCost() {
        // given
        final OrderDetailsCreation orderDetailsCreation = OrderDetailsCreationExample.someOrderDetailsCreation1();
        final BigDecimal expected = BigDecimal.valueOf(60);

        when(foodService.findFoodById(any(Long.class))).thenReturn(FoodExample.someFood1());

        // when
        final BigDecimal actual = orderDetailsService.calculateCost(orderDetailsCreation);

        // then
        Assertions.assertEquals(expected, actual);
    }

}