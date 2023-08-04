package pl.bartek030.foodApp.infrastructure.database.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.business.serviceModel.FoodAppUser;
import pl.bartek030.foodApp.infrastructure.database.entity.FoodAppUserEntity;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FoodAppUserDaoMapper {

    @Mapping(target = "restaurants", ignore = true)
    @Mapping(target = "appOrders", ignore = true)
    FoodAppUser mapFoodAppUserFromEntity(FoodAppUserEntity foodAppUserEntity);

    FoodAppUserEntity mapFoodAppUserToEntity(FoodAppUser foodAppUser);
}
