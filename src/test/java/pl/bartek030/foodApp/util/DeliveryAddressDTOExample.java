package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.api.dto.DeliveryAddressDTO;

@UtilityClass
public class DeliveryAddressDTOExample {

    public DeliveryAddressDTO someDeliveryAddressDto1() {
        return DeliveryAddressDTO.builder()
                .deliveryAddressId(1L)
                .country("Polska")
                .city("Warszawa")
                .street("Dluga")
                .build();
    }
}
