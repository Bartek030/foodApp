package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.business.serviceModel.User;

@UtilityClass
public class UserExample {

    public User someUser1() {
        return User.builder()
                .id(1L)
                .userName("SOMEEMAIL@EMAIL.COM")
                .password("SOMEPASSWORD1")
                .active(true)
                .build();
    }
}
