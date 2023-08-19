package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.business.serviceModel.AppUserLogin;

@UtilityClass
public class AppUserLoginExample {
    public AppUserLogin someAppUserLogin1() {
        return AppUserLogin.builder()
                .username("SOMEUSERNAME")
                .password("SOMEPASSWORD")
                .build();
    }
}
