package pl.bartek030.foodApp.business.serviceModel;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AppUserLogin {

    String username;
    String password;
}
