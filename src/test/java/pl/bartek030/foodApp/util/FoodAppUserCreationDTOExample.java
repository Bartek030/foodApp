package pl.bartek030.foodApp.util;

import lombok.experimental.UtilityClass;
import pl.bartek030.foodApp.api.dto.FoodAppUserCreationDTO;

@UtilityClass
public class FoodAppUserCreationDTOExample {

    public FoodAppUserCreationDTO someFoodAppUserCreationDTO1() {
        return FoodAppUserCreationDTO.builder()
                .name("SOMENAME1")
                .surname("SOMESURNAME1")
                .email("SOMEEMAIL1@EMAIL.COM")
                .password("SomePassword123#")
                .phone("+48 111 222 333")
                .country("Polska")
                .city("Warszawa")
                .street("Dluga")
                .number("22")
                .zipCode("30-300")
                .isOwner(true)
                .build();
    }

    public FoodAppUserCreationDTO someFoodAppUserCreationDTO4() {
        return FoodAppUserCreationDTO.builder()
                .name("SOMENAME4")
                .surname("SOMESURNAME4")
                .email("SOMEEMAIL4@EMAIL.COM")
                .password("SomePassword123#")
                .phone("+48 111 222 333")
                .country("Polska")
                .city("Warszawa")
                .street("Dluga")
                .number("22")
                .zipCode("30-300")
                .isOwner(true)
                .build();
    }
}
