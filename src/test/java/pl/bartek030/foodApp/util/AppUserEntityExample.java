package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.business.serviceModel.AppUser;
import pl.bartek030.foodApp.infrastructure.security.AppUserEntity;

@UtilityClass
public class AppUserEntityExample {

    public AppUserEntity someAppUserEntity() {
        return AppUserEntity.builder()
                .id(1L)
                .userName("SOMEEMAIL@EMAIL.COM")
                .password("SOMEPASSWORD1")
                .active(true)
                .build();
    }
}
