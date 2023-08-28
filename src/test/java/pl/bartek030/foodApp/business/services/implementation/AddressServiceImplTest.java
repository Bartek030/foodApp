package pl.bartek030.foodApp.business.services.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartek030.foodApp.business.serviceModel.Address;
import pl.bartek030.foodApp.business.serviceModel.RestaurantCreation;
import pl.bartek030.foodApp.infrastructure.database.repository.AddressRepository;
import pl.bartek030.foodApp.util.AddressExample;
import pl.bartek030.foodApp.util.RestaurantCreationExample;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private AddressRepository addressDAO;

    @Test
    void givenExistingAddressDataShouldReturnAddressCorrectly() {
        // given
        Address address = AddressExample.someAddress1();

        when(addressDAO.findAddressByData(anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Optional.of(address));

        // when
        final Optional<Address> result = addressService.findAddressByData(
                address.getCountry(),
                address.getCity(),
                address.getStreet(),
                address.getNumber(),
                address.getZipCode());

        // then
        assertEquals(address, result.orElseThrow());
        assertEquals(address.getAddressId(), result.orElseThrow().getAddressId());
        assertEquals(address.getCountry(), result.orElseThrow().getCountry());
        assertEquals(address.getCity(), result.orElseThrow().getCity());
        assertEquals(address.getStreet(), result.orElseThrow().getStreet());
        assertEquals(address.getNumber(), result.orElseThrow().getNumber());
        assertEquals(address.getZipCode(), result.orElseThrow().getZipCode());
    }

    @Test
    void givenNonExistingAddressDataShouldReturnEmptyAddress() {
        // given
        Address address = AddressExample.someAddress1();

        when(addressDAO.findAddressByData(anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Optional.empty());

        // when
        final Optional<Address> result = addressService.findAddressByData(
                address.getCountry(),
                address.getCity(),
                address.getStreet(),
                address.getNumber(),
                address.getZipCode());

        // then
        assertEquals(Optional.empty(), result);
    }

    @Test
    void givenRestaurantCreationAddressShouldCreateAddress() {
        // given
        RestaurantCreation restaurantCreation = RestaurantCreationExample.someRestaurantCreation1();
        Address address = AddressExample.someAddress1();

        when(addressDAO.createAddress(any(Address.class))).thenReturn(AddressExample.someAddress1());

        // when
        final Address result = addressService.createAddressFromRestaurant(restaurantCreation);

        // then
        assertEquals(address, result);
        assertEquals(address.getAddressId(), result.getAddressId());
        assertEquals(address.getCountry(), result.getCountry());
        assertEquals(address.getCity(), result.getCity());
        assertEquals(address.getStreet(), result.getStreet());
        assertEquals(address.getNumber(), result.getNumber());
        assertEquals(address.getZipCode(), result.getZipCode());
    }

    @Test
    void givenAddressShouldCreateAddress() {
        // given
        Address address = AddressExample.someAddress1();

        when(addressDAO.createAddress(any(Address.class))).thenReturn(AddressExample.someAddress1());

        // when
        final Address result = addressService.createAddress(address);

        // then
        assertEquals(address, result);
        assertEquals(address.getAddressId(), result.getAddressId());
        assertEquals(address.getCountry(), result.getCountry());
        assertEquals(address.getCity(), result.getCity());
        assertEquals(address.getStreet(), result.getStreet());
        assertEquals(address.getNumber(), result.getNumber());
        assertEquals(address.getZipCode(), result.getZipCode());
    }
}