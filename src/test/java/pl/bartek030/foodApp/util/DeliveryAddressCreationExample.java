package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.business.serviceModel.DeliveryAddressCreation;

@UtilityClass
public class DeliveryAddressCreationExample {

    public DeliveryAddressCreation someDeliveryAddressCreation1() {
        return DeliveryAddressCreation.builder()
                .country("Polska")
                .city("Warszawa")
                .street("Dluga")
                .deliveryTime(40)
                .restaurantId(1L)
                .build();
    }
}
