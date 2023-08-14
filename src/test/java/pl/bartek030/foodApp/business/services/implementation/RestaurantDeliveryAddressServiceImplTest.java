package pl.bartek030.foodApp.business.services.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.business.serviceModel.RestaurantDeliveryAddress;
import pl.bartek030.foodApp.infrastructure.database.repository.RestaurantDeliveryAddressRepository;
import pl.bartek030.foodApp.util.DeliveryAddressExample;
import pl.bartek030.foodApp.util.RestaurantDeliveryAddressExample;
import pl.bartek030.foodApp.util.RestaurantExample;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantDeliveryAddressServiceImplTest {

    @InjectMocks
    RestaurantDeliveryAddressServiceImpl restaurantDeliveryAddressService;

    @Mock
    RestaurantDeliveryAddressRepository restaurantDeliveryAddressDao;

    @Mock
    DeliveryAddressServiceImpl deliveryAddressService;

    @Mock
    RestaurantServiceImpl restaurantService;

    @Test
    void givenAddressShouldReturnRestaurantsList() {
        // given
        final String country = "Polska";
        final String city = "Warszawa";
        final String street = "Dluga";

        final List<RestaurantDeliveryAddress> expectedList = List.of(
                RestaurantDeliveryAddressExample.someRestaurantDeliveryAddress1(),
                RestaurantDeliveryAddressExample.someRestaurantDeliveryAddress2(),
                RestaurantDeliveryAddressExample.someRestaurantDeliveryAddress3()
        );

        when(deliveryAddressService.findByCountryAndCityAndStreet(anyString(), anyString(), anyString()))
                .thenReturn(Optional.ofNullable(DeliveryAddressExample.someDeliveryAddress1()));
        when(restaurantDeliveryAddressDao.findAllByAddress(any(DeliveryAddress.class)))
                .thenReturn(List.of(
                        RestaurantDeliveryAddressExample.someRestaurantDeliveryAddress1(),
                        RestaurantDeliveryAddressExample.someRestaurantDeliveryAddress2(),
                        RestaurantDeliveryAddressExample.someRestaurantDeliveryAddress3()
                ));

        // when
        final List<RestaurantDeliveryAddress> actualList =
                restaurantDeliveryAddressService.findByAddress(country, city, street);

        // then
        Assertions.assertEquals(expectedList.size(), actualList.size());
    }

    @Test
    void givenAddressShouldThrownException() {
        // given
        final String country = "Polska";
        final String city = "Warszawa";
        final String street = "Dluga";
        final String expectedExceptionMessage = "Delivery address not found";

        when(deliveryAddressService.findByCountryAndCityAndStreet(anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException(expectedExceptionMessage));

        // when then
        final RuntimeException actualException = assertThrows(
                RuntimeException.class,
                () -> restaurantDeliveryAddressService.findByAddress(country, city, street)
        );
        assertEquals(expectedExceptionMessage, actualException.getMessage());
    }

    @Test
    void givenDeliveryAddressAndRestaurantShouldReturnRestaurantDeliveryAddress() {
        // given
        final DeliveryAddress deliveryAddress = DeliveryAddressExample.someDeliveryAddress1();
        final Restaurant restaurant = RestaurantExample.someRestaurant1();
        final RestaurantDeliveryAddress expected = RestaurantDeliveryAddressExample.someRestaurantDeliveryAddress1();

        when(restaurantDeliveryAddressDao.findByAddressAndRestaurant(any(DeliveryAddress.class), any(Restaurant.class)))
                .thenReturn(Optional.of(expected));

        // when
        final RestaurantDeliveryAddress actual =
                restaurantDeliveryAddressService.findByAddressAndRestaurant(deliveryAddress, restaurant);

        // then
        assertEquals(expected, actual);
    }

    @Test
    void givenDeliveryAddressAndRestaurantShouldThrownException() {
        // given
        final DeliveryAddress deliveryAddress = DeliveryAddressExample.someDeliveryAddress1();
        final Restaurant restaurant = RestaurantExample.someRestaurant1();
        final String expectedExceptionMessage = "Delivery address not found";

        when(restaurantDeliveryAddressDao.findByAddressAndRestaurant(any(DeliveryAddress.class), any(Restaurant.class)))
                .thenThrow(new RuntimeException(expectedExceptionMessage));

        // when then
        final RuntimeException actualException = assertThrows(
                RuntimeException.class,
                () -> restaurantDeliveryAddressService.findByAddressAndRestaurant(deliveryAddress, restaurant)
        );
        assertEquals(expectedExceptionMessage, actualException.getMessage());
    }
}