package pl.bartek030.foodApp.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.bartek030.foodApp.configuration.PersistenceContainerTestConfiguration;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodAppUserEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;
import pl.bartek030.foodApp.util.FoodAppUserEntityExample;
import pl.bartek030.foodApp.util.RestaurantEntityExample;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class RestaurantJpaRepositoryIT {

    private RestaurantJpaRepository restaurantJpaRepository;
    private FoodAppUserJpaRepository foodAppUserJpaRepository;

    @Test
    void shouldFindRestaurantsByIdList() {
        // given
        final List<Long> idList = List.of(1L, 3L);
        final RestaurantEntity entity1 = RestaurantEntityExample.someRestaurantEntity1();
        final RestaurantEntity entity2 = RestaurantEntityExample.someRestaurantEntity2();
        final RestaurantEntity entity3 = RestaurantEntityExample.someRestaurantEntity3();

        restaurantJpaRepository.saveAllAndFlush(List.of(entity1, entity2, entity3));

        // when
        final List<RestaurantEntity> result = restaurantJpaRepository.findAllById(idList);

        // then
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnAllRestaurantsByFoodAppUser() {
        // given
        final FoodAppUserEntity foodAppUserEntity = FoodAppUserEntityExample.someFoodAppUserEntity1();
        final RestaurantEntity entity1 = RestaurantEntityExample.someRestaurantEntity1();
        final RestaurantEntity entity2 = RestaurantEntityExample.someRestaurantEntity2();
        final RestaurantEntity entity3 = RestaurantEntityExample.someRestaurantEntity3();

        foodAppUserJpaRepository.saveAndFlush(foodAppUserEntity);
        restaurantJpaRepository.saveAllAndFlush(List.of(entity1, entity2, entity3));

        // when
        final List<RestaurantEntity> result = restaurantJpaRepository.findAllByFoodAppUser(foodAppUserEntity);

        // then
        assertEquals(7, result.size());
    }
}