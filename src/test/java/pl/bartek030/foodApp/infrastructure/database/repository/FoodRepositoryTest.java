package pl.bartek030.foodApp.infrastructure.database.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartek030.foodApp.business.serviceModel.Food;
import pl.bartek030.foodApp.business.serviceModel.Menu;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.MenuEntity;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.FoodDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.entity.mapper.MenuDaoMapper;
import pl.bartek030.foodApp.infrastructure.database.repository.jpa.FoodJpaRepository;
import pl.bartek030.foodApp.util.FoodEntityExample;
import pl.bartek030.foodApp.util.FoodExample;
import pl.bartek030.foodApp.util.MenuEntityExample;
import pl.bartek030.foodApp.util.MenuExample;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FoodRepositoryTest {

    @InjectMocks
    private FoodRepository foodRepository;

    @Mock
    private FoodJpaRepository foodJpaRepository;

    @Mock
    private FoodDaoMapper foodDaoMapper;

    @Mock
    private MenuDaoMapper menuDaoMapper;

    @Test
    void shouldFindFoodByIdCorrectly() {
        // given
        Long id = 1L;

        when(foodJpaRepository.findById(any(Long.class))).thenReturn(Optional.of(FoodEntityExample.someFoodEntity1()));
        when(foodDaoMapper.mapFoodFromEntityWithMenu(any(FoodEntity.class))).thenReturn(FoodExample.someFood1());

        // when
        final Optional<Food> result = foodRepository.findById(id);

        // then
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getFoodId());
    }

    @Test
    void shouldAddFoodCorrectly() {
        // given
        final Food food = FoodExample.someFood1();
        final FoodEntity foodEntity = FoodEntityExample.someFoodEntity1();

        when(foodJpaRepository.save(any(FoodEntity.class))).thenReturn(foodEntity);
        when(foodDaoMapper.mapFoodToEntity(any(Food.class))).thenReturn(FoodEntityExample.someFoodEntity1());

        // when
        foodRepository.addFood(food);

        // then
        Mockito.verify(foodJpaRepository, times(1)).save(foodEntity);
    }

    @Test
    void shouldFindFoodByMenu() {
        // given
        final Menu menu = MenuExample.someMenu1();

        when(menuDaoMapper.mapMenuToEntity(any(Menu.class))).thenReturn(MenuEntityExample.someMenuEntity1());
        when(foodJpaRepository.findAllByMenu(any(MenuEntity.class))).thenReturn(List.of(
                FoodEntityExample.someFoodEntity1(),
                FoodEntityExample.someFoodEntity2(),
                FoodEntityExample.someFoodEntity3()
        ));
        when(foodDaoMapper.mapFoodFromEntity(any(FoodEntity.class)))
                .thenReturn(FoodExample.someFood1())
                .thenReturn(FoodExample.someFood2())
                .thenReturn(FoodExample.someFood3());

        // when
        final List<Food> result = foodRepository.findByMenu(menu);

        // then
        assertEquals(3, result.size());
    }
}