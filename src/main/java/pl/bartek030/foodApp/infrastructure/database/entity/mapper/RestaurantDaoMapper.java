package pl.bartek030.foodApp.infrastructure.database.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RestaurantDaoMapper {

    @Mapping(target = "menus", ignore = true)
    RestaurantEntity mapRestaurantToEntity(Restaurant restaurant);

    @Mapping(target = "menus", ignore = true)
    @Mapping(target = "restaurantDeliveryAddresses", ignore = true)
    @Mapping(target = "appOrders", ignore = true)
    @Mapping(target = "foodAppUser", ignore = true)
    Restaurant mapRestaurantFromEntity(RestaurantEntity restaurantEntity);
}
