package pl.bartek030.foodApp.infrastructure.database.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.business.serviceModel.AppOrder;
import pl.bartek030.foodApp.infrastructure.database.entity.AppOrderEntity;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AppOrderDaoMapper {

    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "foodAppUser", ignore = true)
    @Mapping(target = "orderDetails", ignore = true)
    AppOrderEntity mapAppOrderToEntity(AppOrder appOrder);

    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "foodAppUser", ignore = true)
    @Mapping(target = "orderDetails", ignore = true)
    AppOrder mapAppOrderFromEntity(AppOrderEntity appOrderEntity);
}
