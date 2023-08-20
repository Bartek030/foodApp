package pl.bartek030.foodApp.api.dto;

import lombok.*;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuCreationDTO {

    private String name;
    private String category;

    private Long restaurantId;
}
