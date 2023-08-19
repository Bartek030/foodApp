package pl.bartek030.foodApp.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.bartek030.foodApp.configuration.PersistenceContainerTestConfiguration;
import pl.bartek030.foodApp.infrastructure.database.entity.*;
import pl.bartek030.foodApp.util.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class FoodJpaRepositoryIT {

    private FoodJpaRepository foodJpaRepository;
    private MenuJpaRepository menuJpaRepository;
    private AddressJpaRepository addressJpaRepository;
    private FoodAppUserJpaRepository foodAppUserJpaRepository;
    private RestaurantJpaRepository restaurantJpaRepository;

    @Test
    void shouldReturnListOfFoodsFromMenu() {
        // given
        final AddressEntity addressEntity = addressJpaRepository.saveAndFlush(AddressEntityExample.someAddressEntity1());

        final FoodAppUserEntity foodAppUserEntity =
                foodAppUserJpaRepository.saveAndFlush(FoodAppUserEntityExample.someFoodAppUserEntity1()
                        .withAddress(addressEntity));

        final RestaurantEntity restaurantEntity =
                restaurantJpaRepository.saveAndFlush(RestaurantEntityExample.someRestaurantEntity1()
                        .withFoodAppUser(foodAppUserEntity)
                        .withAddress(addressEntity));

        final MenuEntity menuEntity = menuJpaRepository.saveAndFlush(MenuEntityExample.someMenuEntity1()
                .withRestaurant(restaurantEntity));

        final FoodEntity entity1 = FoodEntityExample.someFoodEntity1()
                .withMenu(menuEntity);
        final FoodEntity entity2 = FoodEntityExample.someFoodEntity2()
                .withMenu(menuEntity);
        final FoodEntity entity3 = FoodEntityExample.someFoodEntity3()
                .withMenu(menuEntity);
        foodJpaRepository.saveAllAndFlush(List.of(entity1, entity2, entity3));

        // when
        final List<FoodEntity> result = foodJpaRepository.findAllByMenu(menuEntity);

        // then
        assertEquals(3, result.size());
    }
}