package pl.bartek030.foodApp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAddressCreationDTO {

    private String country;
    private String city;
    private String street;
    private Integer deliveryTime;

    private Long restaurantId;
}
