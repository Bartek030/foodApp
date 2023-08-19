package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.business.serviceModel.AppUser;

@UtilityClass
public class UserExample {

    public AppUser someUser1() {
        return AppUser.builder()
                .id(1L)
                .userName("SOMEEMAIL@EMAIL.COM")
                .password("SOMEPASSWORD1")
                .active(true)
                .build();
    }
}
