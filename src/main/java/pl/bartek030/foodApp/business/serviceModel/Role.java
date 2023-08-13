package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {"role"})
public class Role {
    Long id;
    String role;
}
