package pl.bartek030.foodApp.infrastructure.database.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;
import pl.bartek030.foodApp.infrastructure.database.entity.DeliveryAddressEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.DeliveryAddressDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.DeliveryAddressJpaRepository;
import pl.bartek030.foodApp.util.DeliveryAddressEntityExample;
import pl.bartek030.foodApp.util.DeliveryAddressExample;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryAddressRepositoryTest {

    @InjectMocks
    private DeliveryAddressRepository deliveryAddressRepository;

    @Mock
    private DeliveryAddressJpaRepository deliveryAddressJpaRepository;

    @Mock
    private DeliveryAddressDaoMapper deliveryAddressDaoMapper;

    @Test
    void shouldFindDeliveryAddressByAddressData() {
        // given
        final String country = "Polska";
        final String city = "Warszawa";
        final String street = "Dluga";

        when(deliveryAddressJpaRepository.findByCountryAndCityAndStreet(anyString(), anyString(), anyString()))
                .thenReturn(Optional.of(DeliveryAddressEntityExample.someDeliveryAddressEntity1()));
        when(deliveryAddressDaoMapper.mapDeliveryAddressFromEntity(any(DeliveryAddressEntity.class)))
                .thenReturn(DeliveryAddressExample.someDeliveryAddress1());

        // when
        final Optional<DeliveryAddress> result =
                deliveryAddressRepository.findByCountryAndCityAndStreet(country, city, street);

        // then
        assertTrue(result.isPresent());
        assertEquals(country, result.get().getCountry());
        assertEquals(city, result.get().getCity());
        assertEquals(street, result.get().getStreet());
    }

    @Test
    void shouldFindAllDeliveryAddressesByIdList() {
        // given
        final List<Long> idList = List.of(1L, 2L, 3L);

        when(deliveryAddressJpaRepository.findAllById(anyList())).thenReturn(List.of(
                DeliveryAddressEntityExample.someDeliveryAddressEntity1(),
                DeliveryAddressEntityExample.someDeliveryAddressEntity2(),
                DeliveryAddressEntityExample.someDeliveryAddressEntity3()
        ));
        when(deliveryAddressDaoMapper.mapDeliveryAddressFromEntity(any(DeliveryAddressEntity.class)))
                .thenReturn(DeliveryAddressExample.someDeliveryAddress1())
                .thenReturn(DeliveryAddressExample.someDeliveryAddress2())
                .thenReturn(DeliveryAddressExample.someDeliveryAddress3());

        // when
        final List<DeliveryAddress> result = deliveryAddressRepository.findAllById(idList);

        // then
        assertEquals(3, result.size());
    }

    @Test
    void shouldAddDeliveryAddressCorrectly() {
        // given
        final DeliveryAddress deliveryAddress = DeliveryAddressExample.someDeliveryAddress1();

        when(deliveryAddressDaoMapper.mapDeliveryAddressToEntity(any(DeliveryAddress.class)))
                .thenReturn(DeliveryAddressEntityExample.someDeliveryAddressEntity1());
        when(deliveryAddressJpaRepository.save(any(DeliveryAddressEntity.class)))
                .thenReturn(DeliveryAddressEntityExample.someDeliveryAddressEntity1());
        when(deliveryAddressDaoMapper.mapDeliveryAddressFromEntity(any(DeliveryAddressEntity.class)))
                .thenReturn(DeliveryAddressExample.someDeliveryAddress1());

        // when
        final DeliveryAddress result = deliveryAddressRepository.addDeliveryAddress(deliveryAddress);

        // then
        assertEquals(deliveryAddress.getCountry(), result.getCountry());
        assertEquals(deliveryAddress.getCity(), result.getCity());
        assertEquals(deliveryAddress.getStreet(), result.getStreet());
    }
}