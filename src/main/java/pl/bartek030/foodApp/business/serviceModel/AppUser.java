package pl.bartek030.foodApp.business.serviceModel;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "userName")
@ToString(of = {"userName"})
public class AppUser {
    Long id;
    String userName;
    String password;
    Boolean active;
    Set<Role> roles;
}
