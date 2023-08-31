package pl.bartek030.foodApp.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import pl.bartek030.foodApp.configuration.PersistenceContainerTestConfiguration;
import pl.bartek030.foodApp.infrastructure.database.entity.AddressEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodAppUserEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.MenuEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;
import pl.bartek030.foodApp.util.AddressEntityExample;
import pl.bartek030.foodApp.util.FoodAppUserEntityExample;
import pl.bartek030.foodApp.util.MenuEntityExample;
import pl.bartek030.foodApp.util.RestaurantEntityExample;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class MenuJpaRepositoryIT {

    private MenuJpaRepository menuJpaRepository;
    private RestaurantJpaRepository restaurantJpaRepository;
    private AddressJpaRepository addressJpaRepository;
    private FoodAppUserJpaRepository foodAppUserJpaRepository;

    @Test
    void shouldReturnMenuListFromRestaurant() {
        // given
        final AddressEntity addressEntity = addressJpaRepository.saveAndFlush(AddressEntityExample.someAddressEntity1());

        final FoodAppUserEntity foodAppUserEntity =
                foodAppUserJpaRepository.saveAndFlush(FoodAppUserEntityExample.someFoodAppUserEntity1()
                        .withAddress(addressEntity));

        final RestaurantEntity restaurantEntity =
                restaurantJpaRepository.saveAndFlush(RestaurantEntityExample.someRestaurantEntity1()
                        .withFoodAppUser(foodAppUserEntity)
                        .withAddress(addressEntity));

        final MenuEntity entity1 = MenuEntityExample.someMenuEntity1()
                .withRestaurant(restaurantEntity);
        final MenuEntity entity2 = MenuEntityExample.someMenuEntity2()
                .withRestaurant(restaurantEntity);
        final MenuEntity entity3 = MenuEntityExample.someMenuEntity3()
                .withRestaurant(restaurantEntity);
        menuJpaRepository.saveAllAndFlush(List.of(entity1, entity2, entity3));

        // when
        final List<MenuEntity> result = menuJpaRepository.findAllByRestaurant(restaurantEntity);

        // then
        assertEquals(3, result.size());
    }
}