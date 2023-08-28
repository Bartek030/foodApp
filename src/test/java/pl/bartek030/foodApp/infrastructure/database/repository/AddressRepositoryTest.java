package pl.bartek030.foodApp.infrastructure.database.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartek030.foodApp.business.serviceModel.Address;
import pl.bartek030.foodApp.infrastructure.database.entity.AddressEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.AddressDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.AddressJpaRepository;
import pl.bartek030.foodApp.util.AddressEntityExample;
import pl.bartek030.foodApp.util.AddressExample;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressRepositoryTest {

    @InjectMocks
    private AddressRepository addressRepository;

    @Mock
    private AddressJpaRepository addressJpaRepository;

    @Mock
    AddressDaoMapper addressDaoMapper;

    @Test
    void shouldReturnAddressByAddressData() {
        // given
        final String country = "Polska";
        final String city = "Warszawa";
        final String street = "Dluga";
        final String number = "22";
        final String zipCode = "30-300";

        when(addressJpaRepository.findByCountryAndCityAndStreetAndNumberAndZipCode(
                any(String.class), any(String.class), any(String.class), any(String.class), any(String.class)
        )).thenReturn(Optional.of(AddressEntityExample.someAddressEntity1()));

        when(addressDaoMapper.mapAddressFromEntity(any(AddressEntity.class)))
                .thenReturn(AddressExample.someAddress1());

        // when
        final Optional<Address> result = addressRepository.findAddressByData(country, city, street, number, zipCode);

        // then
        assertTrue(result.isPresent());
        assertEquals(country, result.get().getCountry());
        assertEquals(city, result.get().getCity());
        assertEquals(street, result.get().getStreet());
        assertEquals(number, result.get().getNumber());
        assertEquals(zipCode, result.get().getZipCode());
    }

    @Test
    void shouldCreate() {
        // given
        final Address address = AddressExample.someAddress1();

        when(addressDaoMapper.mapAddressToEntity(any(Address.class)))
                .thenReturn(AddressEntityExample.someAddressEntity1());

        when(addressJpaRepository.save(any())).thenReturn(AddressEntityExample.someAddressEntity1());

        when(addressDaoMapper.mapAddressFromEntity(any(AddressEntity.class))).thenReturn(AddressExample.someAddress1());

        // when
        final Address result = addressRepository.createAddress(address);

        // then
        assertEquals(address.getCountry(), result.getCountry());
        assertEquals(address.getCity(), result.getCity());
        assertEquals(address.getStreet(), result.getStreet());
        assertEquals(address.getNumber(), result.getNumber());
        assertEquals(address.getZipCode(), result.getZipCode());
    }

}