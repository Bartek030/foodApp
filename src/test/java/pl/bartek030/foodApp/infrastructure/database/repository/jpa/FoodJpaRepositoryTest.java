package pl.bartek030.foodApp.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.bartek030.foodApp.configuration.PersistenceContainerTestConfiguration;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.MenuEntity;
import pl.bartek030.foodApp.util.FoodEntityExample;
import pl.bartek030.foodApp.util.MenuEntityExample;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class FoodJpaRepositoryTest {

    private FoodJpaRepository foodJpaRepository;
    private MenuJpaRepository menuJpaRepository;

    @Test
    void shouldReturnListOfFoodsFromMenu() {
        // given
        final MenuEntity menuEntity = menuJpaRepository.saveAndFlush(MenuEntityExample.someMenuEntity1());
        final FoodEntity entity1 = FoodEntityExample.someFoodEntity1();
        final FoodEntity entity2 = FoodEntityExample.someFoodEntity2();
        final FoodEntity entity3 = FoodEntityExample.someFoodEntity3();
        foodJpaRepository.saveAllAndFlush(List.of(entity1, entity2, entity3));

        // when
        final List<FoodEntity> result = foodJpaRepository.findAllByMenu(menuEntity);

        // then
        assertEquals(3, result.size());
    }
}