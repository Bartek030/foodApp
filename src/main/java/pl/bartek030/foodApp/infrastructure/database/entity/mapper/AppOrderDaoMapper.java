package pl.bartek030.foodApp.infrastructure.database.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import pl.bartek030.foodApp.business.serviceModel.AppOrder;
import pl.bartek030.foodApp.infrastructure.database.entity.AppOrderEntity;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AppOrderDaoMapper {

    AppOrderEntity mapToEntity(AppOrder appOrder);

    AppOrder mapFromEntity(AppOrderEntity appOrderEntity);
}
