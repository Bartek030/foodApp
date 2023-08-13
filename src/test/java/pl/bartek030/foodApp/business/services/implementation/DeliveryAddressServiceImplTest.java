package pl.bartek030.foodApp.business.services.implementation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.infrastructure.database.repository.DeliveryAddressRepository;
import pl.bartek030.foodApp.util.DeliveryAddressExample;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryAddressServiceImplTest {

    @InjectMocks
    DeliveryAddressServiceImpl deliveryAddressService;

    @Mock
    DeliveryAddressRepository deliveryAddressDao;

    @Test
    void givenAddressDataShouldFindDeliveryAddress() {
        // given
        DeliveryAddress expected = DeliveryAddressExample.someDeliveryAddress1();

        when(deliveryAddressDao.findByCountryAndCityAndStreet(anyString(), anyString(), anyString()))
                .thenReturn(Optional.of(DeliveryAddressExample.someDeliveryAddress1()));

        // when
        final Optional<DeliveryAddress> result = deliveryAddressService.findByCountryAndCityAndStreet(
                expected.getCountry(), expected.getCity(), expected.getStreet());

        // then
        if(result.isPresent()) {
            assertEquals(expected, result.get());
            assertEquals(expected.getDeliveryAddressId(), result.get().getDeliveryAddressId());
            assertEquals(expected.getCountry(), result.get().getCountry());
            assertEquals(expected.getCity(), result.get().getCity());
            assertEquals(expected.getStreet(), result.get().getStreet());
        }
    }

    @Test
    void givenDeliveryAddressIdListShouldFindDeliveryAddressList() {
        // given
        List<Long> idList = List.of(1L, 3L);

        when(deliveryAddressDao.findAllById(anyList())).thenReturn(List.of(
                DeliveryAddressExample.someDeliveryAddress1(),
                DeliveryAddressExample.someDeliveryAddress3()
        ));

        // when
        final List<DeliveryAddress> result = deliveryAddressService.findDeliveryAddressesByIdList(idList);

        // then
        assertEquals(idList.size(), result.size());
    }

    @Test
    void givenDeliveryAddressShouldAddDeliveryAddress() {
        // given
        DeliveryAddress expected = DeliveryAddressExample.someDeliveryAddress1();

        when(deliveryAddressDao.addDeliveryAddress(any(DeliveryAddress.class)))
                .thenReturn(DeliveryAddressExample.someDeliveryAddress1());

        // when
        final DeliveryAddress result = deliveryAddressService.addDeliveryAddress(expected);

        // then
        assertEquals(expected, result);
        assertEquals(expected.getCountry(), result.getCountry());
        assertEquals(expected.getCity(), result.getCity());
        assertEquals(expected.getStreet(), result.getStreet());
    }
}