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
}
