package pl.bartek030.foodApp.business.services.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.bartek030.foodApp.business.serviceModel.*;
import pl.bartek030.foodApp.business.util.OffsetDateTimeWrapper;
import pl.bartek030.foodApp.infrastructure.database.enums.OrderStatus;
import pl.bartek030.foodApp.infrastructure.database.repository.AppOrderRepository;
import pl.bartek030.foodApp.util.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppOrderServiceImplTest {

    @InjectMocks
    AppOrderServiceImpl appOrderService;

    @Mock
    private AppOrderRepository appOrderDAO;

    @Mock
    private OrderDetailsServiceImpl orderDetailsService;

    @Mock
    private FoodServiceImpl foodService;

    @Mock
    private FoodAppUserServiceImpl foodAppUserService;

    @Mock
    private RestaurantDeliveryAddressServiceImpl restaurantDeliveryAddressService;

    @Mock
    private RestaurantServiceImpl restaurantService;

    @Mock
    private DeliveryAddressServiceImpl deliveryAddressService;

    @Mock
    OffsetDateTimeWrapper offsetDateTimeWrapper;

    @Mock
    SecurityContext securityContext;

    @Mock
    Authentication authentication;

    @Test
    void givenOrderDetailsListShouldCreateNewAppOrder() {
        // given
        List<OrderDetailsCreation> orderDetailsCreationList = List.of(
                OrderDetailsCreationExample.someOrderDetailsCreation1(),
                OrderDetailsCreationExample.someOrderDetailsCreation2(),
                OrderDetailsCreationExample.someOrderDetailsCreation3()
        );
        Long appOrderNumber = 5L;

        AppOrder expected = AppOrderExample.someAppOrder1()
                .withNumber("XXX-YYY-6")
                .withTotalCost(BigDecimal.valueOf(270));

        when(appOrderDAO.addAppOrder(any(AppOrder.class))).thenReturn(AppOrderExample.someAppOrder1());
        when(restaurantService.findById(any(Long.class))).thenReturn(RestaurantExample.someRestaurant1());

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication().getName()).thenReturn("TEST");

        when(foodService.findFoodById(any(Long.class)))
                .thenReturn(FoodExample.someFood1())
                .thenReturn(FoodExample.someFood2())
                .thenReturn(FoodExample.someFood3());

        when(foodAppUserService.findByEmail(any(String.class))).thenReturn(FoodAppUserExample.someFoodAppUser1());

        when(appOrderDAO.countAllRecords()).thenReturn(appOrderNumber);

        when(orderDetailsService.calculateCost(any(OrderDetailsCreation.class)))
                .thenReturn(BigDecimal.valueOf(60))
                .thenReturn(BigDecimal.valueOf(160))
                .thenReturn(BigDecimal.valueOf(50));

        when(deliveryAddressService.findByCountryAndCityAndStreet(anyString(), anyString(), anyString()))
                .thenReturn(Optional.of(DeliveryAddressExample.someDeliveryAddress1()));

        when(restaurantDeliveryAddressService.findByAddressAndRestaurant(any(DeliveryAddress.class), any(Restaurant.class)))
                .thenReturn(RestaurantDeliveryAddressExample.someRestaurantDeliveryAddress1());

        // when
        final AppOrder result = appOrderService.addOrder(orderDetailsCreationList);

        // then
        assertEquals(expected, result);
    }

    @Test
    void givenOrderDetailsShouldThrownExceptionWhenNoRestaurantHasBeenFound() {
        // given
        List<OrderDetailsCreation> orderDetailsCreationList = List.of(
                OrderDetailsCreationExample.someOrderDetailsCreation1(),
                OrderDetailsCreationExample.someOrderDetailsCreation2(),
                OrderDetailsCreationExample.someOrderDetailsCreation3()
        );
        String expectedExceptionMessage = "No restaurant has been found!";

        when(restaurantService.findById(any(Long.class)))
                .thenThrow(new RuntimeException("No restaurant has been found!"));
        when(foodService.findFoodById(any(Long.class)))
                .thenReturn(FoodExample.someFood1())
                .thenReturn(FoodExample.someFood2())
                .thenReturn(FoodExample.someFood3());

        // when then
        final RuntimeException actualException = assertThrows(
                RuntimeException.class,
                () -> appOrderService.addOrder(orderDetailsCreationList)
        );
        assertEquals(expectedExceptionMessage, actualException.getMessage());
    }

    @Test
    void givenFoodAppUserIdShouldReturnListOfAppOrders() {
        // given
        Long userID = 1L;
        final List<AppOrder> expected = List.of(
                AppOrderExample.someAppOrder1(),
                AppOrderExample.someAppOrder2()
        );

        when(foodAppUserService.findById(any(Long.class))).thenReturn(FoodAppUserExample.someFoodAppUser1());
        when(appOrderDAO.getAppOrdersByUser(any(FoodAppUser.class))).thenReturn(List.of(
                AppOrderExample.someAppOrder1(),
                AppOrderExample.someAppOrder2()
        ));

        // when
        final List<AppOrder> result = appOrderService.getOrdersByUser(userID);

        // then
        assertEquals(expected.size(), result.size());
    }

    @Test
    void givenRestaurantIdShouldReturnListOfAppOrders() {
        // given
        Long restaurantId = 1L;
        final List<AppOrder> expected = List.of(
                AppOrderExample.someAppOrder1(),
                AppOrderExample.someAppOrder2(),
                AppOrderExample.someAppOrder3()
        );

        when(restaurantService.findById(any(Long.class))).thenReturn(RestaurantExample.someRestaurant1());
        when(appOrderDAO.getAppOrdersByRestaurant(any(Restaurant.class))).thenReturn(List.of(
                AppOrderExample.someAppOrder1(),
                AppOrderExample.someAppOrder2(),
                AppOrderExample.someAppOrder3()
        ));

        // when
        final List<AppOrder> result = appOrderService.getOrdersByRestaurant(restaurantId);

        // then
        assertEquals(expected.size(), result.size());
    }

    @Test
    void givenAppOrderIdShouldCancelOrder() {
        // given
        final Long appOrderId = 1L;
        final AppOrder expected = AppOrderExample.someAppOrder1()
                .withStatus(OrderStatus.CANCELLED);

        when(appOrderDAO.findById(any(Long.class))).thenReturn(Optional.of(AppOrderExample.someAppOrder1()));
        when(offsetDateTimeWrapper.getCurrentTime())
                .thenReturn(OffsetDateTime.of(2022, 10, 10, 10, 15, 10, 1000, ZoneOffset.UTC));
        when(appOrderDAO.update(any(Long.class), any(OrderStatus.class)))
                .thenReturn(AppOrderExample.someAppOrder1().withStatus(OrderStatus.CANCELLED));

        // when
        final AppOrder result = appOrderService.cancelOrder(appOrderId);

        // then
        assertEquals(expected, result);
        assertEquals(expected.getStatus(), result.getStatus());
    }

    @Test
    void givenNotExistingAppOrderIdShouldThrownException() {
        // given
        final Long appOrderId = 1L;
        final String expectedExceptionMessage = "AppOrder with id: [%s] has not been found".formatted(appOrderId);

        when(appOrderDAO.findById(any(Long.class)))
                .thenThrow(new RuntimeException(expectedExceptionMessage));

        // when then
        final RuntimeException actualException = assertThrows(
                RuntimeException.class,
                () -> appOrderService.cancelOrder(appOrderId)
        );
        assertEquals(expectedExceptionMessage, actualException.getMessage());
    }

    @Test
    void cancellingAppOrderAfter20minutesShouldThrownException() {
        // given
        final Long appOrderId = 1L;
        final String expectedExceptionMessage = "You can cancel your order before 20 minutes from order time";

        when(appOrderDAO.findById(any(Long.class))).thenReturn(Optional.of(AppOrderExample.someAppOrder1()));
        when(offsetDateTimeWrapper.getCurrentTime())
                .thenReturn(OffsetDateTime.of(2022, 10, 10, 12, 10, 10, 1000, ZoneOffset.UTC));

        // when then
        final RuntimeException actualException = assertThrows(
                RuntimeException.class,
                () -> appOrderService.cancelOrder(appOrderId)
        );
        assertEquals(expectedExceptionMessage, actualException.getMessage());
    }

    @Test
    void givenAppOrderIdShouldMarkOrderDelivered() {
        // given
        final Long appOrderId = 1L;
        final AppOrder expected = AppOrderExample.someAppOrder1().withStatus(OrderStatus.DELIVERED);

        when(appOrderDAO.findById(any(Long.class))).thenReturn(Optional.of(expected.withStatus(OrderStatus.ORDERED)));
        when(appOrderDAO.update(any(Long.class), any(OrderStatus.class)))
                .thenReturn(AppOrderExample.someAppOrder1().withStatus(OrderStatus.DELIVERED));

        // when
        final AppOrder result = appOrderService.markAsDelivered(appOrderId);

        // then
        assertEquals(expected, result);
        assertEquals(expected.getStatus(), result.getStatus());
    }
}