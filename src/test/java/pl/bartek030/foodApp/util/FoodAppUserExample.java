package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;

@UtilityClass
public class FoodAppUserExample {

    public FoodAppUser someFoodAppUser1() {
        return FoodAppUser.builder()
                .foodAppUserId(1L)
                .name("SOMENAME1")
                .surname("SOMESURNAME1")
                .email("SOMEEMAIL@EMAIL.COM")
                .phone("+48 111 222 333")
                .address(AddressExample.someAddress1())
                .build();
    }

    public FoodAppUser someFoodAppUser2() {
        return FoodAppUser.builder()
                .foodAppUserId(2L)
                .name("SOMENAME2")
                .surname("SOMESURNAME2")
                .email("SOMEEMAIL@EMAIL.COM")
                .phone("+48 444 555 666")
                .address(AddressExample.someAddress2())
                .build();
    }

    public FoodAppUser someFoodAppUser3() {
        return FoodAppUser.builder()
                .foodAppUserId(3L)
                .name("SOMENAME3")
                .surname("SOMESURNAME3")
                .email("SOMEEMAIL@EMAIL.COM")
                .phone("+48 777 888 999")
                .address(AddressExample.someAddress3())
                .build();
    }
}
