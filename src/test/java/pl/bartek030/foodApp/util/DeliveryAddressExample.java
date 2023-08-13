package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddress;

@UtilityClass
public class DeliveryAddressExample {

    public DeliveryAddress someDeliveryAddress1() {
        return DeliveryAddress.builder()
                .deliveryAddressId(1L)
                .country("Polska")
                .city("Warszawa")
                .street("Dluga")
                .build();
    }

    public DeliveryAddress someDeliveryAddress2() {
        return DeliveryAddress.builder()
                .deliveryAddressId(2L)
                .country("Polska")
                .city("Krakow")
                .street("Mickiewicza")
                .build();
    }

    public DeliveryAddress someDeliveryAddress3() {
        return DeliveryAddress.builder()
                .deliveryAddressId(3L)
                .country("Polska")
                .city("Torun")
                .street("Lewa")
                .build();
    }
}
