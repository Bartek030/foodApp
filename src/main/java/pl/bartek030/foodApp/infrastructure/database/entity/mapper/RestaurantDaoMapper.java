package pl.bartek030.foodApp.infrastructure.database.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.business.serviceModel.Restaurant;
import pl.bartek030.foodApp.infrastructure.database.entity.RestaurantEntity;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RestaurantDaoMapper {

    RestaurantEntity mapToEntity(Restaurant restaurant);
}
