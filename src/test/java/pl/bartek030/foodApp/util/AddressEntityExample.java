package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.infrastructure.database.entity.AddressEntity;

@UtilityClass
public class AddressEntityExample {

    public AddressEntity someAddressEntity1() {
        return AddressEntity.builder()
                .country("COUNTRY1")
                .city("CITY1")
                .street("STREET1")
                .number("22")
                .zipCode("38-300")
                .build();
    }

    public AddressEntity someAddressEntity2() {
        return AddressEntity.builder()
                .country("COUNTRY2")
                .city("CITY2")
                .street("STREET2")
                .number("22")
                .zipCode("38-300")
                .build();
    }

    public AddressEntity someAddressEntity3() {
        return AddressEntity.builder()
                .country("COUNTRY3")
                .city("CITY3")
                .street("STREET3")
                .number("22")
                .zipCode("38-300")
                .build();
    }
}
