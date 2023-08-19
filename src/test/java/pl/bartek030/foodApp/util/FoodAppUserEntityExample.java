package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodAppUserEntity;

@UtilityClass
public class FoodAppUserEntityExample {

    public FoodAppUserEntity someFoodAppUserEntity1() {
        return FoodAppUserEntity.builder()
                .name("NAME1")
                .surname("SURNAME1")
                .email("EMAIL1@EMAIL.COM")
                .phone("+48 111 222 333")
                .address(AddressEntityExample.someAddressEntity1())
                .build();
    }
}
