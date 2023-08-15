package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.api.dto.DeliveryAddressCreationDTO;

@UtilityClass
public class DeliveryAddressCreationDTOExample {

    public DeliveryAddressCreationDTO someDeliveryAddressCreationDTO1() {
        return DeliveryAddressCreationDTO.builder()
                .country("Polska")
                .city("Warszawa")
                .street("Dluga")
                .deliveryTime(40)
                .restaurantId(1L)
                .build();
    }
}
