package pl.bartek030.foodApp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    Long addressId;
    String country;
    String city;
    String street;
    String number;
    String zipCode;
}
