package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.business.serviceModel.Address;

@UtilityClass
public class AddressExample {

    public Address someAddress1() {
        return Address.builder()
                .addressId(1L)
                .country("Polska")
                .city("Warszawa")
                .street("Dluga")
                .number("22")
                .zipCode("30-300")
                .build();
    }

    public Address someAddress2() {
        return Address.builder()
                .addressId(2L)
                .country("Polska")
                .city("Krakow")
                .street("Mickiewicza")
                .number("12")
                .zipCode("30-302")
                .build();
    }

    public Address someAddress3() {
        return Address.builder()
                .addressId(3L)
                .country("Polska")
                .city("Torun")
                .street("Lewa")
                .number("1")
                .zipCode("30-305")
                .build();
    }
}
