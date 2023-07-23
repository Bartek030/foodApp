package pl.bartek030.foodApp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodAppUserDTO {

    private Long foodAppUserId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private AddressDTO address;
}
