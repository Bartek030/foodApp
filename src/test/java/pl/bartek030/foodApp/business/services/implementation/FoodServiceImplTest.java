package pl.bartek030.foodApp.business.services.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.bartek030.foodApp.business.serviceModel.Food;
import pl.bartek030.foodApp.business.serviceModel.FoodCreation;
import pl.bartek030.foodApp.business.serviceModel.Menu;
import pl.bartek030.foodApp.infrastructure.database.repository.FoodRepository;
import pl.bartek030.foodApp.util.FoodCreationExample;
import pl.bartek030.foodApp.util.FoodExample;
import pl.bartek030.foodApp.util.MenuExample;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoodServiceImplTest {

    @InjectMocks
    FoodServiceImpl foodService;

    @Mock
    MenuServiceImpl menuService;

    @Mock
    FoodRepository foodDao;

    @Test
    void givenFoodIdShouldFindFood() {
        // given
        Long foodId = 1L;
        final Food expected = FoodExample.someFood1();

        when(foodDao.findById(any(Long.class))).thenReturn(Optional.of(expected));

        // when
        final Food actual = foodService.findFoodById(foodId);

        // then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void givenFoodIdShouldThrownException() {
        // given
        Long foodId = 1L;
        final String expectedExceptionMessage = "Food with id: [%s] not found".formatted(foodId);

        when(foodDao.findById(any(Long.class))).thenThrow(new RuntimeException(expectedExceptionMessage));

        // when then
        final RuntimeException actualException = assertThrows(
                RuntimeException.class,
                () -> foodService.findFoodById(foodId)
        );
        assertEquals(expectedExceptionMessage, actualException.getMessage());
    }

    @Test
    void givenFoodDataShouldCreateFood() {
        // given
        final FoodCreation foodCreation = FoodCreationExample.someFoodCreation1();
        final Menu menu = MenuExample.someMenu1();
        final Food food = FoodExample.someFood1();

        when(menuService.findById(any(Long.class))).thenReturn(menu);
        doNothing().when(foodDao).addFood(any(Food.class));

        // when
        foodService.addFood(foodCreation);

        // then
        verify(menuService, times(1)).findById(foodCreation.getMenuId());
    }

    @Test
    void givenMenuIdShouldReturnListOfFoods() {
        // given
        Long menuID = 1L;
        List<Food> foods = List.of(
                FoodExample.someFood1(),
                FoodExample.someFood2(),
                FoodExample.someFood3()
        );
        final Menu menu = MenuExample.someMenu1();

        when(menuService.findById(any(Long.class))).thenReturn(menu);
        when(foodDao.findByMenu(any(Menu.class))).thenReturn(foods);

        // when
        final List<Food> actualList = foodService.getFoodsFromMenu(menuID);

        // then
        assertEquals(foods.size(), actualList.size());
    }
}