package pl.bartek030.foodApp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodAppUserCreationDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private String country;
    private String city;
    private String street;
    private String number;
    private String zipCode;
    private Boolean isOwner;
}
