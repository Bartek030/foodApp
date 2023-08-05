package pl.bartek030.foodApp.infrastructure.database.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.business.serviceModel.Food;
import pl.bartek030.foodApp.business.serviceModel.Menu;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodEntity;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FoodDaoMapper {

    FoodEntity mapFoodToEntity(Food food);

    @Mapping(target = "menu", ignore = true)
    @Mapping(target = "orderDetails", ignore = true)
    Food mapFoodFromEntity(FoodEntity foodEntity);

    default Food mapFoodFromEntityWithMenu(FoodEntity foodEntity) {
        return mapFoodFromEntity(foodEntity)
                .withMenu(Menu.builder()
                        .menuId(foodEntity.getMenu().getMenuId())
                        .name(foodEntity.getMenu().getName())
                        .category(foodEntity.getMenu().getCategory())
                        .restaurant(Restaurant.builder()
                                .restaurantId(foodEntity.getMenu().getRestaurant().getRestaurantId())
                                .build())
                        .build());
    }
}
