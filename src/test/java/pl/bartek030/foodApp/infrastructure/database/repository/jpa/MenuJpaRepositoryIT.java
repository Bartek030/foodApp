package pl.bartek030.foodApp.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.bartek030.foodApp.configuration.PersistenceContainerTestConfiguration;
import pl.bartek030.foodApp.infrastructure.database.entity.MenuEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;
import pl.bartek030.foodApp.util.MenuEntityExample;
import pl.bartek030.foodApp.util.RestaurantEntityExample;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class MenuJpaRepositoryIT {

    private MenuJpaRepository menuJpaRepository;
    private RestaurantJpaRepository restaurantJpaRepository;

    @Test
    void shouldReturnMenuListFromRestaurant() {
        // given
        final RestaurantEntity restaurantEntity =
                restaurantJpaRepository.saveAndFlush(RestaurantEntityExample.someRestaurantEntity1());
        final MenuEntity entity1 = MenuEntityExample.someMenuEntity1();
        final MenuEntity entity2 = MenuEntityExample.someMenuEntity2();
        final MenuEntity entity3 = MenuEntityExample.someMenuEntity3();
        menuJpaRepository.saveAllAndFlush(List.of(entity1, entity2, entity3));

        // when
        final List<MenuEntity> result = menuJpaRepository.findAllByRestaurant(restaurantEntity);

        // then
        assertEquals(3, result.size());
    }
}