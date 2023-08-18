package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.api.dto.OrderDetailsCreationDTO;

@UtilityClass
public class OrderDetailsCreationDTOExample {

    public OrderDetailsCreationDTO someOrderDetailsCreationDTO1() {
        return OrderDetailsCreationDTO.builder()
                .foodId(1L)
                .quantity(2)
                .name("KEBAB")
                .build();
    }

    public OrderDetailsCreationDTO someOrderDetailsCreationDTO2() {
        return OrderDetailsCreationDTO.builder()
                .foodId(2L)
                .quantity(4)
                .name("PIZZA")
                .build();
    }

    public OrderDetailsCreationDTO someOrderDetailsCreationDTO3() {
        return OrderDetailsCreationDTO.builder()
                .foodId(3L)
                .quantity(1)
                .name("BANAN")
                .build();
    }

    public OrderDetailsCreationDTO someOrderDetailsCreationDTO4() {
        return OrderDetailsCreationDTO.builder()
                .foodId(4L)
                .quantity(1)
                .name("BANAN")
                .build();
    }

    public OrderDetailsCreationDTO someOrderDetailsCreationDTO5() {
        return OrderDetailsCreationDTO.builder()
                .foodId(5L)
                .quantity(1)
                .name("BANAN")
                .build();
    }
}
