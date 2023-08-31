package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.infrastructure.database.entity.DeliveryAddressEntity;

@UtilityClass
public class DeliveryAddressEntityExample {

    public DeliveryAddressEntity someDeliveryAddressEntity1() {
        return DeliveryAddressEntity.builder()
                .country("COUNTRY1")
                .city("CITY1")
                .street("STREET1")
                .build();
    }

    public DeliveryAddressEntity someDeliveryAddressEntity2() {
        return DeliveryAddressEntity.builder()
                .country("COUNTRY2")
                .city("CITY2")
                .street("STREET2")
                .build();
    }

    public DeliveryAddressEntity someDeliveryAddressEntity3() {
        return DeliveryAddressEntity.builder()
                .country("COUNTRY3")
                .city("CITY3")
                .street("STREET3")
                .build();
    }
}
