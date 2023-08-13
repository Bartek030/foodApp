package pl.bartek030.foodApp.api.dto;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = {"foodAppUserId"})
@ToString
public class FoodAppUserDTO {
    Long foodAppUserId;
}
