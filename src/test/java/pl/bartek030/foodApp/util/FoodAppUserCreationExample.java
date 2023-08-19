package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.business.serviceModel.FoodAppUserCreation;

@UtilityClass
public class FoodAppUserCreationExample {

    public FoodAppUserCreation someFoodAppUserCreation1() {
        return FoodAppUserCreation.builder()
                .name("SOMENAME1")
                .surname("SOMESURNAME1")
                .email("SOMEEMAIL@EMAIL.COM")
                .password("SOMEPASSWORD1")
                .phone("+48 111 222 333")
                .country("Polska")
                .city("Warszawa")
                .street("Dluga")
                .number("22")
                .zipCode("30-300")
                .isOwner(true)
                .build();
    }

    public FoodAppUserCreation someFoodAppUserCreation2() {
        return FoodAppUserCreation.builder()
                .name("SOMENAME2")
                .surname("SOMEUSERNAME2")
                .email("SOMEEMAIL2@EMAIL.COM")
                .password("SOMEPASSWORD2")
                .phone("+48 444 555 666")
                .country("Polska")
                .city("Warszawa")
                .street("Dluga")
                .number("22")
                .zipCode("30-300")
                .isOwner(true)
                .build();
    }
}
