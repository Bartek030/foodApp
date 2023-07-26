package pl.bartek030.foodApp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantCreationDTO {
    private String name;

    private Long foodAppUserId; // TODO: to be replaced after Spring Security implementation

    private String country;
    private String city;
    private String street;
    private String number;
    private String zipCode;
}
